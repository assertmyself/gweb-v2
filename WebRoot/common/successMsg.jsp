<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/common/header.jsp" %>
    <script language="javascript">
        function doReturn() {
            init();
            var nextUrl = "${msg.url}";
            var isRefreshOpener = ${msg.refreshOpener};
            var isNeedClose = ${msg.needClose};
            var gridParam = '${gridParam}';
            var loadOpener = ${msg.loadOpener};
            var returnValue = "${msg.returnValue}";
            if (isRefreshOpener) {
                opener.window.location.reload();
            }
            if (isNeedClose) {
                self.close();
                if (parent.window) {
                    parent.doRefreshIframGrid();
                    parent.closeWindow();
                }
                return;
            }
            if (nextUrl != "") {
                if (loadOpener) {
                    if (parent.window) {
                        parent.window.returnValue = returnValue;
                    }
                    if (returnValue.indexOf("window") >= 0) {
                        eval(returnValue);
                    }
                    window.close();
                } else if (nextUrl.indexOf("?") >= 0) {
                    window.location = "${ctx}/" + nextUrl + "&gridParam=" + gridParam;
                } else {
                    window.location = "${ctx}/" + nextUrl + "?gridParam=" + gridParam;
                }
            }
        }
        function init() {
            var customEvent = "${msg.customEvent}";
            if (customEvent != "") {
                eval(customEvent);
            }
        }

        //启动流程
        function startBizFlow() {
            var startUrl = "${ctx}/workflow/bpmProcessInstance/startBizFlow.do?bizObject=${bizObject}&bizObjectId=${bizObjectId}";
            $.get(startUrl, {}, function (value) {
                var msg = getElementValue(value, "msg");
                if (msg != "") {
                    alert(msg);
                    var taskId = getElementValue(value, "taskId");
                    if (taskId != "") {
                        window.location = "${ctx}/workflow/bpmProcessInstance/input.html?bpmTaskInstanceId=" + taskId;
                    } else {
                        doReturn();
                    }
                }
            });
        }
    </script>
</head>

<body>
<br>
<table width="450" height="300" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td background="${ctx}/skin/images/success.jpg">
            <table width="330" height="230" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2" height="40"></td>
                </tr>
                <tr>
                    <td width="140">&nbsp;</td>
                    <td height="130" style="word-break:break-all" class="msg_hints">
                        ${msg.msg}&nbsp;
                    </td>
                </tr>
                <c:if test="${msg.url != null && msg.url != ''}">
                    <tr>
                        <td height="30" align="center">&nbsp; </td>
                        <td>
                            <c:choose>
                                <c:when test="${startBizFlow == true}">
                                    <input type="button" value="启动流程" onClick="startBizFlow();this.disabled=true;"
                                           class="button_go_long">
                                    <input type="button" value="跳过" onClick="doReturn();" class="button_go_long">
                                </c:when>
                                <c:otherwise>
                                    <input type="button" value="确定" onClick="doReturn();this.disabled = true;"
                                           class="button_confirm">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="2" height="20"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
<c:choose>
    <c:when test="${startBizFlow == true}">
    </c:when>
    <c:otherwise>
        <script>
            var isAutoJump = ${msg.autoJump};
            if (isAutoJump) {
                //5秒后自动跳转
                setTimeout("doReturn()", 5000);
            }
        </script>
    </c:otherwise>
</c:choose>
</html>