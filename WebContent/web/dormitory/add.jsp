<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>

<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>

</head>
<body>

<fieldset class="layui-elem-field">
  <legend>添加</legend>
  <div class="layui-field-box">
 
 <form class="layui-form" >
  <div class="layui-form-item">
    <label class="layui-form-label">宿舍编号</label>
    <div class="layui-input-inline">
      <input type="text" name="codeDormitory" required  lay-verify="required" 
      placeholder="请输入宿舍编号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">宿舍容量</label>
    <div class="layui-input-inline">
      <input type="text" name="capacityDormitory" required lay-verify="required" 
      placeholder="请输入宿舍容量" autocomplete="off" class="layui-input">
    </div>
<!--     <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">宿舍价格</label>
    <div class="layui-input-inline">
      <input type="text" name="priceDormitory" required lay-verify="required" 
      placeholder="请输入宿舍价格" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
        <input type="button" value="确定" class="layui-btn" lay-submit lay-filter="add">
        <input type="button" value="关闭" class="layui-btn layui-btn-primary" onclick="closeThis()">
    </div>
  </div>
  <input type="hidden" name="action" value="add">
</form>
 
  </div>
</fieldset>
 
<script type="text/javascript">
function closeThis(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
// form.render();
form.on("submit(add)",function(data){
 //当前容器的全部表单字段，名值对形式：{name: value}
	   $.ajax({
	        url:"/student/dormitory",
	        data:data.field,
	        dataType:"json",
	        type:"post",
	        success:function(data){//请求成功返回数据时候执行的方法
	            //data请求返回的数据{code:1}
	              var code = data.code;
	              if(code==1){
	            	  layer.msg("添加成功", closeThis)
	              }else if(code==2){
	            	  layer.msg("添加失败, 宿舍已存在")
	              }else {
	            	  layer.msg("添加失败")
	              }
	        }
	    });
});
</script>

</body>
</html>