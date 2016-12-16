<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";

    $(function(){
        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" >取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\">确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });
    //保存操作
    function save(btn) {
        //加入其他业务判断
        var ss = $(":checkbox");
        var check = false;
        for (var i = 0; i < ss.length; i++) {
            if (ss[i].checked) {
                check = true;
                break;
            }
        }

        if (!check) {
            showInfoMsg('请选择要添加的按钮权限！');
            return;
        }

        //提交表单
        saveAjaxData("${ctx}/sysPrivilege/batchInputSave.do", formId);
    }
</script>
<form:form commandName="bean">
    <form:hidden path="id"/>
    <form:hidden path="parent" value="${bean.parent.id}"/>

    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="form_table">
            <tr class="tr_light">
                <td class="form_label">添加【${bean.parent.name}】的按钮权限:</td>
                <td class="form_content">
                    <input type="checkbox" name="batchType" value="edit"/> 编辑 <br>
                    <input type="checkbox" name="batchType" value="audit"/> 审核
                </td>
            </tr>


        </table>
    </div>
</form:form>