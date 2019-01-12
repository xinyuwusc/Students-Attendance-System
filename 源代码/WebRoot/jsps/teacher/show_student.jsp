<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>
  <head>

    <title>教师主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="plugin/loading/msgbox.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  	<script type="text/javascript" src="plugin/loading/msgbox.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			var currentPage = 1;
  			var hasNext = true;
  			$("#getnext").click(function(){
  				if(hasNext){
  					ZENG.msgbox.show("正在读取下一页的信息", 6);
  					
  					$.post("<c:url value='/TeacherMgr?act=getNextPageStudent'/>",
  					{"nextPage":currentPage + 1},
  					function(data){
  						ZENG.msgbox._hide();
  						if(data == "empty"){
  							hasNext = false;
  							//alert("没有下一页了！");
  							ZENG.msgbox.show("没有下一页了！", 5, 1500);
  						}else if(data == "error"){
  							//alert("出错啦!");
  							ZENG.msgbox.show("出错啦！", 5, 1500);
  						}else{
  							currentPage += 1;
  							//alert(data);
  							data = eval(data);
  							for(var i=0;i<data.length;i++){
  								//alert(data[i].sno);
  								var sex = data[i].sex == '1' ? 'sname' : '女';
  								var status = '<input type="hidden" value="'+data[i].sid+'"/>';
  								if(data[i].sdeletestatus == '1'){
  									status += '<select class="deletestatus">\
	  											<option selected="selected" value="1" >可用</option>\
	  											<option value="0">不可用</option>\
	  										</select>';
  								}else{
  									status += '<select class="deletestatus">\
  												<option selected="selected" value="1" >不可用</option>\
	  											<option value="0">可用</option>\
	  										</select>';
  								}
  								//使用jquery的dom操作进行动态加载
  								var str = "<tr>\
  												<td>"+data[i].sno+"</td>\
  												<td>" + data[i].sname+"</td>\
  												<td>" + sex + "</td>\
  												<td>" + data[i].sbirth + "</td>\
  												<td>" + data[i].stelephone +"</td>\
  												<td>" + status + "</td>\
  											</tr>";
  								$("#tablebody").append(str);
  								ZENG.msgbox.show("数据读取成功！", 4, 1500);
  							}
  						}
  					});
  				}else{
  					//alert("没有下一页了！");
  					ZENG.msgbox.show("没有下一页了！", 5, 1500);
  				}
  			});
  			
  			function getNotRead(){
  				$.post("<c:url value='/TeacherMgr?act=getNotRead'/>",{"tid":$user.tid},function(data){
  					$("#notreadcount").text(data);
  					if(data == '0'){
  						$("#notread").css("color","black");
  					}else{
  						$("#notread").css("color","red");
  					}
  				});
  			
  			}
  			window.setInterval(getNotRead, 5000);
  			
  			$("body").on("change", ".deletestatus", function (event) {
  			//$('.deletestatus').bind("change",function(){ 
	  			if(confirm("您确定要修改其可用状态吗？")){
	  				var status = $(this).children('option:selected').val();
	  				var sid = $(this).parent().children().eq(0).val();
	  				$.get('<c:url value="/TeacherMgr?act=chageStatus"/>&sid='+sid+'&status='+status,function(data){
	  					if(data == "1"){
	  						alert("修改成功");
	  					}else{
	  						alert("修改失败");
	  					}
	  				});	
	  			}
	  			 
			});
  		} );
  	</script>
  </head>
  
  <body>
  	<div class="container">
  		<div>
	  		<div class="alert">
		    	<h3 class="text-center">
		    		<strong>欢迎${user.taccount}</strong>
		    		<strong id="notread">
		    			您有
		    			<span id="notreadcount">
		    				
		    			</span>
		    			条未读信息
		    		</strong>
		    	</h3>
		    	<ul>
		    		<li>
					    <a href="<c:url value='/UserMgr?act=logout'/>">账号注销</a>
					</li>
					<li>
					    <a href="<c:url value='TeacherMgr?act=forAddStudent'/>">新增学生</a>
					</li>
					<li>
					    <a href="<c:url value='TeacherMgr?act=showLeaves'/>">查看请假记录</a>
			    	</li>
			    </ul>
		   </div>
	    </div>
  	
	  	<%-- 展示学生列表 --%>
	  	<div>
	  		<table class="table table-bordered table-striped">
	  			<thead>
	  				<th>学号</th>
	  				<th>姓名</th>
	  				<th>性别</th>
	  				<th>生日</th>
	  				<th>联系方式</th>
	  				<th>是否可用</th>
	  			</thead>
	  			<tbody id="tablebody">
	  				<c:forEach items="${list}" var="s">
		  				<tr>
		  					<td>${s.sno}</td>
		  					<td>${s.sname}</td>
		  					<td>
		  						<c:if test="${s.ssex ==  '1'}">
		  							男
		  						</c:if>
		  						<c:if test="${s.ssex ==  '2'}">
		  							女
		  						</c:if>
		  					</td>
		  					<td>${s.sbirth }</td>
		  					<td>${s.stelephone }</td>
		  					<td>
		  						<input type="hidden" value="${s.sid}"/>
		  						<select class="deletestatus">
		  							<c:if test="${s.sdeletestatus == '1' }">
		  								<option selected="selected" value="1">可用</option>
		  								<option value="0">不可用</option>
		  							</c:if>
		  							<c:if test="${s.sdeletestatus == '0' }">
		  								<option selected="selected" value="0">不可用</option>
		  								<option value="1">可用</option>
		  							</c:if>
		  						</select>
		  					</td>
		  				</tr>
	  				</c:forEach>
	  			</tbody>
	  		</table>
	  		<div class="row">
	  			<button class="span12 btn btn-block btn-primary" id="getnext">点击查看下一页</button>
	  		</div>
	  		
	  	</div>
	 </div> 
  </body>
</html>
