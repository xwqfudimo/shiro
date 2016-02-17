<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>新增角色</title>
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
 <div class="crumb"><span class="dq"><img src="${ctxPath }/images/home.png" style="width:20px;margin-top:-2px;"></span><span class="dq">新增角色</span></div>
 <div class="rt_content">
 	<div><a href="#" onclick="history.go(-1)"><span class="dq"><img src="${ctxPath }/images/back.png" style="width:20px;margin-top:-2px;"></span><span class="dq">返回</span></a></div>
    <section>
      <h2><strong style="color:grey;">新增角色</strong></h2>
      <form method="post">
	      <ul class="ulColumn2">
	       <li>
	        <span class="item_name" style="width:120px;">角色标识名：</span>
	        <input type="text" class="textbox textbox_295" name="name" id="name" placeholder="输入角色标识名"/>
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">角色描述：</span>
	        <input type="text" class="textbox textbox_295" name="description" id="description" placeholder="输入角色描述"/>
	       </li>
	       <li>
	        <span class="item_name" style="width:120px;">拥有权限：</span>
	        
	        <ul class="permul">
		        <s:iterator value="#request.allPermissionMap" id="entry">
		        	<li>
			        	 <table class="table perm">
				        	<tr><th><s:property value="key"/></th></tr>
				        	<s:iterator value="#entry.value" id="p">
				        		<tr><td><input type="checkbox" name="permId" value="${p.id }" pkey="<s:property value="key"/>"> ${p.name }</td></tr>
				        	</s:iterator>
				        	 
				        	<tr><td><a href="#" class="selectAll" pkey="<s:property value="key"/>">全选</a>/<a href="#" class="selectInverse" pkey="<s:property value="key"/>">反选</a></td></tr>
				        </table>
			        </li>
		        </s:iterator>
	       </ul>
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
	  
	  var permIds = '';
	  $("input:checked[name='permId']").each(function(){
		  permIds += ',' + $(this).val();
	  });
	  permIds = permIds.substring(1);
	  
	  var formData = {'name':$('#name').val(), 'description':$('#description').val(), 'permIds':permIds};
	  $.post("${ctxPath }/role_save", formData, function(data){
		  var result = $.parseJSON(data.result);
		  
		  if(result.success == true) {
			  window.location.href = "${ctxPath}/role";
		  }
		  else {
			 $("#result").html(result.message); 
		  }
	  }, "json");
  });
  
  $(".selectAll").click(function(){
  	var pkey = $(this).attr("pkey");
  	$("input:checkbox[pkey=" + pkey + "]").prop("checked", true);
  });
  
  $(".selectInverse").click(function(){
	var pkey = $(this).attr("pkey");  
  	$.each($("input:checkbox[pkey=" + pkey + "]"), function(i, n){
  		if($(n).prop("checked") == true) {
  			$(n).removeAttr("checked");
  		} 
  		else {
  			$(n).prop("checked", true);
  		}
  	});
  });
  
  
});
</script>

</body>
</html>
