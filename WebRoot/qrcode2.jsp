<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<script type="text/javascript">
    var browser={
        versions:function(){
            var u = navigator.userAgent, app = navigator.appVersion;
            return { //移动终端浏览器版本信息
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
        }(),
        language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
    if(browser.versions.ios==true){
        //window.location.href = "itms-services://?action=download-manifest&url=https://www.lianlianbox.com/appdownload/llapp/2014-05-15/ios_2.0.0.plist";
        // 准备工作：
        // 1.申请一级域名、二级域名
        // 2.根据二级域名，向CA申请https服务器证书。 是否一定要是苹果CA，还是其他第三方经过WebTrust认证过的CA都可以？确认下。
        window.location.href = "itms-services://?action=download-manifest&url=https://cloud.gsine.com.cn/gsapp/ios/smarthome_1.0.0.plist";
    }else{
        //window.location.href = "http://appdown.lianlian.com:8000/llapp/2014-05-15/android_2.0.0.apk";
        //window.location.href = "http://10.60.105.162:8080/gsapp/android/smarthome_android.apk";
        window.location.href = "http://cloud.gsine.com.cn/gsapp/android/smarthome_1.0.0.apk";
    }

</script>
</body>
</html>