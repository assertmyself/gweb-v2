<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:choose>
    <c:when test="${isHttp}">
        <iframe scrolling="auto" frameborder="0" src="${page}" style="width:100%;height:100%;" id="tab1"
                name="tab1"></iframe>
    </c:when>
    <c:otherwise>
        <iframe scrolling="auto" frameborder="0" src="${ctx}/${page}" style="width:100%;height:100%;" id="tab1"
                name="tab1"></iframe>
    </c:otherwise>
</c:choose>
