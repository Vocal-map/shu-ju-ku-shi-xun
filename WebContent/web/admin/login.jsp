<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登陆</title>
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
  <div class="layui-form-item" style="width: 400px;
height: 260px;
padding: 13px;
position: absolute;
left: 50%;
top: 50%;
margin-left: -200px;
margin-top: -150px;">

    <label class="layui-form-label" style="font-family:黑体; font-weight:40%;font-size:30px; ">账号</label>
    <div class="layui-input-inline">
      <!-- <input type="text" name="userAdmin" required  lay-verify="required" readonly="readonly"
      placeholder="root" autocomplete="off" class="layui-input"> -->
      <select name='userAdmin'>
  			<option value='root'>root</option>
		</select> 
    </div>
  </div>
  <div class="layui-form-item" style="width: 400px;
height: 260px;
padding: 13px;
position: absolute;
left: 50%;
top: 50%;
margin-left: -200px;
margin-top: -50px;">


    <label class="layui-form-label" style="font-family:黑体; font-weight:40%;font-size:30px; ">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="passAdmin" required lay-verify="required" 
      placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
<!--     <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
  </div>
  
  
    <div class="layui-form-item" >
    <div class="layui-input-block" style="text-align:center; line-height:100px; border: 1px;
top: 40%; left: 38%; position: absolute; margin-top: 100px;">
        <button type="button" class="layui-btn" lay-submit lay-filter="login">登陆</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        <button type="button" class="layui-btn" onclick="toRoot()">忘记密码</button>
    </div>
  </div>
  <input type="hidden" name="action" value="login">
</form>
 
  </div>
</fieldset>

<script type="text/javascript">
function toMain(){
	location.href="/student/web/admin/main.jsp";
}
function toRoot(){
	layer.msg("请联系产品公司进行密码修改");
}
/* function closeThis(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
} */
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
form.render('select');
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
form.render('select');
</script>


</body>
</html>