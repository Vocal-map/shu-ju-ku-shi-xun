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
  <legend>修改</legend>
  <div class="layui-field-box">
 
 <form class="layui-form" lay-filter="formA">
  <div class="layui-form-item">
    <label class="layui-form-label">班级编号</label>
    <div class="layui-input-inline">
      <input type="text" name="codeClass" required  lay-verify="required" readonly="readonly"
      placeholder="请输入班级编号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">所在系</label>
    <div class="layui-input-inline">
      <input type="text" name="departmentClass" required lay-verify="required" readonly="readonly"
      placeholder="请输入系名" autocomplete="off" class="layui-input">
    </div>
<!--     <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">指导老师</label>
    <div class="layui-input-inline">
      <input type="text" name="instructorClass" required 
      placeholder="请输入老师" autocomplete="off" class="layui-input">
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
var codeClass = '<%=request.getParameter("codeClass")  %>'
var departmentClass = '<%=request.getParameter("departmentClass")  %>'
console.log(codeClass)
$.ajax({
	url:"/student/classes",
	data:{codeClass:codeClass, action:'sel'},
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
		   url:"/student/classes",
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==1){
	            	  layer.msg("修改成功", closeThis)
	              }else{
	            	  layer.msg("修改失败")
	              }
	        }
	    });
});
</script>

</body>
</html>