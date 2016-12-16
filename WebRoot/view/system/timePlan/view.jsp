<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover">
        <tr class="tr_light">
            <td >所属类型：</td>
            <td >&nbsp;
                <c:choose><c:when test="${bean.ownerType == 1}">数据库备份</c:when><c:otherwise>其他</c:otherwise></c:choose>
        </tr>
        <tr class="tr_dark">
            <td >定时任务类型：</td>
            <td >&nbsp;
                <c:choose>
                    <c:when test="${bean.type == 1}">一次性任务</c:when>
                    <c:when test="${bean.type == 2}">每天重复</c:when>
                    <c:when test="${bean.type == 3}">每周重复</c:when>
                    <c:when test="${bean.type == 4}">每月重复</c:when>
                    <c:when test="${bean.type == 5}">每隔分钟重复</c:when>
                    <c:otherwise>其他</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="tr_light">
            <td >起始时间：</td>
            <td >&nbsp;
                ${bean.beginTime}</td>
        </tr>
        <tr class="tr_dark">
            <td >定时时间：</td>
            <td >&nbsp;
                ${bean.repeatTime}</td>
        </tr>
        <tr class="tr_light">
            <td >选择星期：</td>
            <td >&nbsp;
                ${bean.selectWeek}</td>
        </tr>
        <tr class="tr_dark">
            <td >选择天：</td>
            <td >&nbsp;
                ${bean.selectDay}</td>
        </tr>
        <tr class="tr_light">
            <td >间隔分钟数：</td>
            <td >&nbsp;
                ${bean.intervalTime}</td>
        </tr>
    </table>
</div>