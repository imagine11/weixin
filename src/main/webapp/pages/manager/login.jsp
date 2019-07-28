<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="js/h-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="js/h-ui/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="js/h-ui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="js/h-ui/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
/** 看不清 换一个*/
	function validateChange(){
		
		var codeId = $("#imgcodeid");
		codeId.attr("src","validate.code?a="+Math.random());
	}
	/**验证表单是否为空*/
	function loginValidate(){
		var uname=$("#uname").val();
		if(uname.length<1){
			alert("用户名不能为空")
			return;
		}
		var upass=$("#upass").val();
		if(upass.length<1){
			alert("密码不能为空")
			return;
		}
		var vcode=$("#vcode").val();
		if(vcode.length<1){
			alert("验证码不能为空")
			return;
		}
		document.formLogin.submit();
	}
	
	
</script>

<title>后台登录-会议达人</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form name="formLogin" class="form form-horizontal" action="adminUser/login" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="uname" name="uname" type="text" placeholder="用户名" value="${param.uname }" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="upass" name="upass" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input id="vcode" name="vcode" class="input-text size-L" type="text" placeholder="验证码" style="width:150px;">
          <img id="imgcodeid" src="validate.code"> <a id="kanbuq" href="javascript:validateChange()">看不清，换一张</a> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label><div style="color:red;font-size:16px">
            	<c:if test="${param.tips==1 }">
            		验证码输入错误
            	</c:if>
            	<c:if test="${param.tips==4 }">
            		用户名或密码输入错误
            	</c:if>
            
            </div>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="button" onclick="loginValidate()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 微信会议开发 by weixin v1.0</div>
<script type="text/javascript" src="js/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/h-ui/static/h-ui/js/H-ui.min.js"></script>
<!--此乃百度统计代码，请自行删除-
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!--/此乃百度统计代码，请自行删除-->
</body>
</html>