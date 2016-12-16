/**
 * @warn: colModel
 */
//是否显示导出excel按钮，默认不显示
var isExportExcel = true;
function gridinit(ele, params, context) {
    isExportExcel = params.isExportExcel;
    if (isExportExcel == undefined) isExportExcel = false;

    var conf = {
        gridOpts: params.gridOpts,
        searchOpts: params.searchOpts,
        userOpts: params.userOpts
    };
    var userOpts = {
        defaultQuery: {},
        buildQuery: null,
        queryButton: null,
        resetButton: (function () {
            var ele = $("<span style='position: absolute; width: 20px;height: 19px;margin-left:-25px;'></span>")
                .insertAfter("#queryConditionDesc");
            /* if ($.browser.msie && $.browser.version > 7) {
             ele.css({"background-color": "white", opacity: 0});   //>ie7 元素不可见就不能触发click事件
             }*/
            return ele;
        })(),
        queryDesc: null,
        otherConditionEle: "#otherConditions",
        fitPageOpts: null
    };
    userOpts = $.extend(userOpts, conf.userOpts);

    if (context) {
        var contextJq = $(context);
        ele = contextJq.find(getSelector(ele));
        var arr = ["queryButton", "resetButton", "queryDesc", "otherConditionEle"];
        for (var i = 0; i < arr.length; i++) {
            var name = arr[i];
            var opt = userOpts[name];
            if (opt) {
                opt = getSelector(opt);
                userOpts[name] = $(contextJq).find(opt);
            }
        }
    }
    /*================begin=========初始化grid==============*/
    var gridOpts =
    {
        datatype: "json",
        height: 360,
//                width:1024,
        actModel: null,
        autowidth: true,
        rowNum: (function () {
            //    if (screen.height < 1000) {
            //       return 10;
            //     }
            return 15;
        })(),
        autoHeight: true,
        hidegrid: false,
        viewrecords: true,
        rowList: [10, 15, 20, 30, 40, 50],
        prmNames: {order: "sort"},
        postData: {
            filters: userOpts.defaultQuery
        },
        buildQuery: userOpts.buildQuery,
        jsonReader: {
            repeatitems: false,
            cell: "",
            id: "0"
        },
        columns: null,
        caption: "查询结果列表",
        serializeGridData: function (postData) {
            var newPostData = $.extend({}, postData);
            newPostData.columns = this.p.columns;
            var conf = this.p;
            var searchTypeMap = searchNameTypeMap(conf);
            var filters = newPostData.filters || {};
            if ($.type(filters) != "string") {

                //置入查询条件
                if (conf.buildQuery != null) {
                    if (conf.buildQuery.val().length > 0) {
                        var params = JSON.parse(conf.buildQuery.val());
                        for (i = 0; i < filters.rules.length; i++) {
                            if (params[searchTypeMap[filters.rules[i].field].name]!=null) {
                               var aaa= params[searchTypeMap[filters.rules[i].field].name];
                                filters.rules[i].data = params[searchTypeMap[filters.rules[i].field].name];
                            }
                        }
                    }

                }

                filters = JSON.stringify(filters); //拷贝对象，jquery的extend方法不能做第二层的拷贝
            }
            filters = $.parseJSON(filters.replace(/[\f\n\r\t\v]/g, ""));
            processFilters(filters, searchTypeMap);
            if (newPostData.sidx) {
                newPostData.sidx = searchTypeMap[newPostData.sidx].name;
            }
            filters = {
                "group": filters,
                "orderColumn": newPostData.sidx,
                "orderType": newPostData.sort
            };
            if (typeof filters !== "string") {
                filters = JSON.stringify(filters);
            }
            newPostData.filters = filters;
            return newPostData;
        },
        loadComplete:function(data){//@autor doujun  grid hint info
			if(!isNull(data.success)){
				if(data.success){
					showInfoMsg(data.msg);
				}else{
					showWarningMsg(data.msg);
				}
			}
		},
		loadError:function(xhr,status,error){
			showErrorMsg(error);
        },
        // select on checkbox
        beforeSelectRow: function (rowid, e) { 
		    var $myGrid = $(this), 
		        i = $.jgrid.getCellIndex($(e.target).closest('td')[0]), 
		        cm = $myGrid.jqGrid('getGridParam','colModel'); 
		    return (cm[i].name ==='cb'); 
		}
    };
    $.extend(gridOpts, conf.gridOpts || {});
    addDefaultIndex(gridOpts.colModel, gridOpts.colNames);
    //添加colmuns，供gridData中的columns用
    addColumnAndProcess(gridOpts);
    if (gridOpts.actModel) {
        gridOpts.colModel = gridOpts.colModel.concat(gridOpts.actModel)
    }

    //调整grid宽度和高度
    var adjustGridSize = (function () {
        var isCustomContainer = true;
        if ($(ele).parentsUntil("#mainContent").last().is("html")) {
            isCustomContainer = false;
        }
        var isIframeContainer = false;
        if ($("#mainContent").length == 0) {
            isIframeContainer = true;
        }
        var container = $("#mainContent");
        var opts = {
            minWidth: 800,
            minHeight: 457,
            offsetX: 5,
            offsetY: 160
        };

        if (isIframeContainer) {
            container = parent.$("#mainContent");
            opts.offsetY = 190;
        }
        if (screen.height < 800) {
            opts.minHeight = 310;
        }
        var treeContainer = container.find("#treeLeft");
        return function () {
            if (!isCustomContainer && !isIframeContainer) {
                return;
            }
            $.extend(opts, userOpts.fitPageOpts);

            var w;
            if (treeContainer.length == 0) {
                w = container.width() - opts.offsetX;
            } else {
                w = container.width() - treeContainer.width() - opts.offsetX-6;
            }
            var h = container.height() - opts.offsetY;
            if (w < opts.minWidth) {
                w = opts.minWidth;
            }
            if (h < opts.minHeight) {
                h = opts.minHeight;
            }
            $(ele).setGridWidth(w-3);
            $($(ele)[0].grid.bDiv).height(h + 10);
        }
    })();
    //调整grid宽度和高度
    var adjustGridHeigth = (function () {
        var isCustomContainer = true;
        if ($(ele).parentsUntil("#mainContent").last().is("html")) {
            isCustomContainer = false;
        }
        var isIframeContainer = false;
        if ($("#mainContent").length == 0) {
            isIframeContainer = true;
        }
        var container = $("#mainContent");
        var opts = {
            minWidth: 1154,
            minHeight: 457,
            offsetX: 5,
            offsetY: 160
        };

        if (isIframeContainer) {
            container = parent.$("#mainContent");
            opts.offsetY = 190;
        }
        if (screen.height < 800) {
            opts.minHeight = 310;
        }
        var treeContainer = container.find("#treeLeft");
        return function () {
            if (!isCustomContainer && !isIframeContainer) {
                return;
            }
            $.extend(opts, userOpts.fitPageOpts);

            var w;
            if (treeContainer.length == 0) {
                w = container.width() - opts.offsetX;
            } else {
                w = treeContainer.width() - opts.offsetX;
            }
            var h = container.height() - opts.offsetY;
            if (w < opts.minWidth) {
                w = opts.minWidth;
            }
            if (h < opts.minHeight) {
                h = opts.minHeight;
            }
            $($(ele)[0].grid.bDiv).height(h + 10);
        }
    })();
    $(ele).jqGrid(gridOpts);
    adjustGridSize();
	$(window).resize(function () {
//        由于div宽度在resize时还没有调整所以无法直接获取到调整后的宽度，故采用延时处理
       // window.setTimeout(adjustGridHeigth, 500);
   });
    /*================end====初始化grid==============*/

    /*================begin=========初始化search==============*/
    var otherConditons = {
        otherConditionsRule: function () {
            var otherConditionArr = [];
            var tab = $(userOpts.otherConditionEle);
            $("tr", tab).has(".data").each(function () {
                otherConditionArr.push({
                    colName: $(".columns", this).text().match(/[^:：&]*/)[0],
                    op: $(".operators", this).text(),
                    data: $(".data", this).find(":input,select").val()
                });
            });
            return otherConditionArr;
        },
        otherConditionsDesc: function (orgSql) {
            var conditionsRules = otherConditons.otherConditionsRule();
            var conditionsDesc = [];
            for (var i = 0; i < conditionsRules.length; i++) {
                var rule = conditionsRules[i];
                if (rule.data) {
                    conditionsDesc.push([rule.colName, rule.op, rule.data].join(" "));
                }
            }
            var sqlDesc = [];
            if (orgSql) {
                sqlDesc.push(orgSql);
            }
            if (conditionsDesc.length > 0) {
                sqlDesc.push(conditionsDesc.join(" 并且 "));
            }
            return sqlDesc.join(" 并且 ");
        },
        otherConditionsPostData: function () {
            var conditionPostData = {};
            $(userOpts.otherConditionEle).find(".data").find(":input,select").each(function () {
                conditionPostData[this.name] = $(this).val();
            });
            $(ele).jqGrid("setGridParam", {postData: conditionPostData});
        },
        otherConditionsReset: function () {
            $(userOpts.otherConditionEle).find(".data").find(":input,select").val("");
            otherConditons.otherConditionsPostData();
        }
    };

    var isShowQuery = false;
    var searchOpts = {
        multipleSearch: true,
        multipleGroup: true,
        showQuery: false,
        advance: true,
        closeAfterSearch: true,
        closeAfterReset: true,
        otherConditons: otherConditons,
        onChange: function (sp) {
            var sqlDesc = otherConditons.otherConditionsDesc(this.toUserFriendlyString());
            $('.query', this).html(sqlDesc);
            $(userOpts.queryDesc).val(sqlDesc);
        },
        onInitializeSearch: function (ele) {
            $(userOpts.resetButton).click(function () {
                $(ele.selector + "_reset").trigger("click");
            })
        },
        beforeRedraw: function (p) {
            p.showQuery = isShowQuery;
        },
        afterRedraw: function () {
            var that = this;
            $(this).append($(userOpts.otherConditionEle).show().change(function () {
                that.onchange();
            }));
            if (!isShowQuery) {
                $("table:[class^='group'] tr:eq(1),.queryresult", this).hide();
            }
        },
        afterShowSearch: function (ele, p) {
            var fid = ele.selector.substring(1);
            var queryWin = $("#searchmod" + fid);
            var clickBtn = queryWin.find("#" + fid + "_query ");
            clickBtn.unbind("click");
            clickBtn.bind("click", function (e) {
                $(queryWin).find("table:[class^='group'] tr:eq(1),.queryresult").toggle();
                $(queryWin).find("td.columns").find("select,span").toggle();
                $(queryWin).find("td.operators").find("select,span").toggle();
                $(queryWin).find("table.group .delete-rule").toggle();
                $(this).hide();
                isShowQuery = true;
                return false;
            });
//            clickBtn.html(clickBtn.html().replace("Query", "高级"));
        },
        onSearch: function () {
            otherConditons.otherConditionsPostData();
        },
        beforeReset: function (fid, p) {
            isShowQuery = false;
            otherConditons.otherConditionsReset();
            $(userOpts.queryDesc).val("");
            $("#" + fid + "_query").show();
        },
        jqModal: false
    };
    $.extend(searchOpts, conf.searchOpts || {});

    if (userOpts.queryButton) {
        $(userOpts.queryButton).click(function () {
//            $(ele).jqGrid("setGridParam", {postData:{filters:userOpts.defaultQuery}});   //因为第一次加载时filters在beforeRequest中被替换，点search不能正常初始化
            $(ele).jqGrid("joSearchGrid", searchOpts);
        });
    }
    /*================end=========初始化search==============*/

    function addColumnAndProcess(gridOpts) {
        var colModel = gridOpts.colModel;
        var columns = [];
        for (var i = 0; i < colModel.length; i++) {
//            colModel[i].name = colModel[i].name.replace(/\./g, "_");  //把前面定义的点换成_，因为传回来的json也是_
            columns.push(colModel[i].name);
        }
        gridOpts.columns = columns.join(",");
    }

    function addDefaultIndex(colModel, colNames) {
        var processDateCol = function (colM) {
            colM.searchoptions = colM.searchoptions || {};
            //普通方式
            if (colM.searchoptions.sopt && colM.searchoptions.sopt === 'ot') {
                colM.searchoptions.sopt = "";
                colM.searchoptions.readonly = true;
                colM.searchoptions.dataInit = function (ele) {
                    ele.onclick = function () {
                        var calJson = 'yyyy-MM-dd';
                        if (colM.searchtype == "datetime") {
                            calJson = 'yyyy-MM-dd HH:mm:ss';
                        }
                        calendarByJson({el: ele, dateFmt: calJson});
                        $(ele).trigger("change");
                    }
                };
                return;
            }
            //带时间范围的方式
            colM.inputtype = "custom";
            colM.searchoptions.sopt = "bt";
            colM.searchoptions.custom_element = function (vl, options) {
                $.valHooks.span = {
                    get: function (elem) {
                        return $(elem).attr("value");
                    }
                };
                var inputWidth = "width: 80px;";
                var spanWidth = "210px";     //不指定span大小，IE下会
                if (colM.searchtype != "date") {
                    inputWidth = "width: 125px;";
                    spanWidth = "280px";
                }
                var html = '<span style="width: ' + spanWidth + '">从<input type="text" readonly="true" role="textbox" style="' + inputWidth + '" readonly="true">' +
                    '到<input type="text" readonly="true" role="textbox" style="' + inputWidth + '"  readonly="true"></span>';
                var element = $(html);
                var inps = $(element).find("input");
                if (options.values) {
                    var valArr = options.values.split(",");
                    inps.eq(0).val(valArr[0] || '');
                    inps.eq(1).val(valArr[1] || '');
                }
                var onChangeFunc = function (dp) {
                    var span_elm = $(this).parent().parent();
                    span_elm.attr("value", inps.map(
                        function () {
                            return $(this).val();
                        }).get().join(","));
                    $(span_elm).trigger("change");
                };
                var calJson = {dateFmt: 'yyyy-MM-dd', onpicked: onChangeFunc, oncleared: onChangeFunc};
                if (colM.searchtype == "datetime") {
                    calJson.dateFmt = 'yyyy-MM-dd HH:mm:ss';
                }
                if (options.calJson) {
                    calJson = $.extend(calJson, options.calJson);
                }
                $(inps).eq(0).click(function () {
                    calendarByJson($.extend({}, calJson, {el: this}));
                    return element;
                });
                $(inps).eq(1).click(function () {
                    calendarByJson($.extend({}, calJson, {el: this, minDate: $(inps).get(0).value}));
                    return element;
                });
                if (options.dataInit) {
                    options.dataInit(element);
                }
                return element;
            }
        };

        for (var i = 0; i < colModel.length; i++) {
            var index = colModel[i].index;
            if (!index) {
                colModel[i].index = colNames[i];  //查询字段根据index显示
            }
            var colM = colModel[i];
            if (colM.searchtype && (colM.searchtype == "date" || colM.searchtype == "datetime")) {
                processDateCol(colM);
            }
        }
    }

    function searchNameTypeMap(conf) {
        var colModel = conf.colModel;
        var map = {};
        for (var i = 0; i < colModel.length; i++) {
            var cm = colModel[i];
//            if (cm.searchtype) {
            map[cm.index] = cm;
//            }
        }
        return map;
    }

//    传入的必须是字符串或jquery对象
    function getSelector(ele) {
        if (ele && ele.selector) {
            return ele.selector;
        }
        return ele;
    }

    function processFilters(group, searchTypeMap) {
        if (!group) {
            return;
        }
        var index;
        if (group.groups !== undefined) {
            for (index = 0; index < group.groups.length; index++) {
                processFilters(group.groups[index], searchTypeMap);
            }
        }
        if (group.rules !== undefined) {
            for (index = 0; index < group.rules.length; index++) {
                var rule = group.rules[index];
                if (rule.alias) {
                    rule.field = rule.alias;
                    continue;
                } else if (!searchTypeMap[rule.field]) {
                    continue;
                }
                rule.searchtype = searchTypeMap[rule.field].searchtype;
                rule.field = searchTypeMap[rule.field].name;//.replace(/_/g, ".");
                if (rule.op == 'bt') {
                    var dates = rule.data.split(",");
                    rule.data = dates[0];
                    rule.op = 'ge';
                    if (dates.length > 1) {
                        var rule1 = $.extend({}, rule);
                        rule1.op = 'le';
                        rule1.data = dates[1] || '';
                        group.rules.push(rule1);
                    }
                }
            }
        }
    }

    //todo excel export
//    if (isExportExcel) {
//        if ($("#pager2 div .ui-pg-div").html() == null) {
//            $("#listGrid").jqGrid('navGrid', '#pager2', {del:false, add:false, edit:false, search:false, refresh:false}).navButtonAdd('#pager2', {
//                caption:"Excel导出",
//                title:"导出到Excel",
//                buttonicon:"ui-icon-newwin", //按钮icon
//                onClickButton:function () {    //执行操作
//                    var cols = getColProperties("listGrid");
//                    var showtitles = getColName("listGrid");
//                    var filename = $('#listGrid').jqGrid('getGridParam', 'caption');
//                    openWindow("导出至Excel", CONTEXT_NAME + "/commonPage/exportGrid.do?" + encodeURI("cols=" + cols + "&showtitles=" + showtitles + "&filename=" + filename));
//                },
//                position:"first"  //按钮位置
//            });
//        }
//    }
}

//导出excel时用到的列
function getColProperties(id) {
    var b = jQuery("#" + id)[0];
    var params = b.p.colModel;
    var cols = params[0].name;
    for (var i = 1; i < params.length; i++) {
        if (!params[i].hidden) {
            cols += "," + params[i].name;
        }
    }
    return cols;
}

//导出excel时用到的列的名称
function getColName(id) {
    var colNames = $('#' + id).jqGrid('getGridParam', 'colNames');
    return colNames;
}

//添加按钮hint
function addButtonHint() {
    $("input[type='button']").each(function () {
        this.title = this.value;
    });
}