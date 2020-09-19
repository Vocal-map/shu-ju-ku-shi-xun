<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<table id="demo" lay-filter="test"></table>

<script type= "text/htmL", id="toolbarDemo">
	<div class="layui-input-inline">
		<input type= "text" name= "codeStudent" placeholder="请输入学号" autocomplete= "off" class="layui-input">
	</div>
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
</script>
<script type="text/javascript">
	var table = layui.table;
	//alert(table);
	table.render({
		elem:"#demo",
		url:"/student/student?action=list",//数据库
		height:"full-100",
		width:"1400",
		toolbar:"#toolbarDemo",
		done:function(res, curr, count){
			layer.msg("查询共"+count+"条记录");
		},
		cols:[[
			{title:"序号", type:"numbers"},
			{title:"学生学号", field:"codeStudent",width:"10%"},
			{title:"学生姓名", field:"nameStudent"},
			{title:"学生性别", field:"sexStudent", width:"8%"},
			{title:"学生电话", field:"telStudent"},
			{title:"所在学院", field:"academyStudent", width:"15%"},
			{title:"所在专业", field:"professionalStudent", width:"13%"},
			{title:"所在年级", field:"gradeStudent"},
			{title:"所在班级", field:"classStudent"},
			{title:"所在宿舍", field:"dormitoryStudent"},
		]]
	});
	var layer = layui.layer;
	var $ = layui.jquery;
	table.on("toolbar(test)", function(obj){
		var event = obj.event;
		switch(event){
		case 'sel':
			var codeStudent = $("input[name='codeStudent']").val();
			console.log(codeStudent)
			table.reload("demo", {
				where:{codeStudent:codeStudent}
			})
			$("input[name='codeStudent']").val(codeStudent);
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