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
		<script type="text/javascript">
			$(function(){
				showMyMeetings();
			});

			function showMyMeetings() {

					$("#two").empty();
					var pid = $("#pid").val();
					$.ajax({
						type:"post",
						url:"meetingGrabWeixin/grabList",
						data:{"pid":pid},
						success:function(msg){
							
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>共有"+msg.length+"人参加本次会议抢单</font>";

							for(var i=0;i<msg.length;i++){
								
								appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + 1 + "\");'>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].user.name + "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].user.province + " / " + msg[i].remark + "</div></div>" +
								"<div style='width: 30%;float: right;'>" ;
								var grabStatus = msg[i].grabstatus;
								if(grabStatus==0){
									appendHtml+="<button class='able-btn' onclick ='chooseGrab(\""+msg[i].pid+"\",\""+msg[i].uid+"\",\""+msg[i].user.name+"\")' >就选你</button>";
								}else if(grabStatus==1){
									appendHtml+="已选择";
								}else{
									appendHtml+="未选择";
								}
								appendHtml+="</div></div>";
							}
							$("#two").append(appendHtml);
						}
					})

			}
			function chooseGrab(pid,uid){
				
				if(confirm("确定要选择"+name+"作为本次会议讲者吗")){
					$.ajax({
						type:"post",
						url:"meetingGrabWeixin/chooseGrabList",
						data:{"pid":pid,"uid":uid},
						success:function(msg){
							if(1==msg){
								alert("选择讲者成功");
								showMyMeetings();
							}else{
								alert("网络异常,请稍候尝试")
							}
						}
					})
				}
			}
		</script>	
	</head>

	<body>
	<input id="pid" name="pid" value="${param.pid }"/>
	<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">				
				<div style="width: 100%;float: left;color: white;"  id="two_tab">抢单者列表</div>
			</div>
			
	<div id="two" class="ui-body-d ui-content" style="padding: 0;width: 100%;">
		
	</div>
	</body>

</html>