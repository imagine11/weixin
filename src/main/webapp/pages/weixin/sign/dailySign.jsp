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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>会议达人</title>
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/sign.css">

<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<script src="js/sign.js"></script>
<script type="text/javascript">
function initSign(year, month, signList){
	$("#currDate").text(year+"-"+((month+1)<10?"0"+(month+1):(month+1)));
	var str = calUtil.drawCal(year,month+1,signList);
	$("#calendar").html("").html(str);
}

$(function(){
	//初始化签到表，为当前月份的签到情况
	var iDate = new Date();
	//var signStr=$("#dateUrlId").val();
	
	//把每次签到的时间放入签到表中  再在页面中获取 循环遍历 
	var signstr = $("#signstr").val();
	var signStr=signstr;
	var signList=signStr.split(",");
	initSign(iDate.getFullYear(), iDate.getMonth(), signList);
	
});
</script>
<style type="text/css">
@media screen and (min-width:1024px) {
	.rich_media {
		width: 500px;
		margin-left: auto;
		margin-right: auto;
		padding: 20px;
	}
}
.STYLE1 {
	font-size: 15px;
	 line-height:32px;  

}
.STYLE2 {
	color: #FF6600;
	font-weight: bold;
}
</style>
</head>
<input type="hidden" value="" id="dateUrlId"> 
<body style=" background-color:#F5F5F5">
 <input  type="hidden" id="signstr" name="signstr" value="${param.signstr}"/>
<input type="hidden"   id="status" name="status" value="${param.status}"/>
 <input type="hidden" id="point" name="point" value="${param.point}"/>
  <input type="hidden" id="str" name="str" value="${param.str}"/>

 <div>
 	<div style="text-align: center;background-color: #2a5e91;height: 50px;margin-bottom:2px;">
				<span style="cursor: pointer;font-weight: 600;font-size: 20px;color: #fff;height: 50px;line-height: 50px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每日签到 
					</span>
				<span style="text-align:right;">&nbsp;&nbsp;&nbsp;<a style="font-size:18px;color:#fff" href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排行榜</a></span>
	</div>	
 </div>
 
 
 
    <div >
	
	<!--图片排列-->
<div class="container-fluid" style="padding-left:5px; padding-right:1px;margin-bottom:10px; margin-top: 10px;"   align="left">
	<div  style="width:64%; float:left;"  align="left">
		<!--  <div  align="center"> <img style="display:block;width:90%;" src="images/img_1.png" /></div>
		 -->
		  <div  style="margin-left: 5px;font-size:30px;color:#f85c03;">签到成功！ </div>
		  <br/>
	 	 <div style="height:35px;margin-left: 5px;">
	 	  <c:if test="${param.status==0}">
	 	  <img style="display:block;width:13%;float:left;"
	 	src="images/img_2.png"/>
	 	</c:if>
	 	<c:if test="${param.status==1}">
	 	  <img style="display:block;width:13%;float:left;"
	 	src="images/img_21.png"/>
	 	</c:if>  
	 	 <span class="STYLE1">&nbsp;获得积分</span></div>
		 <div  style="height:31px; margin-left: 5px;"> <img style="display:block;width:13%;float:left;" src="images/img_3.png" />
		 <span class="STYLE1">&nbsp;您共有<span class="STYLE2">${param.point }</span>积分啦!</span></div>
		 <div style="float:left; "> <span class="STYLE1">&nbsp;您已经击败全国<span class="STYLE2">${param.str}%</span>的用户啦!</span></div>
	</div>
	<div  style="width:32%; height:50%;;float:left; margin-right: 5px;" >
		<br/>
		<img style="width:100%;" src="images/dada1.png" /></div>
	</div>

</div>
<!--图片排列结束-->
	
	
            <div id="page-content" style="background-color:#FFFFFF;width:94%; margin-left:11px;">
            	<div style="text-align: center;background-color: #2a5e91;height: 50px;margin-bottom: 2px;">
					<span style="cursor: pointer;font-weight: 600;font-size: 20px;color: #fff;height: 50px;line-height: 50px;">当月签到记录</span>
			    </div>
			    <div class="container-fluid">
			    	<div class="row-fluid">
			    		<div class='sign_succ_calendar_title'>
			    			<div class='calendar_month_span'>
			    				<span id='minusMonth' style='padding-right:50px;cursor: pointer;'></span>
			    				<span id='currDate'></span>
			    				<span id='addMonth' style='padding-left:50px;cursor: pointer;'></span>
			    			</div>
			    		</div>
			    		<div id="calendar"></div>
			    	</div>
			    </div>
            </div>
    </div>
</body>
</html>