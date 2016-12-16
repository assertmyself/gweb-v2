<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="no_scrollbar">
    <div class="form_div">
        <table cellpadding="0" cellspacing="0" class="table table-hover table-view" width="100%">
            <tr class="tr_light">
                <td class="form_label">
                  用户名：
                </td>
                <td class="form_content">
                    ${userName}
                </td>
            </tr>
            <tr class="tr_dark">
                <td class="form_label">
                  显示名称：
                </td>
                <td class="form_content">
                    ${realName}
                </td>
            </tr>
            <tr class="tr_light">
                <td class="form_label">
                   用户IP：
                </td>
                <td class="form_content">
                    ${ipAddress}
                </td>
            </tr>
		  	<tr class="tr_dark">
                <td class="form_label">
                  所属单位：
                </td>
                <td class="form_content">
                    ${dept}
                </td>
            </tr>
      		<tr class="tr_light">
                <td class="form_label">
                   登录时间：
                </td>
                <td class="form_content">
                    ${loginTime}
                </td>
            </tr>
        	 <tr class="tr_dark">
                <td class="form_label">
                  在线时间：
                </td>
                <td class="form_content">
                    ${onlineTimeMinute}分钟
                </td>
            </tr>
        </table>
    </div>
</div>