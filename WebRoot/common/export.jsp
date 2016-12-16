<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%--<form:form commandName="bean">--%>
<%--<form:hidden path="id" id="id"/>--%>
<script type="text/javascript">

    function doclick(btn) {
        var va = btn.value;
        if (va == "all") {
            $("input:checkbox[name=checkbox]").attr("checked", true);
        }
        if (va == "no") {
            $("input:checkbox[name=checkbox]").attr("checked", false);
        }
        if (va == "un") {
            $("input:checkbox[name=checkbox]").each(function (i, item) {
                $(item).attr("checked", !$(item).attr("checked"));
            })
        }
    }

    function toExcel() {
        var titiles = "";
        var cols = "";
        var filename = document.getElementById("fileName").value;
        var index = "";
        $("input:checkbox[name=checkbox]:checked").each(function () {
            cols += $(this).attr("value") + ",";
            titiles += $(this).parents(".tr_light").find(".form_label").text() + ",";
            index += $(this).parents(".tr_light").find(".index").val() + ",";
        });
        titiles = titiles.substring(0, titiles.length - 1);
        cols = cols.substring(0, cols.length - 1);
        index = index.substring(0, cols.length - 1);
        $("#listGrid").excelExportAdvance("${ctx}/commonPage/exportGridExcute.do?" + encodeURI("cols=" + cols + "&showtitles=" + titiles + "&filename=" + filename + "&index=" + index));
        closeWindow();
    }

</script>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="form_table">
        <input type="hidden" value="${filename}" id="fileName"/>
        <tr class="tr_light">
            <td width="100%" colspan="2">请选择需要导出的列</td>
        </tr>
        <tr class="tr_dark">
            <td class="form_label"></td>
            <td class="form_content">
                <input type="radio" name="radio" value="all" onclick="doclick(this)" checked=checked/>全选
                <input type="radio" name="radio" value="no" onclick="doclick(this)"/>全不选
                <input type="radio" name="radio" value="un" onclick="doclick(this)"/>反选
            </td>
        </tr>
        <c:forEach items="${bean}" var="item" varStatus="var">
            <tr class="tr_light">
                <td class="form_label">${item.titles}</td>
                <td class="form_content"><input type="checkbox" name="checkbox" value="${item.cols}" checked=checked/>
                    <input type="hidden" value="${var.index}" class="index"/>
                </td>
            </tr>
        </c:forEach>
        <tr class="tr_button">
            <td class="form_label"></td>
            <td class="form_content" style="width:100%">
                <c:if test="${button=='on'}"><input type="button" value="确定" class="button_confirm"
                                                    onclick="toExcel()"></c:if>&nbsp;
                <input type="button" value="取消" class="button_cancel" onclick="closeWindow()">
            </td>
        </tr>
    </table>
</div>
<%--</form:form>--%>