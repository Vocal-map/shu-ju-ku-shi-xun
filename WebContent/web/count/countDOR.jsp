<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>course</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<table id="demo" lay-filter="test"></table>

<script type= "text/htmL", id="toolbarDemo">
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
</script>
<script type="text/javascript">
	var table = layui.table;
	//alert(table);
	table.render({
		elem:"#demo",
		url:"/student/count?action=listDor",//数据库
		height:"full-100",
		width:"1030",
		toolbar:"#toolbarDemo",
		done:function(res, curr, count){
			layer.msg("查询共"+count+"条记录");
		},
		cols:[[
			{title:"序号", type:"numbers"},
			{title:"宿舍编号", field:"dorStudent"},//与list（model）中的值对应
			{title:"学生学号", field:"codeStudent"},
			{title:"学生姓名", field:"nameStudent"},
			{title:"宿舍目前人数", field:"countDor"},
		]]
	});
	var layer = layui.layer;
	var $ = layui.jquery;
 	table.on("toolbar(test)", function(obj){
		var event = obj.event;
		switch(event){
		case 'sel':
			table.reload("demo", {
				where:{}
			})
			layer.msg("查询");
			break;
		}
	}) 
	function refresh(){
		$('input[value="查询"]').click();
	}
</script>
</body>
</html>