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
		<meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta name="format-detection" content="telephone=no, address=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black-translucent" name="apple-mobile-web-app-status-bar-style">
		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>


  	
	<script type="text/javascript">
		function subMeetingPub(){
			var remark=$("#remark").val();
			//判断非空
			if(remark.length<1){
				alert("备注不能为空")
				return;
			}else{
				
				var dataObj=$("#formGrabId").serialize();
				
				$("#remark").val("");
				
				var uid = $("#uid").val();
				$.ajax({
					type:"post",
					url:"meetingGrabWeixin/add",
					data:dataObj,
					success:function(mag){
						alert("操作完成")
						window.location.href="pages/weixin/meetingGrab/meetingGrabList.jsp?uid="+uid;
					}
				})
			}	
		}
			
			
	</script>
</head>
<body>
	
<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 100%;float: left;color: white;" id="one_tab">
					抢单
					
				</div>
			
				
			</div>
			<form action="" name="formGrabId" id="formGrabId">
			<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
			<input type="hidden" name="uid" value="${uid } " id="uid"/>
			<input type="hidden" name="pid" value="${pid } " id="pid"/>
				<font style="padding:10px 10px 10px 15px;display: block;color: #777777;">请填写会议抢单相关信息</font>
				<div style="width: 100%;background-color: white;padding:10px 0 10px 0;">
					
					
						<div style="padding-right:15px;padding-left:15px">
							<label for="remark" class="font-label">备注(选填，100字以内)</label>
							<textarea name="remark" id="remark" placeholder="请输入会议备注" maxlength="100" class="font-blue input-lightblue" style="box-shadow: none;"></textarea>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<input type="button" value="进行抢单"  onclick="subMeetingPub()" id="btnId" />
						</div>
				
				</div>
			</div>
			</form>

	
	
			

	</body>

</html>