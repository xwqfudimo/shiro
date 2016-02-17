<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>角色详情</title>
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
    <dd><a href="${ctxPath}/user">用户管理</a></dd>
    <dd><a href="${ctxPath}/role" class="active">角色管理</a></dd>
    <dd><a href="${ctxPath}/permission">权限管理</a></dd>
   </dl>
  </li>
 </ul>
</aside>

<section class="rt_wrap content mCustomScrollbar">
 <div class="crumb"><span class="dq"><img src="${ctxPath }/images/home.png" style="width:20px;margin-top:-2px;"></span><span class="dq">角色详情</span></div>
 <div class="rt_content">
 	<div><a href="#" onclick="history.go(-1)"><span class="dq"><img src="${ctxPath }/images/back.png" style="width:20px;margin-top:-2px;"></span><span class="dq">返回</span></a></div>
    <section>
      <h2><strong style="color:grey;">角色详情</strong></h2>
      <form method="post">
	      <ul class="ulColumn2">
	       <li>
	        <span class="item_name" style="width:120px;">角色标识名：</span>
	        ${role.name }
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">角色描述：</span>
	        ${role.description }
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">拥有权限：</span>
	        
	        <ul class="permul">
		        <s:iterator value="#request.permMap" id="entry">
		        	<li>
			        	 <table class="table perm">
				        	<tr><th><s:property value="key"/></th></tr>
				        	<s:iterator value="#entry.value" id="p">
				        		<tr><td>${p.name }</td></tr>
				        	</s:iterator>
				        </table>
			        </li>
		        </s:iterator>
	       </ul>
	       </li>
	      </ul>
      </form>
     </section>
 </div>
</section>

</body>
</html>
