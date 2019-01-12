<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    <title>学生请假申请列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css"></link>
	
  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  	<script type="text/javascript" src="plugin/zdialog/zDialog.js"></script>
  	
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$(".approve").click(function(){
  				var lid = $(this).prev().val();
  				
  				//打开一个页面
  				var diag = new Dialog();
				diag.Width = 900;
				diag.Height = 550;
				diag.Title = "请假审批";
				diag.URL = "<c:url value='/TeacherMgr?act=forDealLeave'/>"+"&lid="+lid;
				diag.show();
  			});
  			
  			$("#refresh").click(function(){
  				//页面刷新
  				window.location.href = "<c:url value='TeacherMgr?act=showLeaves'/>";
  			});
  		});
  	</script>
  </head>
  
  <body>
    <div class="container">
    	<div class="row">
 
    	</div>
    	<table class="table table-bordered table-striped">
    		<thead>
    			<th>申请时间</th>
    			<th>请假起始时间</th>
    			<th>请假结束时间</th>
    			<th>请假理由</th>
    			<th>审批状态</th>
    			<th>我的回复</th>
    			<th>操作</th>
    	
    		<tbody>
    			<c:forEach items="${leaveList}" var="l">
    				<tr>
    					<td>${l.lapplytime}</td>
    					<td>${l.lbegintime}</td>
    					<td>${l.lendtime}</td>
    					<td>${l.lreason}</td>
    					<td>
    						<c:if test="${l.lapprovestatus == '0'}">
    							未审批
    						</c:if>
    						<c:if test="${l.lapprovestatus == '1'}">
    							审批通过
    						</c:if>
    						<c:if test="${l.lapprovestatus == '2'}">
    							审批不通过
    						</c:if>
    					</td>
    					<td>${l.lfeedback}</td>
    					<td>
    						<input type="hidden" value="${l.lid}"/>
    						<input type="button" class="btn btn-primary approve" value="审批"/>
    					</td>
    				</tr>
    			</c:forEach> 			
    		</tbody>
    	</table>
    </div>
  </body>
</html>
