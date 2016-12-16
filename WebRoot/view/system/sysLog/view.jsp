<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="form_table">
        <tr class="tr_dark">
            <td class="form_label">用户名:</td>
            <td class="form_content">
                ${bean.user.loginName}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">IP地址:</td>
            <td class="form_content">
                ${bean.ipAddress}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">登陆时间:</td>
            <td class="form_content">
                <fmt:formatDate value="${bean.enterTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">退出时间:</td>
            <td class="form_content">
                <fmt:formatDate value="${bean.outTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">访问页面:</td>
            <td class="form_content">
                ${bean.pageUrl}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td class="form_label">浏览器版本:</td>
            <td class="form_content">
                ${bean.ieVersion}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label">SESSIONID:</td>
            <td class="form_content">
                ${bean.sessionid}
                &nbsp;
            </td>
        </tr>

    </table>
</div>