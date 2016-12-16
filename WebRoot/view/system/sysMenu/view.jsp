<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover table-view">
        <tr class="tr_light">
            <td class="form_label">名称:</td>
            <td class="form_content">
                ${bean.name}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">编码:</td>
            <td class="form_content">
                ${bean.privilege}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">层次:</td>
            <td class="form_content">
                ${bean.menuLevel}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">链接地址:</td>
            <td class="form_content">
                <sys:toHtml>${bean.url}</sys:toHtml>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">事件:</td>
            <td class="form_content">
                ${bean.jsEvent}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">是否有效:</td>
            <td class="form_content">
                <c:choose><c:when test="${bean.isValid}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">参数:</td>
            <td class="form_content">
                ${bean.param}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">目标窗口:</td>
            <td class="form_content">
                ${bean.target}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">显示图标:</td>
            <td class="form_content">
                <c:if test="${bean.icon != ''}">
                    <span class="icon ${bean.icon}">&nbsp;</span>
                </c:if>
                &nbsp;
            </td>
        </tr>

    </table>
</div>