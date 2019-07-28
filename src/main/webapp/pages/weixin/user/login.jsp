<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
             <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
		<script src="js/jquerymobile_popup.js"></script>
<script type="text/javascript">
function login(){
	//非空验证
	var email = $("#email").val();
	if(email.length<1){
		openPopup('邮箱不能为空','提示',undefined,true,undefined,'error','cn');
		return;
	}
	var wid = $("#wid").val();
	$.ajax({
		type:"post",
		url:"userWeixin/login",
		data:{"email":email,"wid":wid},
		success:function(msg){
			if("1"==msg){
				openPopup('您输入的邮箱不存在','提示',undefined,true,undefined,'error','cn');
			}else if("2"==msg){
				openPopup('邮箱已被人绑定,如有疑问,请联系管理员','提示',undefined,true,undefined,'error','cn');
			}else {
				openPopup('绑定成功','提示',undefined,true,undefined,'error','cn');
				WeixinJSBridge.call('closeWindow');
			}
			
			
		}
		
	});
	
	//	
	//	WeixinJSBridge.call('closeWindow');
}
</script>
<style type="text/css">
	.ui-page{
	     background:#eee;
	}
</style>
</head>
<body>
<div data-role="page" id="pageMain" style="background-color: #4E90C7;">
<input type="hidden" name="wid" id="wid" value="${param.wid }">
  <div style="text-align:center;"><img src="images/dada_logo1.png" style="width: 100%;"></div>
	  		
  <div data-role="content" class="content">
        <form id="login_params" method="post" >
       <div>
		    <input type="text" id="email" name="email" placeholder="公司邮箱" style="background: url('${pageContext.request.contextPath}/images/ic_mail.png') no-repeat 5px;background-size: 30px;padding-left:40px;">
	
	  	</div>
	  	<div align="center" style="padding-top:30px;">
			<input id="pubBtn" type="button" value="登录"  onclick="login();" style="padding:10px;background: #E57330;text-shadow: none;opacity:100;color:white;font-size:20px;text-indent:0px;font-family:微软雅黑;border: none;-webkit-appearance:none;-moz-appearance:none;width: 100%;border-radius:7px;" >
	  </div>
  	 </form>
  	
  </div>
</div> 
		
		
	</body>
</html>