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
 * 将map转化成json,map的value也可以是map
 * @param map 转换map
 * @return json数据
 */
function mapToJson(map){
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




/**
 * 获取时间格式为yyyy/MM/dd HH:mm:ss
 * @param formattime 格式时间
 * @return string 格式化后的时间字符串
 */
function getFormatTime(formattime){
	var datetime = getYMDTime();
	var hmtime = getHMTime(formattime);
	datetime = datetime+" "+hmtime;
	return datetime;
}

/**
 * 获取时间格式为HH:mm:ss
 * @param formattime 格式时间
 * @return 格式化后的时间字符串
 */
function getHMTime(formattime){
	var date = new Date();
	date.setTime(formattime-28800000);
	var hour = date.getHours();
	if(hour<10){
		hour = "0"+hour;
	}
	var minute = date.getMinutes();
	if(minute<10){
		minute = "0"+minute;
	}
	datetime = hour+":"+minute;
	return datetime;
}

/**
 * 获取时间格式为yyyy/MM/dd
 * @return 格式化后的时间字符串
 */
function getYMDTime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var datetime = year+"/"+month+"/"+day;
	return datetime;
}

/**
 * 获取当天后的n天，时间格式为yyyy-MM-dd
 * @param days 天数
 * @return 格式化后的时间字符串
 */
function getYMDTimeOther(days){
	var date = new Date();
	date.setDate(date.getDate()+days);
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	if(day<10){
		day = "0"+day;
	}
	 hour = date.getHours(); //小时         
     minutes = date.getMinutes(); //分 
     if(minutes<10){
    	 minutes = "0"+minutes;
 	}
     seconds = date.getSeconds(); //秒  
     if(seconds<10){
    	 seconds = "0"+seconds;
 	}
	var datetime = year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds+"";
	return datetime;
}

/**
 * 求两个时间之差
 * @param date1 时间1
 * @param date2 时间2
 * @return 时间差
 */
function dateDiff(date1,date2){
	var type1 = typeof date1, type2 = typeof date2;
    if (type1 == 'string')
        date1 = stringToTime(date1);
    else if (date1.getTime)
        date1 = date1.getTime();
    if (type2 == 'string')
        date2 = stringToTime(date2);
    else if (date2.getTime)
        date2 = date2.getTime();
    return (date1 - date2) / (1000 * 60 * 60*24);
}

/**
 * 将时间字符串转换成时间
 * @param string 时间字符串
 * @return 时间
 */
function stringToTime(string) {
    var f = string.split(' ', 2);
    var d = (f[0] ? f[0] : '').split('-', 3);
    var t = (f[1] ? f[1] : '').split(':', 3);
    return (new Date(
    parseInt(d[0], 10) || null,
    (parseInt(d[1], 10) || 1) - 1,
    parseInt(d[2], 10) || null,
    parseInt(t[0], 10) || null,
    parseInt(t[1], 10) || null,
    parseInt(t[2], 10) || null
    ));
}

/**
 * 根据时间毫秒数计算时间
 * @param date 时间字符串
 * @param million  毫秒值
 * @param flag 是否带时分秒
 * @return 时间字符串
 */
function getTimeByMillion(date,million,flag){
	var datetime = new Date();
	datetime = stringToTime(date);
	var milliontime = datetime.getTime();
	datetime.setTime(million+milliontime);
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;
	if(month<10){
		month = "0" + month;
	}
	var day = datetime.getDate();
	if(day<10){
		day = "0" + day;
	}
	var hour = datetime.getHours();
	if(hour<10){
		hour = "0"+hour;
	}
	var minute = datetime.getMinutes();
	if(minute<10){
		minute = "0"+minute;
	}
	var yymmdd = year+"-"+month+"-"+day;
	var hhmm = hour+":"+minute; 
	if(flag == "hour"){
		return yymmdd+" "+hhmm;
	}else{
		return yymmdd;
	}
}

