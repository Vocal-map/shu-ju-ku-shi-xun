<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pass</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>
<fieldset class="layui-elem-field">
  <legend>密码修改</legend>
  <div class="layui-field-box">
 
 <form class="layui-form" lay-filter="formA">
  <div class="layui-form-item">
    <label class="layui-form-label">账号</label>
    <div class="layui-input-inline">
      <input type="text" name="userAdmin" required  lay-verify="required" readonly="readonly"
      placeholder="请输入账号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">管理员密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passOld" required lay-verify="required" 
      placeholder="请输入管理员密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">新密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passNew" required lay-verify="required" 
      placeholder="请输入新密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passNew2" required lay-verify="required" 
      placeholder="请输入新密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
        <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="pass">
        <input type="button" value="关闭" class="layui-btn layui-btn-primary" onclick="closeThis()">
    </div>
  </div>
  <input type="hidden" name="action" value="pass">
</form>
 
  </div>
</fieldset>
<script type="text/javascript">
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var userAdmin = '<%=request.getParameter("userAdmin")  %>'
	console.log(userAdmin)
	$.ajax({
	url:"/student/administrator",
	data:{userAdmin:userAdmin, action:'sel'},
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
form.on("submit(pass)",function(data){
 //当前容器的全部表单字段，名值对形式：{name: value}
 	
	   $.ajax({
	        url:"/student/administrator",
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==2){
	            	  layer.msg("修改密码失败，原密码错误")
	              }else if(code==1){
	            	  layer.msg("修改密码成功",closeThis)
	              }else if(code==3){
	            	  layer.msg("修改密码失败, 新密码与确认密码不一致")
	              }else{
	            	  layer.msg("修改密码失败")
	              }
	        }
	    });
});
</script>
</body>
</html>