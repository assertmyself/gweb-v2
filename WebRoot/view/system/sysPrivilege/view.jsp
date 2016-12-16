<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover table-view">
        <tr class="tr_light">
            <td>显示名称：</td>
			<td>
				${bean.code}
			</td>
		</tr>
        <tr class="tr_dark">
            <td>权限名称：</td>
            <td>
                ${bean.name}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td>权限类型：</td>
            <td>
                ${bean.type.name}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td>TAG：</td>
            <td>
                ${bean.tag}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td>页面地址：</td>
            <td class="tr_dark">
                ${bean.url}
                &nbsp;
            </td>
        </tr>
        <tr class="tr_dark">
            <td>定义：</td>
            <td>
                <sys:toHtml>${bean.definition}</sys:toHtml>
                &nbsp;
            </td>
        </tr>
        <tr class="tr_light">
            <td>描述：</td>
            <td>
                <sys:toHtml>${bean.description}</sys:toHtml>
                &nbsp;
            </td>
        </tr>

    </table>
</div>

