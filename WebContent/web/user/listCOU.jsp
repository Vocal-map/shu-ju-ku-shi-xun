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
	<div class="layui-input-inline">
		<input type= "text" name= "nameCourse" placeholder="请输入课程名" autocomplete= "off" class="layui-input">
	</div>
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
</script>
<script type="text/javascript">
	var table = layui.table;
	//alert(table);
	table.render({
		elem:"#demo",
		url:"/student/course?action=list",//数据库
		height:"full-100",
		width:"1030",
		toolbar:"#toolbarDemo",
		done:function(res, curr, count){
			layer.msg("查询共"+count+"条记录");
		},
		cols:[[
			{title:"序号", type:"numbers"},
			{title:"课程编号", field:"codeCourse"},//与list（model）中的值对应
			{title:"课程名", field:"nameCourse"},
			{title:"课程学分", field:"creditCourse"},
			{title:"课程学时", field:"periodCourse"},
		]]
	});
	var layer = layui.layer;
	var $ = layui.jquery;
	table.on("toolbar(test)", function(obj){
		var event = obj.event;
		switch(event){
		case 'sel':
			var nameCourse = $("input[name='nameCourse']").val();
			console.log(nameCourse)
			table.reload("demo", {
				where:{nameCourse:nameCourse}
			})
			$("input[name='nameCourse']").val(nameCourse);
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