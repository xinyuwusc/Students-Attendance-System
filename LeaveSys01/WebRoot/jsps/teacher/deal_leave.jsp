<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    <title>请假审批</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css"></link>
	
  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			
  		});
  	</script>
  </head>
  
  <body>
  	<div class="container">
	    <form class="form-horizontal" action='<c:url value="/TeacherMgr?act=dealLeave"/>' method="post">
			<fieldset>
				<legend>学生请假信息</legend>
				
				<div class="control-group">
					<label class="control-label">学号</label>
					<input type="hidden" name="lid" value="${leave.lid}" />
					<div class="controls">
						<input disabled="disabled" type="text" value="${leave.sno}"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">姓名</label>
					<div class="controls">
						<input disabled="disabled" type="text" value="${leave.sname}" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">请假开始时间</label>
					<div class="controls">
						<input disabled="disabled" type="text" value="${leave.lbegintime}" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">请假结束时间</label>
					<div class="controls">
						<input disabled="disabled" type="text" value="${leave.lendtime}" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">请假理由</label>
					<div class="controls">
						<textarea rows="20" cols="20" name='lreason' disabled="disabled">${leave.lreason}</textarea>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">是否批准</label>
					<div class="controls">
						<label class="radio inline">
							<c:if test="${leave.lapprovestatus == '1' }">
								<input type="radio" name="lapprovestatus" value="1" checked="checked">是</input>
							</c:if>
							<c:if test="${leave.lapprovestatus != '1' }">
								<input type="radio" name="lapprovestatus" value="1">是</input>
							</c:if>
						</label>
						<label class="radio inline">
							<c:if test="${leave.lapprovestatus == '1' }">
								<input type="radio" name="lapprovestatus" value="2">否</input>
							</c:if>
							<c:if test="${leave.lapprovestatus != '1' }">
								<input type="radio" name="lapprovestatus" value="2" checked="checked">否</input>
							</c:if>
						</label>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">回复信息</label>
					<!--不同的选项要放在不同的label里面-->
					<div class="controls">
						<textarea rows="20" cols="20" name='lfeedback'>${leave.lfeekback }</textarea>
					</div>
				</div>
				
				<div class="form-actions"><!--把提交按钮和表单内容区分开来-->
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</fieldset>
		</form>
		
		<c:if test="${not empty msg}">
			<script type="text/javascript">
				alert('${msg}');
			</script>
		</c:if>
	</div>
  </body>
</html>
