<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="no_scrollbar">
    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="table table-hover table-view">
            <tr class="tr_light">
                <td class="form_label">编号:</td>
                <td class="form_content">
                    ${bean.code}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">姓名:</td>
                <td class="form_content">
                    ${bean.name}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">所属单位（部门）:</td>
                <td class="form_content">
                    ${bean.company.name}&nbsp;（${bean.dept.name}）
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">身份证号:</td>
                <td class="form_content">
                    ${bean.card}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">年龄:</td>
                <td class="form_content">
                    ${bean.age}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">性别:</td>
                <td class="form_content">
                    <c:choose><c:when test="${bean.sex}">男</c:when><c:otherwise>女</c:otherwise></c:choose>
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">出生年月:</td>
                <td class="form_content">
                    <fmt:formatDate value="${bean.bornDate}" pattern="yyyy-MM-dd"/>
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">籍贯:</td>
                <td class="form_content">
                    ${bean.bornPlace}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">移动电话:</td>
                <td class="form_content">
                    ${bean.mobile}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">固定电话:</td>
                <td class="form_content">
                    ${bean.officeTel}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">传真:</td>
                <td class="form_content">
                    ${bean.faxTel}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">邮件:</td>
                <td class="form_content">
                    ${bean.email}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">邮政编码:</td>
                <td class="form_content">
                    ${bean.zipcode}
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">工作年限:</td>
                <td class="form_content">
                    ${bean.workYear}
                    &nbsp;
                </td>
            </tr>
            <%--<tr class="tr_light">--%>
            <%--<td class="form_label">MSN_CODE:</td>--%>
            <%--<td class="form_content">--%>
            <%--${bean.msnCode}--%>
            <%--&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="tr_dark">--%>
            <%--<td class="form_label">QQ_CODE:</td>--%>
            <%--<td class="form_content">--%>
            <%--${bean.qqCode}--%>
            <%--&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <tr class="tr_light">
                <td class="form_label">备注:</td>
                <td class="form_content">
                    <sys:toHtml>${bean.memo}</sys:toHtml>
                    &nbsp;
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">部门排序号:</td>
                <td class="form_content">
                    ${bean.personDept.orderNo}&nbsp;
                </td>
            </tr>

        </table>
    </div>
</div>
