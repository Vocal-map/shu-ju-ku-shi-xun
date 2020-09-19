<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dormitory</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<table id="demo" lay-filter="test"></table>
<script type="text/html" id="toolbar">
	<input type="button" value="编辑" class="layui-btn layui-btn-xs" lay-event='upd' >
	<input type="button" value="删除" class="layui-btn layui-btn-xs layui-btn-danger" lay-event='del' >
</script>

<script type= "text/htmL", id="toolbarDemo">
	<div class="layui-input-inline">
		<input type= "text" name= "codeDormitory" placeholder="请输入宿舍编号" autocomplete= "off" class="layui-input">
	</div>
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
	<input type="button" value="添加" class="layui-btn layui-btn-sm" lay-event='add'>
</script>
<script type="text/javascript">
	var table = layui.table;
	//alert(table);
	table.render({
		elem:"#demo",
		url:"/student/dormitory?action=list",//数据库
		height:"full-100",
		width:"1030",
		toolbar:"#toolbarDemo",
		done:function(res, curr, count){
			layer.msg("查询共"+count+"条记录");
		},
		cols:[[
			{title:"序号", type:"numbers"},
			{title:"宿舍编号", field:"codeDormitory"},//与list（model）中的值对应
			{title:"宿舍容量", field:"capacityDormitory"},
			{title:"宿舍价格", field:"priceDormitory"},
			{title:"操作", toolbar:"#toolbar"}
		]]
	});
	var layer = layui.layer;
	var $ = layui.jquery;
	table.on("toolbar(test)", function(obj){
		var event = obj.event;
		switch(event){
		case 'sel':
			var codeDormitory = $("input[name='codeDormitory']").val();
			console.log(codeDormitory)
			table.reload("demo", {
				where:{codeDormitory:codeDormitory}
			})
			$("input[name='codeDormitory']").val(codeDormitory);
			layer.msg("查询");
			break;
		case 'add':
			layer.open({
				type:2,
				area: ['600px', '400px'],
				maxmin: true,
				end: refresh,
				content:"/student/web/dormitory/add.jsp"
			})
			break;
		}
	})
	function refresh(){
		$('input[value="查询"]').click();
	}
	table.on("tool(test)", function(obj){
		var codeDormitory = obj.data.codeDormitory;
		var layEvent = obj.event;
		switch(layEvent){
		case 'del':
			layer.confirm('确定是否删除?', {icon: 3, title:'提示' }, function(index) {
				$.ajax({
					url:"/student/dormitory",
					data:{codeDormitory:codeDormitory, action:"del"},
					dataType:"json" ,
					type:"post" ,
					success:function(data){
						var code = data.code;
						if(code==1){
							layer.msg("删除成功", refresh);
						}else{
							layer.msg("删除失败");
						}
					}
				})
			});
			//layer.msg("删除");
			break;
		case 'upd':
			layer.open({
				type:2,
				area: ['600px', '400px'],
				maxmin: true,
				end: refresh,
				content:"/student/web/dormitory/upd.jsp?codeDormitory="+codeDormitory
			})
			//layer.msg("修改");
			break;
		}
})
</script>
</body>
</html>