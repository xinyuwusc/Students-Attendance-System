<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML>
<html>
  <head>

    <title>新增学生</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$("#sno").keyup(function(){
  				var sno = $(this).val();
  				
  				$.post("http://localhost:8080/LeaveSys01/TeacherMgr?act=isExistBySno", 
  					{ "sno": sno},
				   	function(data){
				     	$("#snotip").text(data);
				   	}
				);
  				
  			});
  		});
  	</script>
  	
  	</head>
  
  <body>
  <div class="container">
    <form class="form-vertical" action="http://localhost:8080/LeaveSys01/TeacherMgr?act=addStudent" method="post">
    	<legend>新增学生</legend>
    	<label>
    		学号：<input id="sno" type="text" name="sno" />
    		<span id="snotip"></span>
    	</label>
    	<br/>
    	<label>
    		姓名：<input type="text" name="sname" />
    	</label>
    	<br/>
    	<label>
    		初始密码：<input type="text" name="spassword_" value="123123" disabled="disabled" />
    		<input type="hidden" name="spassword" value="123123"/>
    	</label>
    	<br/>
    	<label>
    		性别：
    		<input type="radio" name="ssex" value="1"/>男
    		<input type="radio" name="ssex" value="2"/>女	
    	</label>
    	<br/>
    	<label>
    		生日：<input class="sang_Calender" type="text" name="sbirth" />
    	</label>
    	<br/>
    	<label>
    		联系方式：<input type="text" name="stelephone" />
    	</label>
    	<br/>

    	<div class="form-actions">
			<input type="submit" value="提交" class="btn btn-primary"/>
		</div>
    	
    </form>
   </div>
    <script type="text/javascript" src="plugin/datetime/datetime.js"></script>
  </body>
</html>
