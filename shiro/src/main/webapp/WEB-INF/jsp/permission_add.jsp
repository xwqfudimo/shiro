<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>新增权限</title>
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
    <dd><a href="${ctxPath}/role">角色管理</a></dd>
    <dd><a href="${ctxPath}/permission" class="active">权限管理</a></dd>
   </dl>
  </li>
 </ul>
</aside>

<section class="rt_wrap content mCustomScrollbar">
 <div class="crumb"><span class="dq"><img src="${ctxPath }/images/home.png" style="width:20px;margin-top:-2px;"></span><span class="dq">新增权限</span></div>
 <div class="rt_content">
 	<div><a href="#" onclick="history.go(-1)"><span class="dq"><img src="${ctxPath }/images/back.png" style="width:20px;margin-top:-2px;"></span><span class="dq">返回</span></a></div>
    <section>
      <h2><strong style="color:grey;">新增权限</strong></h2>
      <form method="post">
	      <ul class="ulColumn2">
	       <li>
	        <span class="item_name" style="width:120px;">权限名称：</span>
	        <input type="text" class="textbox textbox_295" name="name" id="name" placeholder="输入权限名称"/>
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">权限url：</span>
	        <input type="text" class="textbox textbox_295" name="url" id="url" placeholder="输入权限url"/>
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">所属权限组：</span>
	        <input type="text" class="textbox textbox_295" name="group_name" id="group_name" placeholder="输入所属权限组"/>
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;"></span>
	        <input type="submit" class="link_btn" id="submit" value="提交"/>
	       </li>
	       <li><div id="result"></div></li>
	      </ul>
      </form>
     </section>
 </div>
</section>

<script>
$(document).ready(function() {
  $("#submit").click(function(e){
	  e.preventDefault();
	  
	  var formData = {'name':$('#name').val(), 'url':$('#url').val(), 'group_name':$('#group_name').val()};
	  $.post("${ctxPath }/permission_save", formData, function(data){
		  var result = $.parseJSON(data.result);
		  
		  if(result.success == true) {
			  window.location.href = "${ctxPath}/permission";
		  }
		  else {
			 $("#result").html(result.message); 
		  }
	  }, "json");
  });
});
</script>

</body>
</html>
