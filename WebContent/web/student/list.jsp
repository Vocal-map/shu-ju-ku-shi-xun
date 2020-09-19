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
<script type="text/html" id="toolbar">
	<input type="button" value="编辑" class="layui-btn layui-btn-xs" lay-event='upd' >
	<input type="button" value="删除" class="layui-btn layui-btn-xs layui-btn-danger" lay-event='del' >
</script>

<script type= "text/htmL", id="toolbarDemo">
	<div class="layui-input-inline">
		<input type= "text" name= "codeStudent" placeholder="请输入学号" autocomplete= "off" class="layui-input">
	</div>
	<input type="button" value="查询" class="layui-btn layui-btn-sm" lay-event='sel'>
	<input type="button" value="添加" class="layui-btn layui-btn-sm" lay-event='add'>
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
			{title:"操作", toolbar:"#toolbar"}
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
		case 'add':
			layer.open({
				type:2,
				area: ['600px', '400px'],
				maxmin: true,
				end: refresh,
				content:"/student/web/student/add.jsp"
			})
			break;
		}
	})
	function refresh(){
		$('input[value="查询"]').click();
	}
	table.on("tool(test)", function(obj){
		var codeStudent = obj.data.codeStudent;
		var layEvent = obj.event;
		switch(layEvent){
		case 'del':
			layer.confirm('确定是否删除?', {icon: 3, title:'提示' }, function(index) {
				$.ajax({
					url:"/student/student",
					data:{codeStudent:codeStudent, action:"del"},
					dataType:"json" ,
					type:"post" ,
					success:function(data){
						var code = data.code;
						if(code==1){
							layer.msg("删除成功", refresh);
						}else if(code==2){
							layer.msg("删除失败, 成绩表存在数据丢失");
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
				content:"/student/web/student/upd.jsp?codeStudent="+codeStudent
			})
			//layer.msg("修改");
			break;
		}
})
</script>
</body>
</html>