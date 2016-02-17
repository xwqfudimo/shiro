<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>

<link rel="stylesheet" type="text/css" href="${ctxPath}/css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${ctxPath}/js/jquery.js"></script>
<script src="${ctxPath}/js/verificationNumbers.js"></script>
<script src="${ctxPath}/js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
  //登录
  $(".submit_btn").click(function(){
	  login();
  });
  
  $("input").keypress(function(e){
	var key = e.which; //e.which是按键的值
   	if (key == 13) { //回车事件
       login();
   	}
  });
});

function login() {
	if(validate()) {
		  var formData = {'username':$('#username').val(), 'password':$('#password').val()};
		  $.post("${ctxPath}/loginSubmit", formData, function(data){
			  var result = $.parseJSON(data.result);
			  
			  if(result.success == true) {
				  window.location.href = "${ctxPath}/index";
			  }
			  else {
				 $("#result").html(result.message); 
			  }
		  }, "json");
	  }		
}
</script>
</head>
<body>
<dl class="admin_login">
 <dt>
  <strong>后台管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="username"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" id="password"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
 </dd>
 <dd>
  <input type="button" value="立即登陆" class="submit_btn"/>
 </dd>
 <dd>
 <div id="result"></div>
 </dd>
</dl>
</body>
</html>
