<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>
</head>
<body style="background: url(/student/resource/shelf.jpg);
background-size: 100%;
background-repeat: no-repeat;" >  

              
<fieldset class="layui-elem-field" style="
    width: 100%;
    right:-300px;
     height: 400px;
     padding: 10px 5px 15px 20px;
     margin: 250px 300px 300px 300px;
    position: fixed; 
   
   	opacity: 0.8;
    background: linear-gradient(to bottom right,#CCCCCC,#c0c0c0);
    background: -webkit-linear-gradient(to bottom right,#CCCCCC,#c0c0c0);">

  <!-- <legend style="font-family:微软雅黑; font-weight:100%;font-size:30px;text-align:center; ">登陆</legend> -->
  <div class="layui-field-box"  style="text-align:center; line-height:100px; border: 1px;
left: 40%;
top: 45%;">
 
 <form class="layui-form" >
    <div class="layui-form-item" >
    <div class="layui-input-block" style="text-align:center; line-height:100px; border: 1px;
top: 10%; left: 38%; position: absolute; margin-top: 100px;">
        <button type="button" class="layui-btn" onclick="toMain()">管理员登陆</button>
        <button type="button" class="layui-btn" onclick="toRoot()">用户登录</button>
    </div>
  </div>
  <input type="hidden" name="action" value="login">
</form>
 
  </div>
</fieldset>

<script type="text/javascript">

function toMain(){
	location.href="/student/web/admin/login.jsp";
}
function toRoot(){
	location.href="/student/web/user/login.jsp";
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
	              }else if(code==2){
	            	  layer.msg("登陆失败，账号不存在")
	              }else if(code==3){
	            	  layer.msg("登陆失败，密码错误")
	              }
	        }
	    });
});
</script>


</body>
</html>