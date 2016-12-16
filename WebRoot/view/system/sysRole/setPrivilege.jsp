<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {

        //页面验证初始化
        var validateCondition = [

        ];
        validateInit(validateCondition, formId);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);



		//icheck 只能注册回调，写在 input上的onclick 不生效
        $("#selectAll").on("ifChecked",function(event){
            $('input[name=sysPrivilegeId]').iCheck('check'); 
        });
        $("#selectAll").on("ifUnchecked",function(event){
            $('input[name=sysPrivilegeId]').iCheck('uncheck'); 
        });
        
        $("input[name=sysPrivilegeId]").on("ifChecked",function(event){
            var treeid = $(this).attr("treeid");
            $("input[name=sysPrivilegeId]").each(function(i,obj){
	        	 if (($(obj).attr("treeid").length >= treeid.length) && ($(obj).attr("treeid").substr(0, treeid.length) == treeid)) {
	        		 $(obj).iCheck('check'); 
	             }
            });
        });
        $("input[name=sysPrivilegeId]").on("ifUnchecked",function(event){
            var treeid = $(this).attr("treeid");
            $("input[name=sysPrivilegeId]").each(function(i,obj){
	        	 if (($(obj).attr("treeid").length >= treeid.length) && ($(obj).attr("treeid").substr(0, treeid.length) == treeid)) {
	        		 $(obj).iCheck('uncheck'); 
	             }
            });
        });
    });

    //保存操作
    function save(btn) {

        //提交表单
        disableBtn(btn);
        saveAjaxData("${ctx}/sysRole/savePrivilege.do", formId);
    }

    /**
     *如果选择父项是子项会自动被选上。
     * @param startTreeId .
     * @param checkBox .
     */
    function checkChildren(startTreeId, checkBox) {
        alert("xxxxx");
        var startTreeIdLength = startTreeId.length;
        var treeIds = document.getElementsByName("treeId");
        var sysPrivilegeIds = document.getElementsByName("sysPrivilegeId");
        var length = sysPrivilegeIds.length;
        var checkStatus = checkBox.checked;
        for (var i = 0; i < length; i++) {
            var treeId = treeIds[i].value;
            if ((treeId.length >= startTreeIdLength) && (treeId.substr(0, startTreeIdLength) == startTreeId)) {
                sysPrivilegeIds[i].checked = checkStatus;
            }
        }
    }

    //选择全选时所有选项都被自动选上。
    /*
    function checkAll() {
        
        var selectStatus = document.getElementById("selectAll").checked;
        var sysPrivilegeIds = document.getElementsByName("sysPrivilegeId");
        var length = sysPrivilegeIds.length;
        for (var i = 0; i < length; i++) {
            sysPrivilegeIds[i].checked = selectStatus;
        }
    }
    */

</script>
<form:form commandName="bean">
    <form:hidden path="id"/>
    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="form_table">
            <tr class="tr_dark" align="center">
                <td colspan="2" align="center">权限选择（全选/全不选<input type="checkbox" id="selectAll" onclick="checkAll()">）
                </td>
            </tr>

            <tr class="tr_light">
                <td width="38%" nowrap align="right">&nbsp;</td>
                <td width="62%" nowrap align="left">
                    <c:forEach items="${list}" var="item">
                        <div>
                                ${item.space}
                            <input type="checkbox" name="sysPrivilegeId" treeid="${item.sysPrivilege.treeId}"
                                   onclick="checkChildren('${item.sysPrivilege.treeId}',this)"
                                   value="${item.sysPrivilege.id}"
                                   <c:if test="${item.isChecked}">checked</c:if> align="absmiddle">
                            <c:choose>
                                <c:when test="${item.sysPrivilege.type.id == buttonType}">
                                    <span style="font-size:12px;">${item.sysPrivilege.name}</span>
                                </c:when>
                                <c:otherwise>
                                    <strong style="font-size:13px;">${item.sysPrivilege.name}</strong>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="treeId" value="${item.sysPrivilege.treeId}">
                        </div>
                    </c:forEach>
                </td>
            </tr>


        </table>
    </div>
</form:form>