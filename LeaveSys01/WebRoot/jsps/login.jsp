<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML>
<html>
  <head>    
    <title>学生请假系统登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css"></link>

  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$("#tipname").show().text("请输入用户名").css("color","red");
  			$("#tippass").show().text("请输入密码").css("color","red");
  			
  			var usernameKeyUp = function(){
  				var input = $("#username").val();
  				if(input == null || input == ""){
  					$("#tipname").text("用户名不能为空！");
  					$("#tipname").show();
  					return false;
  				}else{
  					$("#tipname").text("");
  					$("#tipname").hide();
  					return true;
  				}
  			}
  			
  			$("#username").keyup(usernameKeyUp);
  			
  			var userpassKeyUp = function(){
  				var input = $("#userpass").val();
  				if(input == null || input == ""){
  					$("#tippass").text("密码不能为空！");
  					$("#tippass").show();
  					return false;
  				}else{
  					$("#tippass").text("");
  					$("#tippass").hide();
  					return true;
  				}
  			}
  			
  			$("#userpass").keyup(userpassKeyUp);
  			
  			$("#commit").click(function(){
  				return (usernameKeyUp() && userpassKeyUp()) ? true : false;
  			});
  		});
  	</script>
  	<style>
  		.required{
  			color:red;
  		}
  	</style>
  </head>
  
  <body>
  	<div class="container">
	    <form class="form-horizontal" action="http://localhost:8080/LeaveSys01/UserMgr?act=login" method="post">
	    	<legend>登录信息</legend>
	    	<div class="control-group">
		    	<label  class="control-label">
		    		用户名
		    		<span class="required">*</span>
		    	</label>
		    	<div class="controls">
			    	<input id="username" type="text" name="username"/>
			    	
			    	<p id="tipname" class="help-block"></p>
		    	</div>
	    	</div>
	    	
	    	<div class="control-group">
		    	<label  class="control-label">
		    		密码
		    		<span class="required">*</span>
		    	</label>
		    	<div class="controls">
			    	<input id="userpass" type="password" name="userpass"/>
			    	
			    	<p id="tippass" class="help-block"></p>
		    	</div>
	    	</div>
	    	
	    	<div class="control-group">
		    	<label  class="control-label">
		    		类别
		       	</label>
		       	<div class="controls">
			       	<input class="radio" type="radio" name="type" value="1" checked="checked">教师</input>
			    	<input  class="radio" type="radio" name="type" value="2">学生</input>
			    </div>
	       	</div>
	       
	       	<div class="form-actions">
					<input id="commit" class="btn btn-primary" type="submit" value="登录"/> 
			</div>
	       	
	    </form>
    </div>
    
    <script type="text/javascript">
    	var get = "${msg}";
    	if(get != null && get != ""){
    	    alert(get);
    	}
    </script>
  </body>
</html>
