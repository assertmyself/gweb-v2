<%@ tag pageEncoding="UTF-8" %>
<%--
   用来div域伸缩
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
  2、 在需要翻译系统代码列表的地方使用如下代码：
    <sys:region target="detail" title="查询">content</sys:region>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="id" type="java.lang.String" required="true" description="对应域目标的id" %>
<%@ attribute name="title" type="java.lang.String" required="true" description="区域说明" %>
<jsp:doBody var="html"/>

<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all">
    <div>
        <div class="ui-jqgrid-titlebar ui-widget-header ui-corner-top ui-helper-clearfix">
            <a class="ui-jqgrid-titlebar-close HeaderButton" href="javascript:void(0)" role="link" style="right: 0px;"
               onclick="javascript:toggleDiv('${id}');">
                <span class="ui-icon ui-icon-circle-triangle-n"></span>
            </a>
            <span class="ui-jqgrid-title">${title}</span>
        </div>
    </div>
    <div id="${id}">
        ${html}
    </div>
</div>

