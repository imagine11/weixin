<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

		<script src="js/jquerymobile_popup.js"></script>

  	
	<script type="text/javascript">
			$(function(){
				
				loadMeetingType();
			})
			/*加载 课题类别数据*/
			function loadMeetingType(){
				
				$.ajax({
					type:"post",
					url:"MeetingTypeWeixin/list",
					success:function(msg){
						var appendHtml="";
						for(var i=0;i<msg.length;i++){
							appendHtml+="<option value='"+msg[i].tname+"'>"+msg[i].tname+"</option>"
						}
						$("#tname").append(appendHtml);
					}
				});
			}
			
			function showPubDiv() {
				$("#two_line").css("border-top", "5px solid #4E90C7");
				//$("#two_tab").css("color","#777777");
				$("#one_line").css("border-top", "5px solid white");
				//$("#one_tab").css("color","white");
				$("#two").css("display", "none");
				$("#one").css("display", "block");
			}

			 function showMyMeetings() {
				
				   $.mobile.loading( "show", {
					             text: "加载中...",
					             textVisible: true
					          
					     });
				 
				$("#two_line").css("border-top", "5px solid white");
				$("#one_line").css("border-top", "5px solid #4E90C7");
			
					$("#one").css("display", "none");
					$("#two").css("display", "block");
			
					$("#two").empty();
					
					var uid = $("#uid").val();
					$.ajax({
						type:"post",
						url:"meetingPubWeixin/uid/"+uid,
						success:function(msg){
							
							var mname="学习SpringBoot";
							var remark="备注";
							var dateCurr="时间";
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>您共发布了"+msg.length+"场会议</font>";

							for(var i=0;i<msg.length;i++){
								
								appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + msg[i].id + "\");'>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].ptitle + "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].remark + " / " + msg[i].ptime + "</div></div>" +
								"<div style='width: 30%;float: right;'>" +
								"<button class='able-btn' onclick='Choice(\"" + msg[i].id + "\")' >选择讲者</button></div></div>";
								
								
							}
							$("#two").append(appendHtml);
							 $.mobile.loading( "hide" );
						}
					})
			} 
			 //会议详情
			 //跳转到后台 通过后台再跳转到会议详情
			 function showMeetingInfo(id){
				 alert(id)
				 window.location.href="meetingPubWeixin/info?id="+id;
				 
			 }
			 //进入选择讲者页面
			 function Choice(pid){
				
				 window.location.href="pages/weixin/meetingPub/grabList.jsp?pid="+pid;
			 }
			 
			//会议发布
			function subMeetingPub(){
				
				//非空验证
				var ptitle=$("#ptitle").val();
				if(ptitle.length<1){
					openPopup('会议名称不能为空','提示',undefined,true,undefined,'error','cn');
					
					return;
				}
				
				 var ptime = $("#ptime").val();
				if(ptime.length<1){
					openPopup('时间不能为空','提示',undefined,true,undefined,'error','cn');
					return;
				}
				var tname = $("#tname").val();
				if(tname.length<1){
					openPopup('类别不能为空','提示',undefined,true,undefined,'error','cn');
					return;
				} 
				var formData = $("#pubForm").serialize();
				
				$.ajax({
					type:"post",
					url:"meetingPubWeixin/add",
					data:formData,
					success:function(msg){
						if(msg==1){
							openPopup('发布成功','提示',undefined,true,undefined,'error','cn');
						}
					}
				})
				
			}
			
		</script>
</head>
<body>
	
<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 50%;float: left;color: white;" onclick="showPubDiv();" id="one_tab">
					发布
					<div style="border-right: 1px solid white;float: right;margin-top: 10px;height: 20px;"></div>
				</div>
				<div style="width: 50%;float: left;color: white;" onclick="showMyMeetings();" id="two_tab">我的会议</div>
				<div style="border-top:5px solid white;width: 50%;float: left;" id="one_line"></div>
				<div style="border-top:5px solid #4E90C7;width: 50%;float: left;" id="two_line"></div>
			</div>
			<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
				<font style="padding:10px 10px 10px 15px;display: block;color: #777777;">请填写会议相关信息</font>
				<div style="width: 100%;background-color: white;padding:10px 0 10px 0;">
					
					<form id="pubForm" method="post">
						
						<input type="hidden" name="uid" id="uid" value="${user.id}"/>
						<div style="padding-right:15px;padding-left:15px">
							<label for="ptitle" class="font-label">会议名称:</label>
							<input name="ptitle" id="ptitle" placeholder="请输入会议名称" ></input>
						</div>						
						<div  style="padding-right:15px;padding-left:15px">			
								<label for="ptime" class="font-label">会议日期</label>
	      					   <input type="datetime-local" name="ptime" id="ptime" />							
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<label for="tname" class="font-label">类别：</label>
							<select name="tname" id="tname">
								
							
							</select>
						</div>
						
						<div style="padding-right:15px;padding-left:15px">
							<label for="zone" class="font-label">讲者区域</label>
							<select name="zone" id="zone">
								<option value="全国">全国</option>
								<option value="东区">	东区</option>
								<option value="西区">西区</option>
								<option value="南区">南区</option>
								<option value="北区">北区</option>
							</select>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<label for="remark" class="font-label">备注(选填，100字以内)</label>
							<textarea name="remark" id="remark" placeholder="请输入会议备注" maxlength="100" class="font-blue input-lightblue" style="box-shadow: none;"></textarea>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<input type="button" value="发布会议"  onclick="subMeetingPub()" id="btnId" />
						</div>
					</form>
				</div>
			</div>
			

	
	
			
	<div id="two" class="ui-body-d ui-content" style="padding: 0;display: none;width: 100%;">
		
	</div>
	</body>

</html>