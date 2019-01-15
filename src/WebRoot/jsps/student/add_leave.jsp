<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>
  <head>

    <title>新增请假申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.css" type="text/css"></link>
  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  </head>
  
  <body>
    <div class="container">
     
    <!-- 新增请假申请 -->
    <form class="form-horizontal" action='<c:url value="/StudentMgr?act=addLeave"/>' method="post">
		<fieldset>
			<legend>新增请假申请</legend>
			
			<div class="control-group">
				<label class="control-label">请假开始时间</label>
				<div class="controls">
					<input class="sang_Calender" type="text" name="lbegintime" required="required"/>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">请假结束时间</label>
				<div class="controls">
					<input class="sang_Calender" type="text" name="lendtime" required="required"/>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">请假理由</label>
				<div class="controls">
					<textarea rows="20" cols="20" name='lreason' required="required"></textarea>
				</div>
			</div>
			
			<div class="form-actions"><!--把提交按钮和表单内容区分开来-->
				<button type="submit" class="btn btn-primary">提交申请</button>
			</div>
		</fieldset>
	<form>
  </body>
  <script type="text/javascript" src="plugin/datetime/datetime.js"></script>
</html>
