<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!-- multiselect -->
<!--<script src="/assets/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="/assets/js/jquery.mulitselector.js" type="text/javascript"></script> -->
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            //{name: "intervalTime", rule: "validate[required]"}
        ];
        validateInit(validateCondition, formId);
        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>&nbsp确定</button>";

		$("#" + openWindowId + " .modal-footer").html(btns);

        // 界面控件制约控制
        var type = ${bean.type};
        if(type=="1") {
            $('#beginTime_tr').toggle(true);
            $('#repeatTime_tr').toggle(false);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        } else if(type=="2") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        } else if(type=="3") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(true);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        }  else if(type=="4") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(true);
            $('#intervalTime_tr').toggle(false);
        }  else if(type=="5") {
            $('#beginTime_tr').toggle(true);
            $('#repeatTime_tr').toggle(false);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(true);
        }

        setTime();

    });

    function isSelect(selectPress) {
        var selectValue = selectPress.options[selectPress.selectedIndex].value; //显示value 下标0、1、2
        if (selectValue == "1") {
            $('#beginTime_tr').toggle(true);
            $('#repeatTime_tr').toggle(false);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        } else if (selectValue == "2") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        } else if (selectValue == "3") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(true);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(false);
        } else if (selectValue == "4") {
            $('#beginTime_tr').toggle(false);
            $('#repeatTime_tr').toggle(true);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(true);
            $('#intervalTime_tr').toggle(false);
        } else if (selectValue == "5") {
            $('#beginTime_tr').toggle(true);
            $('#repeatTime_tr').toggle(false);
            $('#selectWeek_tr').toggle(false);
            $('#selectDay_tr').toggle(false);
            $('#intervalTime_tr').toggle(true);
        }
    }

    function setTime() {
        var h = 0;
        var m = 0;
        var s = 0;
        var str = "";
        //str += "<div id=\"_contents\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:0px; top:0px; width:0px; height:0px; z-index:1; visibility:hidden\">";
        //str += "<div id=\"_contents\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777; position:absolute; left:0px; top:0px; width:0px; height:0px; z-index:1; visibility:visible\">";
        str += "\u65f6<select id=\"_hour\">";
        for (h = 0; h <= 9; h++) {
            str += "<option value=\"0" + h + "\">0" + h + "</option>";
        }
        for (h = 10; h <= 23; h++) {
            str += "<option value=\"" + h + "\">" + h + "</option>";
        }
        str += "</select> \u5206<select id=\"_minute\">";
        for (m = 0; m <= 9; m++) {
            str += "<option value=\"0" + m + "\">0" + m + "</option>";
        }
        for (m = 10; m <= 59; m++) {
            str += "<option value=\"" + m + "\">" + m + "</option>";
        }
        str += "</select> \u79d2<select id=\"_second\">";
        for (s = 0; s <= 9; s++) {
            str += "<option value=\"0" + s + "\">0" + s + "</option>";
        }
        for (s = 10; s <= 59; s++) {
            str += "<option value=\"" + s + "\">" + s + "</option>";
        }
        str += "</select> ";
        //str += "<input name=\"queding\" type=\"button\" onclick=\"_select()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" />";
        //str += "</div>";
        //document.writeln(str);

        document.getElementById("repeatTime_td").innerHTML=str;
    }

    function multi_selector(tt, title, data) {
        if ($("#mulitSelector").length != 0)
            return;

        var $input = $(tt);

        var ms_html;

        var settings =
        {
            title: title,
            data: data
        };


        function initialise(){
            initContent();
            initEvent();
        }

        function initEvent() {

            $("#ms_bt_ok").click(function() {
                var result = "";
                var obj = $("#allItems1 input:checked");
                for(var i=0; i<obj.length; i++)
                    //result += (i==0?"":"+")+obj[i].value.split("@")[1];
                    result += (i==0?"":",")+obj[i].value.split("@")[0];
                $input.val(result);
                ms_html.remove();
            });

            $("#ms_bt_clear").click(function() {
                ms_html.remove();
                $input.val("");
            });

            $("#ms_img_close").click(function() {
                ms_html.remove();
            });

        }

        function initContent() {

            var offset = $input.offset();
            var divtop = 1 + offset.top + document.getElementById($input.attr("id")).offsetHeight + 'px';
            var divleft = offset.left + 'px';
            var popmask = document.createElement('div');

            var html = [];

            html.push('<div id="mulitSelector" style="display:block; top:'+divtop+';left:'+divleft+'; position: absolute; z-index: 1999;">');
            html.push('    <div id="pslayer"  class="alert_div sech_div ms_width">');
            html.push('        <div class="box">');
            html.push('            <h1><span id="psHeader">'+settings.title+'</span><A href="javascript:void(0);" class="butn3" id="ms_img_close"></A></h1>');
            html.push('			<div class="blk">');
            html.push('				<div id="divSelecting" class="sech_layt">');
            html.push('					<h3>');
            html.push('						<span id="selectingHeader"></span><b class="btn_fst">');
            html.push('						<input id="ms_bt_ok" name="" type="button" value="确定" />');
            html.push('						<input id="ms_bt_clear" name="" type="button" value="清空" /></b>');
            html.push('					</h3>');
            html.push('				</div>');
            html.push('				<div class="sech_layb"> ');
            html.push('					<h2 id="subHeader1"></h2>');
            html.push('					<ol id="allItems1">');

            var dataArray = settings.data;
            if (dataArray != null){
                var len = dataArray.length;
                for(var i=0; i<len; i++){
                    var d = dataArray[i];
                    var status = findStatus(d.name);
                    html.push('						<li id=$'+d.id+' name=100 class="nonelay">');
                    html.push('							<a href="###">');
                    html.push('							<label for="'+d.id+'">');
                    html.push('							<input id="'+d.id+'" type="checkbox" '+status+' value="'+(d.id+ '@'+ d.name)+'" />'+d.name+'</label>');
                    html.push('							</a>');
                    html.push('						</li>');
                }
            }

            html.push('					</ol>');
            html.push('				</div>');
            html.push('			</div>');
            html.push('		</div>');
            html.push('   </div>');
            html.push('</div>');

            ms_html = $(html.join("")).appendTo('body');
        }

        function findStatus(d){

            var content = $input.val();
            if ((content || "").replace( /^\s+|\s+$/g, "" ) == "") {
                return "";
            }

            var obj = content.split("+");
            for(var i=0; i<obj.length; i++){
                if(obj[i] == d){
                    return "checked";
                }
            }

        }

        initialise();
    }

    function setSelectWeek(objName) {
        var data =
                [
                    {id: "1",name: "星期一"},
                    {id: "2",name: "星期二"},
                    {id: "3",name: "星期三"},
                    {id: "4",name: "星期四"},
                    {id: "5",name: "星期五"},
                    {id: "6",name: "星期六"},
                    {id: "7",name: "星期日"}
                ];

        var tt = document.getElementsByName(objName);
        multi_selector(tt,"请选择星期", data);
    }

    function setSelectDay(objName) {
        var data =
                [
                    {id: "1",name: "1"},
                    {id: "2",name: "2"},
                    {id: "3",name: "3"},
                    {id: "4",name: "4"},
                    {id: "5",name: "5"},
                    {id: "6",name: "6"},
                    {id: "7",name: "7"},
                    {id: "8",name: "8"},
                    {id: "9",name: "9"},
                    {id: "10",name: "10"},
                    {id: "11",name: "11"},
                    {id: "12",name: "12"},
                    {id: "13",name: "13"},
                    {id: "14",name: "14"},
                    {id: "15",name: "15"},
                    {id: "16",name: "16"},
                    {id: "17",name: "17"},
                    {id: "18",name: "18"},
                    {id: "19",name: "19"},
                    {id: "20",name: "20"},
                    {id: "21",name: "21"},
                    {id: "22",name: "22"},
                    {id: "23",name: "23"},
                    {id: "24",name: "24"},
                    {id: "25",name: "25"},
                    {id: "26",name: "26"},
                    {id: "27",name: "27"},
                    {id: "28",name: "28"},
                    {id: "29",name: "29"},
                    {id: "30",name: "30"},
                    {id: "31",name: "31"}
                ];

        var tt = document.getElementsByName(objName);
        multi_selector(tt,"请选择日期", data);
    }


    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        disableBtn(btn);

        $('#repeatTime').value = document.getElementById("_hour").value + ":" + document.getElementById("_minute").value + ":" + document.getElementById("_second").value;

        //提交表单
        saveAjaxData("${ctx}/timePlan/save.do", formId);
    }
</script>
<form:form commandName="bean">
    <form:hidden path="id"/>
    <form:hidden path="ownerType"/>

    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="form_table">
            <tr class="tr_light">
                <td class="form_label">定时任务类型:</td>
                <td class="form_content">
                    <form:select path="type" onchange="isSelect(this);" id="type">
                        <form:option value="1">一次性任务</form:option>
                        <form:option value="2">每天重复</form:option>
                        <form:option value="3">每周重复</form:option>
                        <form:option value="4">每月重复</form:option>
                        <form:option value="5">每隔分钟重复</form:option>
                    </form:select>
                </td>
            </tr>
            <tr class="tr_dark" id="beginTime_tr">
                <td class="form_label">起始时间(一次性/每隔分钟重复):</td>
                <td class="form_content">
                    <form:input path="beginTime" cssClass="input_datetime" readonly="true"/>
                    <input type="button" class="button_calendar" value=" " onClick="calendar('beginTime', 'all');">
                </td>
            </tr>
            <tr class="tr_light" id="repeatTime_tr">
                <td class="form_label">定时时间(按天/周/月重复):</td>
                <td class="form_content" id="repeatTime_td">
                    <form:hidden path="repeatTime"/>
                    <!--<form:input path="repeatTime" cssClass="input_datetime" readonly="true"/>
                    <input type="button" class="button_calendar" value=" " onClick="_SetTime(this)"/>-->
                </td>
            </tr>
            <tr class="tr_dark" id="selectWeek_tr">
                <td class="form_label">选择星期:</td>
                <td class="form_content">
                    <form:input path="selectWeek" cssClass="input_datetime" readonly="true"/>
                    <input type="button" class="button_calendar" value=" " onClick="setSelectWeek('selectWeek')"/>
                </td>
            </tr>
            <tr class="tr_light" id="selectDay_tr">
                <td class="form_label">选择天:</td>
                <td class="form_content">
                    <form:input path="selectDay" cssClass="input_datetime" readonly="true"/>
                    <input type="button" class="button_calendar" value=" " onClick="setSelectDay('selectDay')"/>
                </td>
            </tr>
            <tr class="tr_dark" id="intervalTime_tr">
                <td class="form_label">间隔分钟数:</td>
                <td class="form_content">
                    <form:input path="intervalTime" cssClass="input_text"/>
                </td>
            </tr>

            <tr class="tr_light">
                <td class="form_label">状态:</td>
                <td class="form_content">
                    <form:radiobutton path="state" value="true"/>是
                    <form:radiobutton path="state" value="false"/>否
                </td>
            </tr>

        </table>
    </div>
</form:form>