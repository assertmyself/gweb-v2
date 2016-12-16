/**
 * 使用js定义一个HashMap
 */
var HashMap = function(){
	var size = 0;
	var entry = new Object();
	 /**
		 * 存对象
		 */
	 this.put = function (key , value){
	     if(!this.containsKey(key))
	     {
	         size ++ ;
	     }
	     entry[key] = value;
	 };
	 /**
		 * 取对象
		 */
	 this.get = function (key){
	     if( this.containsKey(key) ){
	         return entry[key];
	     }
	     else{
	         return null;
	     }
	 };     
	  /**
		 * 是否包含key
		 * 
		 * @returns Boolean
		 */
	 this.containsKey = function ( key ){
	     return (key in entry);
	 };
	  /**
		 * Map Size
		 * 
		 * @returns Number
		 */
	 this.size = function (){
	     return size;
	 };
	  /**
		 * 所有 key
		 * 
		 * @returns Array
		 */
	 this.keys = function (){
	     var keys = new Array();
	     for(var prop in entry)
	     {
	         keys.push(prop);
	     }
	     return keys;
	 };
	   /**
		 * Clear HashMap
		 */
	 this.clear = function() {
		 if(this.isEmpty()){ return ; }
		  for(var prop in entry){
			  delete entry[prop];
		  }
		  size = 0;
	 };
	  /**
		 * 是否是空
		 * 
		 * @return Boolean
		 */
	 this.isEmpty = function(){
		return (this.size()==0); 
	 };
	  /**
		 * 删除
		 */
	 this.remove = function ( key ){
	     if( delete entry[key] )
	     {
	         size --;
	     }
	 };
	  /**
		 * 是否包含value
		 * 
		 * @returns Boolean
		 */
     this.containsValue = function ( value ){
         for(var prop in entry)
         {
             if(entry[prop] == value)
             {
                 return true;
             }
         }
         return false;
     };
     
     /**
		 * 所有 Value
		 * 
		 * @returns Array
		 */
     this.values = function (){
         var values = new Array();
         for(var prop in entry)
         {
             values.push(entry[prop]);
         }
         return values;
     };
     /**
		 * HashMap序列化,用于Ajax请求
		 * 
		 * @returns String
		 */
     this.serialize = function(){
    	 if(this.isEmpty()){ return null; }
    	 var keys = this.keys();
    	 var str = "";
    	 for(var i in keys ){
    		 var key = keys[i];
    		 str = str+key+"="+this.get(key)+"&";
    	 }
    	 str = str.substring(0, str.length-1);
    	 return str;
     };
}

/**
 * 配置模板中使用的js方法（自定义）
 * 根据类名获取元素的name和value的值，并存储到HashMap中
 * @param obj jquery选择集合
 * @return HashMap
 */
function getValueArray(obj){
	var tempHashMap = new HashMap();
	$.each(obj,function(i,n){
		if($(this).attr("name")!= undefined || $(this).attr("name")!=null){
			tempHashMap.put($(this).attr("name"),$(this).val());
		}
	});
	return tempHashMap;
}

/**
 * 将map转化成json,map的value也可以是map
 * @param map 转换map
 * @return json数据
 */
function map2Json(map){
	var keys="",values="";
	if(!isNull(map)){
		keys = map.keys();
		values = map.values();
	}
	var json = "{";
	for(var i=0;i<keys.length;i++){
		var key = keys[i];
		var value = map.get(key);
		if(typeof value == 'object'){
			json = json + "\""+key+"\":"+map2Json(value)+",";
		}else{
			json = json + "\""+key+"\":\""+value+"\",";
		}
	}
	if(json.length==1){
		json = json + "}";
	}else{
		json = json.substring(0,json.length-1)+"}";
	}
	return json;
}

/**
 * 根据矢量和标量的不同获取不同的map
 * @param map 存放数据的最终map
 * @param valueMap 存放临时数据的map
 * @param modelName 设备类型
 * @param templetName 配置模板类名
 * @param fileName 文件名
 * @return map
 */
function addMap(map,valueMap,templetName,fileName){
	var isVector = false;
	var ssidIndex = "";
	var keys = valueMap.keys();
	for(var i=0;i<keys.length;i++){
		if(keys[i].indexOf("{index}") != -1){
			isVector = true;
			ssidIndex = valueMap.get(keys[i]);
			break;
		}
	}
	if(valueMap.size() > 0){	
		if(isVector){
			map.put(fileName+"-"+templetName+"-"+ssidIndex,valueMap);
		}else{
			map.put(fileName+"-"+templetName,valueMap);
		}
	}
	return map;
}

/**
 * 删除map中特定的值
 * @param map 存放数据的最终map
 * @param modelName 设备类型
 * @param fileName 文件名
 * @return map
 */
function removeValue(map,className,fileName){
		if(className.indexOf("ssid") != -1){
			for(var i =0;i<16;i++){
				map.remove(fileName+"-ssidBasic-"+i);
				map.remove(fileName+"-ssidSec-"+i);
				map.remove(fileName+"-ssidAdvance-"+i);
				map.remove(fileName+"-ssidFlowControl-"+i);
			}
		}else{
			map.remove(fileName+"-"+className);
		}
	return map;
}

/**
 * 删除数组中的某个元素，js数组中没有提供相应的方法
 * @param array 数组
 * @param element 需要删除的元素
 * @return 删除元素后的数组
 */
function delArrayElement(array,element){
	var temp = [];
	for(var i=0;i<array.length;i++){
		if(array[i] != element){
			temp.push(array[i]);
		}
	}
	return temp;
}

/**
 * 清空数组
 * @param array 数组
 */
function clearArray(array){
	array.length = 0;
}

/**
 * 数组中是否包含某个元素
 * @param array 数组
 * @param element 元素
 * @return Boolean
 */
function arrayIsContain(array,element){
	for(var i=0;i<array.length;i++){
		if(array[i] == element){
			return true;
		}
	}
	return false;
}