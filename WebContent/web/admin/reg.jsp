<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>
</head>
<body>
<fieldset class="layui-elem-field">
  <legend>注册</legend>
  <div class="layui-field-box">
 
 <form class="layui-form" >
  <div class="layui-form-item">
    <label class="layui-form-label">账号</label>
    <div class="layui-input-inline">
      <input type="username" name="userAdmin" required  lay-verify="required" 
      placeholder="请输入账号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passAdmin" required lay-verify="required" 
      placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
<!--     <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passAdmin2" required lay-verify="required" 
      placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">管理员密码</label>
    <div class="layui-input-inline">
      <input type="password" name="rootPass" required lay-verify="required" 
      placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
        <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="reg">
        <input type="button" value="取消" class="layui-btn"onclick="closeThis()">
    </div>
  </div>
  <input type="hidden" name="action" value="add">
</form>
 <script type="text/javascript">
 
 function closeThis(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
// form.render();
form.on("submit(reg)",function(data){
 //当前容器的全部表单字段，名值对形式：{name: value}
	   $.ajax({
	        url:"/student/administrator",//数据库
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==1){
	            	  layer.msg("注册成功", closeThis)
	              }else if(code==2){
	            	  layer.msg("注册失败, 管理员密码错误")
	              }else if(code==3){
	            	  layer.msg("注册失败, 密码与确认密码不一致")
	              }else{
	            	  layer.msg("注册失败")
	              }
	        }
	    });
});
</script>
  </div>
</fieldset>
</body>
</html>