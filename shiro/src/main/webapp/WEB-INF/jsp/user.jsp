<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="${ctxPath}/css/style.css" />

<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5.js"></script>
<![endif]-->
<script src="${ctxPath}/js/jquery.js"></script>
<script src="${ctxPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>

</head>
<body>
<!--header-->
<header>
 <h1><img src="${ctxPath}/images/admin_logo.png"/></h1>
 <ul class="rt_nav">
  <li><a href="#" class="set_icon">账号设置</a></li>
  <li><a href="${ctxPath }/logout" class="quit_icon">安全退出</a></li>
 </ul>
</header>

<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
 <h2>菜单</h2>
 <ul>
  <li>
   <dl>
    <dt>系统管理</dt>
    <dd><a href="${ctxPath}/user" class="active">用户管理</a></dd>
    <dd><a href="${ctxPath}/role">角色管理</a></dd>
    <dd><a href="${ctxPath}/permission">权限管理</a></dd>
   </dl>
  </li>
 </ul>
</aside>

<section class="rt_wrap content mCustomScrollbar">
 <div class="crumb"><span class="dq"><img src="${ctxPath }/images/home.png" style="width:20px;margin-top:-2px;"></span><span class="dq">用户管理</span></div>
 <div class="rt_content">
 	 <h2 class="h-title">用户管理</h2>	
 	 
 	 <a href="${ctxPath }/user_add" class="link_btn">+新增用户</a>	
 	 <h3>用户列表：</h3>
	 <table class="table">
       <tr>
        <th>用户名</th>
        <th width="180px">操作</th>
       </tr>
       
       <s:iterator value="userList" id="user">
	       <tr>
	        <td>${user.username }</td>
	        <td align="center">
	         <a href="${ctxPath }/user_edit?id=${user.id}">编辑</a>
	         <a href="${ctxPath }/user_del?id=${user.id}" class="del">删除</a>
	        </td>
	       </tr>
	   </s:iterator>    
    </table>
 
 </div>
</section>

<!-- 弹出删除确认窗口 -->
<section class="pop_bg">
      <div class="pop_cont">
       <!--title-->
       <h3>提示</h3>
       <!--以pop_cont_text分界-->
       <div class="pop_cont_text">
       	 确认删除数据吗？
       </div>
       <!--bottom:operate->button-->
       <div class="btm_btn">
        <input type="button" value="确认" class="input_btn trueBtn"/>
        <input type="button" value="关闭" class="input_btn falseBtn"/>
       </div>
      </div>
</section>

<script>
$(document).ready(function(){
	 var url;
	 
	 //弹出文本性提示框
	 $(".del").click(function(e){
		 e.preventDefault();
		 url = $(this).attr("href");
		 $(".pop_bg").fadeIn();
	 });
	 //弹出：确认按钮
	 $(".trueBtn").click(function(){
		 $.get(url, function(data){
			 var result = $.parseJSON(data.result);
			 
			 if(result.success == true) {
				 $(".pop_bg").fadeOut();
				 window.location = "${ctxPath}/user";
			 }
		 });		 
	 });
	 //弹出：取消或关闭按钮
	 $(".falseBtn").click(function(){
		 $(".pop_bg").fadeOut();
	 });
});
</script>

</body>
</html>
