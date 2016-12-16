<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover table-view">
        <tr class="tr_light">
            <td class="form_label">角色编码:</td>
            <td class="form_content">
                ${bean.code}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">角色名称:</td>
            <td class="form_content">
                ${bean.roleName}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">描述:</td>
            <td class="form_content">
                <sys:toHtml>${bean.description}</sys:toHtml>
                &nbsp;
            </td>
        </tr>

    </table>
</div>