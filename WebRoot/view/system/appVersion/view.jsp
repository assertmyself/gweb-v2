<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="form_table">
        <tr class="tr_light">
            <td>版本OS类型：</td>
            <td>&nbsp;
                <c:choose><c:when test="${bean.appOsType == 1}">Android</c:when><c:otherwise>IOS</c:otherwise></c:choose>
        </tr>
        <tr class="tr_dark">
            <td>版本号：</td>
            <td>&nbsp;
                ${bean.appVersion}</td>
        </tr>
        <tr class="tr_light">
            <td>版本文件名称：</td>
            <td>&nbsp;
                ${bean.appFileName}</td>
        </tr>
        <tr class="tr_dark">
            <td>版本文件路径：</td>
            <td>&nbsp;
                ${bean.appFilePath}</td>
        </tr>
        <tr class="tr_light">
            <td>版本更新内容：</td>
            <td>&nbsp;
                ${bean.updateContent}</td>
        </tr>

    </table>
</div>