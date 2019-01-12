<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>
  <head>

    <title>我的请假列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.css" type="text/css"></link>
  	
  </head>
  
  <body>
  	<div class="container">
  		<div>
  			<div class="alert text-center">
		    	<h3><strong>欢迎 ${user.sname }</strong></h3>
		    </div>
		    <div class="alert alert-info">
			    <a href="<c:url value='/UserMgr?act=logout'/>">账号注销</a>
			    <a href="<c:url value='/StudentMgr?act=forAddLeave'/>">新增请假申请</a>
			    
		    </div>
    	</div>
    	
    	<div>
    		<table class="table table-bordered table-striped">
		  <thead>
			<tr>
			  <th>申请时间</th>
			  <th>请假起始时间</th>
			  <th>请假结束时间</th>
			  <th>请假理由</th>
			  <th>审批状态</th>
			  <th>老师回复</th>
			</tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${leaveList}" var="leave">
				<tr>
				  <td>${leave.lapplytime}</td>
				  <td>${leave.lbegintime}</td>
				  <td>${leave.lendtime}</td>
				  <td>${leave.lreason}</td>
				  <td>
				  	<c:if test="${leave.lapprovestatus == '0'}">未审批</c:if>
				  	<c:if test="${leave.lapprovestatus == '1'}">通过</c:if>
				  	<c:if test="${leave.lapprovestatus == '2'}">不通过</c:if>
				  </td>
				  <td>${leave.lfeedback}</td>	
				</tr>
			</c:forEach>
		  </tbody>
		</table>
    	</div>  	
    </div>
  </body>
</html>
