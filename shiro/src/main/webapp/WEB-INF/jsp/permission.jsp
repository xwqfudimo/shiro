<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>权限管理</title>
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
    <dd><a href="${ctxPath}/user" >用户管理</a></dd>
    <dd><a href="${ctxPath}/role">角色管理</a></dd>
    <dd><a href="${ctxPath}/permission" class="active">权限管理</a></dd>
   </dl>
  </li>
 </ul>
</aside>

<section class="rt_wrap content mCustomScrollbar">
 <div class="crumb"><span class="dq"><img src="${ctxPath }/images/home.png" style="width:20px;margin-top:-2px;"></span><span class="dq">权限管理</span></div>
 <div class="rt_content">
 	 <h2 class="h-title">权限管理</h2>	
 	 
 	 <a href="${ctxPath }/permission_add" class="link_btn">+新增权限</a>	
 	 <br><br>
 	 
 	 筛选：<input type="text" class="textbox textbox_225" placeholder="搜索权限名称" name="search_name" id="search_name"/><input type="button" value="搜索" class="group_btn" id="search"/>
 	 <h3>权限列表：</h3>
	 <table class="table">
       <tr>
        <th>权限名称</th>
        <th>权限url</th>
        <th>所属权限组</th>
        <th width="180px">操作</th>
       </tr>
       
       <s:iterator value="permissionList" id="perm">
	       <tr>
	        <td>${perm.name }</td>
	        <td>${perm.url }</td>
	        <td>${perm.group_name }</td>
	        <td align="center">
	         <a href="${ctxPath }/permission_edit?id=${perm.id}">编辑</a>
	         <a href="${ctxPath }/permission_del?id=${perm.id}" class="del">删除</a>
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
				 window.location = "${ctxPath}/permission";
			 }
		 });		 
	 });
	 //弹出：取消或关闭按钮
	 $(".falseBtn").click(function(){
		 $(".pop_bg").fadeOut();
	 });
	 
	 
	 $("#search").click(function(){
		 var search_name = $("#search_name").val();
		 window.location = "${ctxPath}/permission?search_name=" + search_name;
	 });
});
</script>

</body>
</html>
