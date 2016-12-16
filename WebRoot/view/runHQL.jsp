<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@include file="/common/header.jsp" %>
<html>
<head>
    <title>run HQL</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Run HQL" content="0;URL=${ctx}/${url}"/>

</head>

<body>
<form action="${ctx}/dXI/getMultiObjects.do">
    HQL: <textarea id="hql" name="hql" cols="10" rows="5"></textarea>
    <input type="button" id="bnt" value="submit"/>
</form>

<script type="text/javascript">
    $(function () {
        $("#bnt").click(function (event) {
            $("#result").html("");
            var params = $("#params").val();
            $.ajax({ type: "GET", url: '${ctx}/dXI/runHql.do?hql=' + $("#hql").val(), data: {}, contentType: "text/plain", dataType: "text", success: function (text) {
//       var dataArray=eval("("+text+")");
//      for(i=0;i<dataArray.length;i++){
//          var curtr="<tr>";
//         curtr=curtr+ "<td>"+  JSON.stringify(dataArray[i])+"</td>";
//          curtr+="</tr>"
//          $("#result").append(curtr);
//      }
                $("#result").append(text);
            }});
            return false;
        });
    });
</script>


<table id="result">

</table>
</body>
</html>