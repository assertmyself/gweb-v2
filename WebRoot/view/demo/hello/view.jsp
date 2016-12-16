<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="form_div">
    <table cellpadding="0" cellspacing="0" class="table table-hover">
        <tr class="tr_light">
                <td >名称：</td>
                <td >&nbsp;
                    ${bean.name}
                </td>
            </tr>

            <tr class="tr_dark">
                <td >年龄：</td>
                <td >&nbsp;
					${bean.age}
                </td>
            </tr>
            <tr class="tr_light">
                <td >地址：</td>
                <td >&nbsp;
                    ${bean.address}
                </td>
            </tr>

            <tr class="tr_dark">
                <td >描述：</td>
                <td >&nbsp;
					${bean.description}
                </td>
            </tr>
			<tr class="tr_light">
                <td >创建时间：</td>
                <td >&nbsp;
                    ${bean.createTime}
                </td>
            </tr>
           
	</table>
</div>