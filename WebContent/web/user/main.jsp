<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>学生信息管理系统</title>
<link rel="stylesheet" href="/student/layui/css/layui.css" media="all">
<script type="text/javascript" src="/student/layui/layui.all.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">学生信息管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="/student/resource/123.png" class="layui-nav-img">
          用户
        </a>
      </li>
      <li class="layui-nav-item"><a href="/student/web/mainPage/login.jsp">Sign out</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">信息维护</a>
          <dl class="layui-nav-child" >
            <dd><a href="javascript:openUrl('/student/web/user/listSTU.jsp');">学生信息维护</a></dd>
            <dd><a href="javascript:openUrl('/student/web/user/listCLA.jsp');">班级信息维护</a></dd>
            <dd><a href="javascript:openUrl('/student/web/user/listCOU.jsp');">课程信息维护</a></dd>
            <dd><a href="javascript:openUrl('/student/web/user/listDOR.jsp');">宿舍信息维护</a></dd>
            <dd><a href="javascript:openUrl('/student/web/user/listRES.jsp');">成绩信息维护</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">数据统计</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:openUrl('/student/web/count/countSTU.jsp');">班级人数统计</a></dd>
            <dd><a href="javascript:openUrl('/student/web/count/countDOR.jsp');">宿舍人数统计</a></dd>
            <dd><a href="javascript:openUrl('/student/web/count/countRE.jsp');">考试成绩人数统计</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe name="frameA" width="98%" height="98%"></iframe>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © edu -- 学生信息管理系统  -- 2020
  </div>
</div>
<script src="/student/layui/layui.all.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
function openUrl(url){
	window.open(url,"frameA");
}
</script>
</body>
</html>
</html>