<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover">
        <tr class="tr_light">
            <td>编码：</td>
            <td>
                ${bean.areaCode}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td>名称：</td>
            <td>
                ${bean.areaName}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td>系统定义：</td>
            <td>
                <c:choose><c:when test="${bean.isReserved}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td>层次：</td>
            <td>
                ${bean.layer}
                &nbsp;
            </td>

        </tr>
        <tr class="tr_light">
            <td>是否叶子节点：</td>
            <td>
                <c:choose><c:when test="${bean.isLeaf}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
                &nbsp;
            </td>
        </tr>

        <tr class="tr_dark">
            <td>备注：</td>
            <td>
                <sys:toHtml>${bean.description}</sys:toHtml>
                &nbsp;
            </td>
        </tr>

    </table>
</div>