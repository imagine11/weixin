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
			function showPubDiv() {
				$("#two_line").css("border-top", "5px solid #4E90C7");
				//$("#two_tab").css("color","#777777");
				$("#one_line").css("border-top", "5px solid white");
				//$("#one_tab").css("color","white");
				$("#two").css("display", "none");
				$("#one").css("display", "block");
			}
			
			$(function(){
				//加载 课题类别
				loadMeetingType();
				//加载 可抢单列表
				loadMeetingGrabList("-1");
			})
			//加载可抢单列表
			function loadMeetingGrabList(tname){

				   $.mobile.loading( "show", {
					             text: "加载中...",
					             textVisible: true
					          
					     });
				$("#grabDiv").empty();
				$("#grabNum").empty();
				var uid = $("#uid").val();
				$.ajax({
					type:"post",
					url:"meetingGrabWeixin/list",
					data:{"uid":uid,"tname":tname},
					success:function(msg){
						$("#grabNum").append(msg.length);
						var mname="学习SpringBoot";
						var remark="备注";
						var dateCurr="时间";
						var appendHtml = "";

						for(var i=0;i<msg.length;i++){
							
							appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
							"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + msg[i].id + "\");'>" +
							"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
							msg[i].ptitle + "</div>" +
							"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
							msg[i].remark + " / " + msg[i].ptime + "</div></div>" +
							"<div style='width: 30%;float: right;'>" +
							"<button class='able-btn' onclick='grabTo("+uid+",\""+msg[i].id+"\")' >可抢单</button></div></div>";
							
							
							
							
							
						}
						$("#grabDiv").append(appendHtml);
						$.mobile.loading( "hide" );
						
					}
					
				})
			}
			
			function grabTo(uid,pid){
				alert(pid)
				$("#pid").val(pid);
				document.formGrab.submit();
			}
			
			
			/*加载 课题类别数据*/
			function loadMeetingType(){
				
				$.ajax({
					type:"post",
					url:"MeetingTypeWeixin/list",
					success:function(msg){
						var appendHtml="<option value='-1'>可抢单</option>";
						for(var i=0;i<msg.length;i++){
							appendHtml+="<option value='"+msg[i].tname+"'>"+msg[i].tname+"</option>"
						}
						$("#tname").append(appendHtml);
					}
				});
			}
			

			function showMyGrabMeetings() {
				
				$("#two_line").css("border-top", "5px solid white");
				$("#one_line").css("border-top", "5px solid #4E90C7");
			
					$("#one").css("display", "none");
					$("#two").css("display", "block");

					$("#two").empty();
					var mname="学习SpringBoot";
					var remark="备注";
					var dateCurr="时间";
					
					var uid = $("#uid").val();
					$.ajax({
						type:"post",
						url:"meetingGrabWeixin/"+uid,
						success:function(msg){
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>您共抢了"+msg.length+"场会议</font>";
							for(var i=0;i<msg.length;i++){
								
								appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + 1 + "\");'>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].ptitle+ "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].pcode + " / " + msg[i].tname + "</div></div>" +
								"<div style='width: 30%;float: right;'>" ;
								var grabstatus=msg[i].meetingGrab.grabstatus;
								if(grabstatus==0){
									appendHtml+="未审批";
								}else if(grabstatus==1){
									appendHtml+="<button class='able-btn' >已抢单</button>";
									
								}else{
									appendHtml+="抢单失败";
								}
								appendHtml+="</div></div>";
								
							}
							$("#two").append(appendHtml);
						}
					})
				

						
							
							
							
						appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
							"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + 1 + "\");'>" +
							"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
							mname + "</div>" +
							"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
							remark + " / " + dateCurr + "</div></div>" +
							"<div style='width: 30%;float: right;'>" +
							"<button class='able-btn' >选择讲者</button></div></div>";	
					
					
			
			}
		</script>	
	</head>

	<body>
		<form action="meetingGrabWeixin/grabTo" name="formGrab" method="post">
		<input type="hidden" id="uid" name="uid" value="${param.uid}"/>
		<input type="hidden" id="pid" name="pid" />
	</form>
	<div data-role="page" id="pageDetail">
			
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 50%;float: left;color: white;" onclick="showPubDiv();" id="one_tab">
					抢单
					<div style="border-right: 1px solid white;float: right;margin-top: 10px;height: 20px;"></div>
				</div>
				<div style="width: 50%;float: left;color: white;" onclick="showMyGrabMeetings();" id="two_tab">我的抢单</div>
				<div style="border-top:5px solid white;width: 50%;float: left;" id="one_line"></div>
				<div style="border-top:5px solid #4E90C7;width: 50%;float: left;" id="two_line"></div>
			</div>
	<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
		<div style="padding:0 0 0 15px;display: block;width: 55%;float: left;line-height: 60px;color: #777777;">
			可参与<font id="grabNum"></font>场会议的抢单
		</div>
		<div style="width: calc(45% - 15px);float: right;">
			<select id="tname" onchange="loadMeetingGrabList(this.value)" >
				
			
			</select>
		</div>
		<div id="grabDiv" style="width: 100%;float: left;"></div>
	</div>
			

	
	
			
	<div id="two" class="ui-body-d ui-content" style="padding: 0;display: none;width: 100%;">
		
	</div>
	</body>

</html>