<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="no_scrollbar">
    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="table table-hover">
            <tr class="tr_light">
                <td>单位代码:</td>
                <td>
                    ${bean.code}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td>单位名称:</td>
                <td>
                    ${bean.name}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td>简称:</td>
                <td>
                    ${bean.shortName}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td>机构代码证:</td>
                <td>
                    ${bean.organizationCode}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td>联系人:</td>
                <td>
                    ${bean.contacter}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td>联系电话:</td>
                <td>
                    ${bean.telephone}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td>注册地址:</td>
                <td>
                    ${bean.address}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td>是否单位:</td>
                <td>
                    <c:choose><c:when test="${bean.isCompany}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td>是否有效:</td>
                <td>
                    <c:choose><c:when test="${bean.isValid}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td>排序:</td>
                <td>
                    ${bean.orderNo}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td>备注:</td>
                <td>
                    <sys:toHtml>${bean.memo}</sys:toHtml>
                    &nbsp;
                </td>
            </tr>

        </table>
    </div>
</div>