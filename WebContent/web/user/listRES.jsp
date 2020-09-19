<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<table id="demo" lay-filter="test"></table>

<script type= "text/htmL", id="toolbarDemo">
	<div class="layui-input-inline">
		<input type= "text" name= "studentResult" placeholder="请输入学生学号" autocomplete= "off" class="layui-input">
	</div>
	<div class="layui-input-inline">
		<input type= "text" name= "courseResult" placeholder="请输入课程编号" autocomplete= "off" class="layui-input">
	</div>
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
</script>
<script type="text/javascript">
	var table = layui.table;
	//alert(table);
	table.render({
		elem:"#demo",
		url:"/student/result?action=list",
		height:"full-100",
		width:"1030",
		toolbar:"#toolbarDemo",
		done:function(res, curr, count){
			layer.msg("查询共"+count+"条记录");
		},
		cols:[[
			{title:"序号", type:"numbers"},
			{title:"学生学号", field:"studentResult"},
			{title:"课程编号", field:"courseResult"},
			{title:"课程名", field:"courseNameResult"},
			{title:"成绩", field:"resultResult"},
		]]
	});
	var layer = layui.layer;
	var $ = layui.jquery;
	table.on("toolbar(test)", function(obj){
		var event = obj.event;
		switch(event){
		case 'sel':
			var studentResult = $("input[name='studentResult']").val();
			var courseResult = $("input[name='courseResult']").val();
			table.reload("demo", {
				where:{studentResult:studentResult,courseResult:courseResult}
			})
			$("input[name='studentResult']").val(studentResult);
			$("input[name='courseResult']").val(courseResult);
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