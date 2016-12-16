<%@ attribute name="detailId" %>
<%@ attribute name="detailCode" %>
<%@ tag pageEncoding="UTF-8" %>
<%--
 author: Wang Kaifeng

 用法：
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
 2、在需要系统代码的地方使用如下代码：
    <sys:code code="sys_unit_character" name="character" id="character" type="select"/>
    code一般使用com.hc.ipromis.Constants.SYS_UNIT_CHARACTER方式
    sysCodeDetailId一般在修改表单时使用，用于选中代码明细
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
        %>
<%@ tag import="com.gbcom.system.domain.SysCodeDetail"
        %>
<%@ tag import="com.gbcom.system.manager.SysCodeManager"
        %>
<%@ tag import="flexjson.JSONSerializer"
        %>
<%@ tag import="org.apache.commons.lang.StringUtils"
        %>
<%@ tag import="org.springframework.context.ApplicationContext"
        %>
<%@ tag import="org.springframework.web.context.support.WebApplicationContextUtils"
        %>
<%@ tag import="java.util.HashMap"
        %>
<%@ tag import="java.util.List"
        %>
<%@ tag import="java.util.Map"

        %>
<%@ attribute name="code" type="java.lang.String" required="true" description="系统代码code"
        %>
<%@ attribute name="sysCodeDetailId" type="java.lang.Long" description="系统代码明细ID"
        %>
<%@ attribute name="isAlowedNull" type="java.lang.Boolean" description="是否允许为空，将出现空选项"
        %>
<%@ attribute name="clazz" type="java.lang.String" description="类样式"
        %>
<%@ attribute name="style" type="java.lang.String" description="自定义样式"
        %>
<%@ attribute name="name" type="java.lang.String" description="表单控件的名称"
        %>
<%@ attribute name="id" type="java.lang.String" description="表单控件的ID，默认为名称"
        %>
<%@ attribute name="defaultName" type="java.lang.String" description="默认的被选中项"
        %>
<%@ attribute name="type" type="java.lang.String" required="true" description="表单控件类型，如select，list等，目前只支持select"
        %>
<%@ attribute name="onChange" type="java.lang.String" description="属性更改触发事件"
        %>
<%@ attribute name="disabled" type="java.lang.String" description="是否灰暗显示，不可修改" %>
<%

    ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession(true).getServletContext());
    SysCodeManager sysCodeManager = (SysCodeManager) applicationContext.getBean("sysCodeManager");

    List<SysCodeDetail> sysCodeDetails = sysCodeManager.getCodeListByCode(code);
    StringBuilder ret = new StringBuilder();
    if (StringUtils.isNotEmpty(type)) {
        if ("select".equals(type)) {
            ret.append("<select");
            if (StringUtils.isEmpty(id)) {
                id = name;
            }
            ret.append(" id=\"" + id + "\" name=\"" + name + "\"" + " onchange=\"" + StringUtils.defaultString(onChange) + "\"");
            if (StringUtils.isNotEmpty(disabled)) {
                ret.append("\" disabled=\"" + disabled + "\"");
            }
            if (StringUtils.isNotEmpty(style)) {
                ret.append(" style='").append(style).append("'");
            }
            if (StringUtils.isEmpty(clazz)) {
                ret.append(" class='form_select'>");
            } else {
                ret.append(" class='").append(clazz).append("'>");
            }

            if (isAlowedNull != null && isAlowedNull) {
                ret.append("<option value=\"\">请选择</option>");
            }
            for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
                if (sysCodeDetail.getIsValid()) {
                    ret.append("<option value=\"" + sysCodeDetail.getId() + "\"");
                    boolean isSelect = ((sysCodeDetailId == null || sysCodeDetailId == 0) && (defaultName != null && defaultName.equals(sysCodeDetail.getName())));
                    isSelect = isSelect || sysCodeDetail.getId().equals(sysCodeDetailId);
                    if (isSelect) {
                        ret.append("selected=\"selected\"");
                    }
                    ret.append(">" + sysCodeDetail.getName() + "</option>");
                }
            }
            ret.append("</select>");
        } else if ("json".equals(type)) {
            Map map = new HashMap();
            if (isAlowedNull != null && isAlowedNull) {
                map.put("", "请选择");
            }
            for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
                if (sysCodeDetail.getIsValid()) {
                    map.put(sysCodeDetail.getId(), sysCodeDetail.getName());
                }
            }
            ret.append(new JSONSerializer().serialize(map));
        }
    }
    out.print(ret.toString());
%>