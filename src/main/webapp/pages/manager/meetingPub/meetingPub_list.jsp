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
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="js/h-ui/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>角色列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 发单列表 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form action="meetingPub/list" method="POST">
		<div class="text-c">
			<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 <span class="select-box inline">
			<select name="grabstatus" class="select">
				 <option value="-1">全部状态</option>
				<option value="1" <c:if test="${meetingGrab.grabstatus==1 }">selected="selected"</c:if>>已匹配</option>
				<option value="0" <c:if test="${meetingGrab.grabstatus==0 }">selected="selected"</c:if>>未匹配</option> 
				<option value="2" <c:if test="${meetingGrab.grabstatus==2 }">selected="selected"</c:if>>匹配失败</option> 
			</select>
			
			<input type="text" name="pcode" id="" value="${meetingpub.pcode}" placeholder="会议编号" style="width:250px" class="input-text">
			<input type="text" name="name" id="" value="${user.name}" placeholder="发单人姓名" style="width:250px" class="input-text">
			<input type="text" name="tname" id="" value="${meetingpub.tname}" placeholder="课题类别" style="width:250px" class="input-text">
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</div>
	</form>	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
	<a class="btn btn-primary radius" data-title="添加角色" data-href="meetingPub-add.html" onclick="meetingPub_add('角色添加','pages/manager/meetingPub/meetingPub_add.jsp','1','2')" href="javascript:;">
	<i class="Hui-iconfont">&#xe600;</i> 添加角色</a></span> 
	<span class="r">共有数据：<strong>${listMeetingpub.size() }</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="check-ids" value=""></th>
					
					<th>会议编号</th>
					<th width="100">召开时间</th>
					
					<th width="100">课题类别</th>
					<th width="60">主题</th>
					<th width="60">讲者区域</th>
					<th width="60">备注</th>
					<th width="75">发单人姓名</th>
					
					<th width="120">创建时间</th>
					
					<th width="80">状态</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listMeetingpub}" var="r">
				<tr class="text-c" >
					<td><input type="checkbox" value="${r.id }" name="ids"></td>
					
					<td class="text-l"><u style="cursor:pointer" class="text-primary" >${r.pcode }</u></td>
					<td>${r.ptime }</td>
					<td>${r.tname }</td>
					<td>${r.ptitle }</td>
					
					<td>${r.zone }</td>
					<td>${r.remark }</td>
					<td>${r.user.name }</td>
					<td>${r.createdate }</td>
					
					<td class="td-status">
						<c:if test="${r.meetingGrab.grabstatus==1}">
							<span class="label label-success radius">已匹配</span>
						</c:if>
						<c:if test="${r.meetingGrab.grabstatus==0}">
							<span class="label label-danger radius">未匹配</span>
						</c:if>
						<c:if test="${r.meetingGrab.grabstatus==2}">
							<span class="label label-danger radius">匹配失败</span>
						</c:if>
						
					</td>
					<td class="f-14 td-manage">
					<c:if test="${r.status==0 }">
							<a style="text-decoration:none" onClick="meetingPub_start(this,'${r.id}')" href="javascript:;" title="上架">
								<i class="Hui-iconfont">&#xe6dc;</i>
							</a>
						</c:if>
						
						<c:if test="${r.status==1 }">
							<a style="text-decoration:none" onClick="meetingPub_stop(this,'${r.id}')" href="javascript:;" title="下架">
							<i class="Hui-iconfont">&#xe6de;</i>
							</a>
						</c:if>
					<a style="text-decoration:none" class="ml-5" onClick="meetingPub_edit('角色编辑','meetingPub/selectById?id=${r.id }','10001')" href="javascript:;" title="编辑">
						<i class="Hui-iconfont">&#xe6df;</i>
						</a>  
					<a style="text-decoration:none" class="ml-5" onClick="meetingPub_del(this,'${r.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="js/h-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="js/h-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/h-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/h-ui/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"lengthMenu":[10,20,30,40,50],
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,6]}// 不参与排序的列
	]
});

/*资讯-添加*/
function meetingPub_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-编辑*/
function meetingPub_edit(title,url,id,w,h){
	alert(1)
	alert(url)
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

/*角色-批量删除*/
 function datadel(){
	//第一步 先获得所有教ids 名字的checkbox
	var objs=document.getElementsByName("ids");
	//2 判断哪个被选中
	var ids="";
	for(var i=0;i<objs.length;i++){
		if(objs[i].checked==true){
		ids+=objs[i].value+",";
		}
	}
	if(ids==""){
		
		layer.msg('请选择需要删除的数据!',{icon:2,time:3000});
		return;
	}
	layer.confirm('确认要删除吗？',function(index){
		alert(1)
		$.ajax({
			type: 'POST',
			url: 'meetingPub/batch/'+ids,
			dataType: 'json',
			success: function(data){
				location.replace(location.href);
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
		
}
 
 

/*角色-删除*/
function meetingPub_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'meetingPub/'+id,
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
}

/*资讯-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*角色-下架*/
function meetingPub_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$.ajax({
			type:"post",
			url:"meetingPub/updateStatusById",
			data:{"status":0,"id":id},
			success:function(msg){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="meetingPub_start(this,'+id+')" href="javascript:;" title="上架"><i class="Hui-iconfont">&#xe6dc;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">无效</span>');
				
				
				$(obj).remove();
				layer.msg('已下架!',{icon: 5,time:1000});
			}
		});
	});
}

/*角色-上架*/
function meetingPub_start(obj,id){
	layer.confirm('确认要上架吗？',function(index){
		$.ajax({
			type:"post",
			url:"meetingPub/updateStatusById",
			data:{"status":1,"id":id},
			success:function(msg){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="meetingPub_stop(this,'+id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">有效</span>');
				
				
				$(obj).remove();
				layer.msg('已上架!',{icon: 6,time:1000});
			}
		});
	});
}
/*资讯-发布*/
function article_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>
</html>