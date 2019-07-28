<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/css/style.css" />


<title>编辑角色</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add" name="form-article-add" action="user/add" method="post">
	
	
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="email" name="email">
			</div>
		</div>
		
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">电话:</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="telephone" name="telephone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>省份：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="province" name="province">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>城市：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="city" name="city">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>地区：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="zone" name="zone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">角色：</label>
				
					<input type="radio"  onClick="changeStatuss(this)"  name="type" >
					<label for="checkbox-pinglun" id="statusLabelIds">发单组</label>
				
				
					<input type="radio" onClick="changeStatuss(this)"  name="type">
					<label for="checkbox-pinglun" id="statusLabelIds">抢单组</label>
					
					<input type="radio" onClick="changeStatuss(this)"  name="type">
					<label for="checkbox-pinglun" id="statusLabelIds">查看组</label>
				
					<input type="radio" onClick="changeStatuss(this)" name="type">
					<label for="checkbox-pinglun" id="statusLabelIds">VIP组</label>
					<input type="hidden" value="${user.rid }" value="1" name="rid" id="rid">
		</div>
	
	
		
		
		
		<!-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">角色描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" 
				datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" 
				onKeyUp="$.Huitextarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div> -->
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">状态：</label>
				
				
				
					<input type="checkbox" onClick="changeStatus(this)" checked="checked">
					<label for="checkbox-pinglun" id="statusLabelId">有效</label>
				
					<input type="hidden" value="1"  name="status" id="status">
		</div>
		
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="js/h-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="js/h-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/h-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="js/h-ui/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="js/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="js/h-ui/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
/*根据状态改变触发事件 */
function changeStatus(obj){
	if(obj.checked==true){
		$("#statusLabelId").html("有效");
		$("#status").val("1");
	}else{
		
		$("#status").val("2");
	}
}
function changeStatuss(obj){
	if(obj.checked==true){
		var data = $("#statusLabelIds").val();
		if(data.equals("发单组")){
			$("#rid").val("1");
		}else if(data.equals("抢单组")){
			$("#rid").val("2");
		}else if(data.equals("查看组")){
			$("#rid").val("3");
		}else if(data.equals("VIP组")){
			$("#rid").val("4");
		}
		
	}else{
		
		
	}
}


$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//表单验证
	$("#form-article-add").validate({
		rules:{
			rname:{
				required:true,
			},
			sortnum:{
				required:true,
				digits:true,
			},
			remark:{
				required:true,
			},	
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			/* alert(1);
			document.form-article-add.submit();
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index); */
			var formData = $("#form-article-add").serialize();
			$.ajax({
				type:"post",
				url:"user/add",
				data:formData,
				success:function(msg){
					
					var index = parent.layer.getFrameIndex(window.name);
					
					parent.location.href="user/list";
					parent.layer.close(index)
				}
			
				
			})
			
		}
	});
});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>