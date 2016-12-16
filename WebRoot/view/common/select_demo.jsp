<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选择树demo</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">

    </script>
</head>

<body>
<div>

    <select name="selectId" id="selectId" style="width:180px" size="5"></select>
    <input type="text" name="inputName" id="inputName"/>
    <input type="hidden" name="inputId" id="inputId"/>
</div>
<div>
    选择单位：
    <input type="button" value=" " class="button_select" onclick="selectSysDept('inputId','inputName')" title="单选">
    <input type="button" value=" " class="button_select_add"
           onclick="multiSelectSysDept('selectId', 'inputId','inputName')" title="多选">
    <input type="button" value=" " class="button_select_remove"
           onclick="removeMultiSelectOpt('selectId', 'inputId','inputName')" title="移除选中节点">
</div>
<div>
    选择人员：
    <input type="button" value=" " class="button_select" onclick="selectSysPerson('inputId','inputName')" title="单选">
    <%--<input type="button" value=" " class="button_select_add" onclick="multiSelectSysDept('selectId', 'inputId','inputName')" title="多选">--%>
    <%--<input type="button" value=" " class="button_select_remove" onclick="removeMultiSelectOpt('selectId', 'inputId','inputName')" title="移除选中节点">--%>
</div>

<div>
    文档上传测试：
    ${uploadButton}
</div>
</body>
</html>