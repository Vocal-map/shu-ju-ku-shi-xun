<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<fieldset class="layui-elem-field">
  <legend>添加</legend>
  <div class="layui-field-box">
 <!--     <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
 <form class="layui-form" lay-filter="formA">
  <div class="layui-form-item">
    <label class="layui-form-label">学生学号</label>
    <div class="layui-input-inline">
      <input type="text" name="codeStudent" required  lay-verify="required" readonly="readonly"
      placeholder="请输入学生学号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">学生姓名</label>
    <div class="layui-input-inline">
      <input type="text" name="nameStudent" required lay-verify="required" 
      placeholder="请输入学生姓名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">学生性别</label>
    <div class="layui-input-inline">
      <select name='sexStudent'>
  			<option value='男'>男</option>
  			<option value='女'>女</option>
		</select> 
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">学生电话</label>
    <div class="layui-input-inline">
      <input type="text" name="telStudent" required  lay-verify="required" 
      placeholder="请输入学生电话" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在学院</label>
    <div class="layui-input-inline">
      <input type="text" name="academyStudent" required lay-verify="required" 
      placeholder="请输入学院" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在专业</label>
    <div class="layui-input-inline">
      <input type="text" name="professionalStudent" required lay-verify="required" 
      placeholder="请输入专业" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在年级</label>
    <div class="layui-input-inline">
      <input type="text" name="gradeStudent" required  lay-verify="required" 
      placeholder="请输入年级" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在班级</label>
    <div class="layui-input-inline">
      <input type="text" name="classStudent" required lay-verify="required" 
      placeholder="请输入班级" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在宿舍</label>
    <div class="layui-input-inline">
      <input type="text" name="dormitoryStudent" required lay-verify="required" 
      placeholder="请输入宿舍" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
        <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="upd">
        <input type="button" value="关闭" class="layui-btn layui-btn-primary" onclick="closeThis()">
    </div>
  </div>
  <input type="hidden" name="action" value="upd">
</form>
 
  </div>
</fieldset>
 
<script type="text/javascript">
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var codeStudent = '<%=request.getParameter("codeStudent")  %>'
console.log(codeStudent)
$.ajax({
	url:"/student/student",
	data:{codeStudent:codeStudent, action:'sel'},
	dataType:"json",
	type:"post",
	success:function(data){
		console.log(data)
		form.val("formA", data.code)
	}
})

function closeThis(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}

// form.render();
form.on("submit(upd)",function(data){
 //当前容器的全部表单字段，名值对形式：{name: value}
 	
	   $.ajax({
		   url:"/student/student",
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==1){
	            	  layer.msg("修改成功", closeThis)
	              }else if(code==2){
	            	  layer.msg("修改失败, 班级不存在")
	              }else if(code==3){
	            	  layer.msg("修改失败, 宿舍不存在")
	              }else{
	            	  layer.msg("修改失败")
	              }
	        }
	    });
});
</script>

</body>
</html>