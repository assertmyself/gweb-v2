
/**
 * tree的右键提示，添加默认的常用事件，在rightMenuOpts中用["下移", "moveDown"],直接使用
 * @param setting
 */
var global_zTree;
var treeActions = {
    //刷新节点
    refreshNode: function (treeNode, zTree) {
        treeNode = treeNode || zTree.getSelectedNodes()[0];
        zTree.reAsyncChildNodes(treeNode, "refresh");
    },
    //刷新父节点
    refreshParentNode: function (treeNode, zTree) {
        treeNode = treeNode || zTree.getSelectedNodes()[0];
        zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
    },
    moveUp: function (treeNode, zTree, className) {
        if (!isVaildTreeId(treeNode)) return;
        className = className || clazz;
        $.get(CONTEXT_NAME + "/commonPage/moveUp.do?id=" + treeNode.id + "&clazz=" + className, function () {
            treeActions.refreshParentNode(treeNode, zTree);
        });
    },
    moveDown: function (treeNode, zTree, className) {
        if (!isVaildTreeId(treeNode)) return;
        className = className || clazz;
        $.get(CONTEXT_NAME + "/commonPage/moveDown.do?id=" + treeNode.id + "&clazz=" + className, function () {
            treeActions.refreshParentNode(treeNode, zTree);
        });
    },
    treeReorder: function (treeNode, zTree, className) {
        className = className || clazz;
        $.get(CONTEXT_NAME + "/commonPage/treeReorder.do?clazz=" + className, function () {
            treeActions.refreshParentNode(treeNode, zTree);
            alert("重新排序成功");
        });
    },
    curdAction: function (treeNode, zTree, title, url, opts) {
        treeNode.url = url;
        opts = opts || {};
        openWindow(title, url, opts.isRefreshGrid, opts.width, opts.height);
    }
};

function isVaildTreeId(treeNode) {
    return treeNode && treeNode.id;
}

/**
 * @param ele
 * @param setting
 */
function initTree(ele, setting, zNodes,configType) {
    var zTree;
    setting = setting || {};
    //简单配置
    var defalutSimpleOpts = {
        isOpenRoot: true, //是否打开根节点
        menuParams: {
            destory: true
        }
//        clickOpts:{
//            "all":null, //默认配置，或type没有时取该配置
//            "root":null,
//            "data":CONTEXT_NAME + "/sysPrivilege/modify.do?id={id}"
//        },
    };
    var simpleOpts = $.extend(defalutSimpleOpts, setting.simpleOpts);
    var execOnce = true;
    var firstAsyncSuccessFlag = 0;
    var defaultOpts = {
        check: {
            enable: false,
            chkStyle: "radio",
            radioType: "level"
        },
        async: {
            enable: true,
            url: simpleOpts.treeUrl,
            autoParam: ["id", "uid", "type"],
            otherParam: {"otherParam": ""}
        },
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                if (treeNode) {
                    var type = treeNode.type || "all";
                    if (!simpleOpts.clickOpts) return;
                    var clickObjs = simpleOpts.clickOpts;
                    if ($.isFunction(simpleOpts.clickOpts)) {
                        clickObjs = simpleOpts.clickOpts(treeId, treeNode);
                    }
                    var info = clickObjs[type] || clickObjs["all"];
                    if (info) {
                        if ($.isArray(info)) {
                            treeActions.curdAction(treeNode, zTree, info[0], processUrl(info[1], treeNode, zTree), info[2]);
                        } else {
                            loadAjaxData("dataInfo", processUrl(info, treeNode, zTree));
                        }
                    }
                }
            },
            onRightClick: function (event, treeId, treeNode) {
                if (!treeNode) {
                    return;
                }
                if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
                    zTree.cancelSelectedNode();
                } else if (treeNode && !treeNode.noR) {
                    zTree.selectNode(treeNode);
                }
                if (simpleOpts.rightMenuOpts) {
                    var menuEle = getTreeMenus(simpleOpts.rightMenuOpts, treeNode, zTree, simpleOpts.menuParams);
                    $(menuEle).menu('show', fitPos(event, window, menuEle));
                }
            },
            onAsyncSuccess: function (event, treeId, treeNode, msg) {
            		if (execOnce) {//根节点自动展开
                        var allNodes = zTree.getNodes();
                        if (allNodes && allNodes.length > 0) {
                            execOnce = false;
                            var zznode = treeNodejson(zTree.getNodes()[0]);
                            zTree.expandNode(zznode, true);
                        }
                        resizeTree();
                     }else{
                    	 var nodes = treeNode.children;
                    	 if(treeNode.checked){
                    		 for(var i=0;i<nodes.length;i++){
                    			 zTree.checkNode(nodes[i], true);
                    		 }
                    	 }
                    	 //给节点添加iconOpen和iconClose属性
                    	 for(var k = 0;k<nodes.length;k++){
                    		 treeNodejson(nodes[k]);
                    	 }
                    	 
                     }
            }
        }
    }; 
    if (!simpleOpts.isOpenRoot) {
        defaultOpts.callback.onAsyncSuccess = null;
    }
    var opts = {};
    if(setting.customerOpts){
    	 $.extend(true,defaultOpts,setting.customerOpts);
    }
    for (var optName in defaultOpts) {
        opts[optName] = $.extend(defaultOpts[optName], setting[optName]);
    }
    //添加非异步加载
    if (zNodes) {
        opts.async = null;
    }
    global_zTree = zTree = $.fn.zTree.init(ele, opts, zNodes);
    return zTree;
}

function expandLevel(treeObj,node,level)  
{  
    var childrenNodes = treeObj.transformToArray(node);  
    for(var i=0;i<childrenNodes.length;i++){
        treeObj.expandNode(childrenNodes[i], true, false, false);
        level=level-1;  
        if(level>0)  
        {  
            expandLevel(treeObj,childrenNodes[i],level);  
        }
    }  
} 
function fitPos(event, container, ele) {
    /*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
    container = $(container);
    ele = $(ele);
    var left = event.clientX;
    var top = event.clientY;
    if (left + $(ele).outerWidth() > $(container).width() + $(container).scrollLeft()) {
        left = $(container).width() + $(container).scrollLeft() - $(ele).outerWidth();
    } else {
        left += container.scrollLeft();
    }
    if (top + $(ele).outerHeight() > $(container).height() + $(container).scrollTop()) {
        top -= $(ele).outerHeight();
    } else {
        top += container.scrollTop();
    }
    return {
        left: left,
        top: top
    }
}

//处理tree菜单参数，见上simpleOpts.rightMenuOpts示例
function getTreeMenus(menuOpts, treeNode, zTree, menuParams) {
//    var opts = [[text, treeAction, confirm]]
    var menu = new RightMenu(menuParams);
    if ($.type(menuOpts) == "function") {
        menuOpts = menuOpts(treeNode, zTree);
    }
    for (var i = 0; i < menuOpts.length; i++) {
        var arr = menuOpts[i];
        if (arr.length == 0) {
            menu.addSeparator();
        } else {
            menu.addItem(arr[0], processTreeAction(arr[0], arr[1], !!arr[2], treeNode, zTree));
        }
    }
    return menu.getMenu();
}

//处理链接，默认替换掉id，也可以直接{treeNode.id}，替换url中的元素
function processUrl(url, treeNode, zTree) {
    var id = isVaildTreeId(treeNode) ? treeNode.id : "";
    url = url.replace("{id}", id);
    formatStr(url, treeNode);
    formatStr(url, zTree);
    return url;
}

function processTreeAction(text, treeAction, confirm, treeNode, zTree) {
    function execTreeAction(treeAction, treeNode, zTree) {
        if ($.isFunction(treeAction)) {
            treeAction(treeNode, zTree);
        } else if ($.type(treeAction) == "string") {
            treeActions[treeAction](treeNode, zTree);
        } else if ($.isArray(treeAction)) {
            var title = treeAction[0];
            var url = treeAction[1];
            treeActions.curdAction(treeNode, zTree, title, processUrl(url, treeNode, zTree));
        } else {
        }
    }

    return function () {
        if (treeAction && confirm === true) {
            showConfigMsg('您确定要进行' + text + '操作？',function (bool) {
                if (bool) {
                    //if (text.indexOf("删除") > -1 && !treeNode.extendLeaf) {
                    //    showWarningMsg("该节点下已经存在子节点，不能删除！")
                    //} else {……
                    	
                   //主要是判断删除的情况
                   if ($.type(treeAction) == 'string' && treeAction.indexOf("?") > -1) {
                       var url = processUrl(treeAction, treeNode, zTree);
                       treeNode.url = url;
                       saveAjaxData(url, null, null);
                    } else {
                       execTreeAction(treeAction, treeNode, zTree);
                    }
                }
            });

        } else {
            execTreeAction(treeAction, treeNode, zTree);
        }
    }
}

function RightMenu(params) {
    params = $.extend({
        destory: true,
        beforeShow: null
    }, params);
    this.ele = $('<div id="rightMenu" class="easyui-menu" style="width:120px;"></div>');	

    this.init = function () {
        if (params.destory) {
            this.destory();
        }
        $(this.ele).menu(params);		
    };
    //添加分隔
    this.addSeparator = function () {
        this.ele.append('<div class="menu-sep">&nbsp;</div>');
    };
    /**
     * 添加菜单项
     * @param text  文本，可以是html
     * @param action 绑定事件，如果是funtion默认绑定click，如果是object,则绑定多组事件
     */
    this.addItem = function (text, action) {
        var menuItem;
        if (text.charAt(0) === "<" && text.charAt(text.length - 1) === ">" && text.length >= 3) {
            menuItem = $(text);
        } else {
            menuItem = $('<div>' + text + '</div>');
        }
        if ($.type(action) == 'function') {
            menuItem.click(action);
        } else {
            menuItem.bind(action);
        }
        this.ele.append(menuItem);
    };
    this.getMenu = function () {
        this.init();
        if (params.beforeShow) {
            params.beforeShow(this.ele);
        }
        return this.ele;
    };
    //销毁菜单，如果在该页面也有使用easyUI的右键菜单可能会造成某些异常如元素的阴影无法显示，因为下面已经销毁了所有的menu-shadow元素
    this.destory = function () {
        $("#rightMenu").remove();
        $(".menu-shadow").remove();
    }
}

/**
 * 增删改情况下刷新树节点
 */
function refreshTree(zTreeId) {
    zTreeId = zTreeId || global_zTree;
    if (zTreeId) {
        var treeNode = zTreeId.getSelectedNodes()[0];
        if (!treeNode) {
            return;
        }
        if (treeNode.url) {
            if (treeNode.url.indexOf("add.do") > -1) {      //添加情况
                //当当前节点不是父节点且在上面添加子节点时，刷新当前节点则无刷新，所以刷新父节点，因为刷新节点没有回调函数，所以不展开当前节点
                if (!treeNode.isParent) {
                    treeActions.refreshParentNode(treeNode, zTreeId);
                } else {
                    treeActions.refreshNode(treeNode, zTreeId);
                }
            } else if (treeNode.url.indexOf("delete.do") > -1) {    //删除情况
                var parentNode = treeNode.getParentNode();
                //当当前节点为唯一子节点且被删除时，刷新它的祖父节点，否则父节点左边图标无法刷新
                if (treeNode.isFirstNode && treeNode.isLastNode && parentNode) {
                    treeActions.refreshParentNode(parentNode, zTreeId)
                } else {
                    treeActions.refreshParentNode(treeNode, zTreeId);
                }
            } else {   //修改情况
                treeActions.refreshParentNode(treeNode, zTreeId);
            }
        } else {
            treeActions.refreshParentNode(treeNode, zTreeId);
        }
        delete treeNode.url;
    }
}
/**
 * demo
 * var aa = {
 *  aa: {cc:3,dd:4},
 *  bb:2
 *}
 *formatStr("fa{aa.cc},fdkfj{bb}",aa);
 *formatStr("aaa{0},{1}",23,33);
 *formatStr("aaa{0},{1}",[23,33]);
 * @param str
 * @param arrObj
 */
formatStr = function (str, arrObj) {
    var sourceStr = '';
    var reg;
    if ($.type(arrObj) === "object") {
//        for (var key in arrObj) {
//            reg = new RegExp("\\{" + key + "\\}", "g");
//            sourceStr = sourceStr.replace(reg, arrObj[key]);
//        }
        var isExec = false;
        var tail = str;
        reg = /\{(.*?)\}/;
        while (reg.exec(tail)) {
            sourceStr += RegExp.leftContext;
            var cascadeProperty = RegExp.$1;
            var properties = cascadeProperty.split(".");
            sourceStr += _GetValues(properties, arrObj);
            tail = RegExp.rightContext;
            isExec = true;
        }
        if (isExec) {
            sourceStr += RegExp.rightContext;
        } else {
            sourceStr = tail;
        }
    } else {
        sourceStr = str;
        if (!(arrObj instanceof Array)) {
            arrObj = Array.apply(this, arguments);
            arrObj.shift();
        }
        for (var i = 0; i < arrObj.length; i++) {
            reg = new RegExp("\\{" + i + "\\}", "g");
            sourceStr = sourceStr.replace(reg, arrObj[i]);
        }
    }
    function _GetValues(properties, arrObj) {
        var result = arrObj;
        for (var i = 0; i < properties.length; i++) {
            var property = properties[i];
            result = result[property];
            if (!result) {
                result = "";
                break;
            }
        }
        return result;
    }

    return sourceStr;
};



//处理
function treeNodejson(node) {
	if(node.type != "device"){
		node['iconOpen'] = node.icon.replace(".png","_open.png");
	}
	node['iconClose'] = node.icon;
	return node;
}

/**
 * @param name String 参数名
 * @param valuse String[] 值
 * @return String
 */
function multiValueParamSerialize(name,values){
	var param = "";
	if(name == null || name == "" || typeof values != "object"){
		return param;
	}
	for(var i = 0;i < values.length;i++){
		var value = values[i];
		param = param + name + "=" + value + "&"; 
	}
	if(param.length < 1){
		return param;
	}
	return param.substring(0, param.length-1);
}
$("#treeLeft").scroll(function (){
	resizeTree();
})
function resizeTree(){
	var height=$("#treeLeft").parent().height();
	var winWidth=$(window).width();
	var tree=$("#treeLeft ul")[0];
	if(winWidth<768){
		$(tree).removeAttr("style");
		return;
	}
	if(height>60){
		$(tree).height(height-10);
	}
}
