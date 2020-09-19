<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>
<style type="text/css">
	body{padding: 20px;}
	.bootstrap-elem-field{
	    margin-bottom: 10px;
		padding: 50;
		border-width: 2px;
		border-style: solid;
		border-color: black;
		
	}
	.bootstrap-elem-field legend {
	    margin-left: 20px;
	    padding: 0 10px;
	    font-size: 20px;
	    font-weight: 300;
	    border-bottom:none;
	    width: auto;
	}
	.bootstrap-field-box{padding: 10px 15px;}
</style>
</head>
<body>  

              
<fieldset style="
    width: 100%;
    right:-300px;
     height: 400px;
     padding: 30px 5px 15px 20px;
     margin: 200px 300px 300px 300px;
    position: fixed; 
   
   	opacity: 0.75;
    background: linear-gradient(to bottom right,#CCCCCC,#c0c0c0);
    background: -webkit-linear-gradient(to bottom right,#CCCCCC,#c0c0c0);">
  <div class="layui-field-box" style="
  	text-align:center; 
  	line-height:100px; 
 	border: 2px;
	left: 40%;
	top: 40%;
	position:absolute;">
 
 <form class="layui-form" >
  <div class="layui-form-item">

    <label class="layui-form-label"style="font-family:黑体; font-weight:40%;font-size:16px;">管理员密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passAdmin" required lay-verify="required" 
      placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  
    <div class="layui-form-item" >
    <div class="layui-input-block">
        <button type="button" class="layui-btn" lay-submit lay-filter="login">登陆</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  <input type="hidden" name="action" value="login2">
</form>
 
  </div>
</fieldset>

<script type="text/javascript">

function toMain(){
	location.href="/student/web/admin/list.jsp";
}
/* function closeThis(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
} */
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
// form.render();
form.on("submit(login)",function(data){
 //当前容器的全部表单字段，名值对形式：{name: value}
	   $.ajax({
	        url:"/student/administrator",
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==1){
	            	  layer.msg("登陆成功", toMain)
	              }else if(code==3){
	            	  layer.msg("登陆失败，密码错误")
	              }else{
	            	  layer.msg("登陆失败")
	              }
	        }
	    });
});
</script>


</body>
</html>