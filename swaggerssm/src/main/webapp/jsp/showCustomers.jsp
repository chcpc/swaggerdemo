<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/javaScript" href="js/jqurey.min.js" >
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/javaScript" href="js/bootstrap.min.js" >
	<link rel="stylesheet" type="text/css" href="css/showCustomers.css" >
</head>
<body>
${msg }
<div class="in-form">
	<form action="<c:url value='/CustomerServlet'/>" method="get">
		<input type="hidden" name="method" value="show"/>
		<input type="text" name="cid" />${errors.cid }<br/>
		<input type="text" name="cname" />${errors.cname }<br/>
		<select name="gender"><option></option><option>男</option><option>女</option></select>${errors.gender }<br/>
		<input type="submit" value="ok" class="btn-primary"/><br/>
	</form>
</div>	
<div class="c-list">
	<table class="table table-hover">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>生日</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>描述</th>
		</tr>
	<c:forEach var="customer" begin="0"  end="9"  items="${customers }">
		<tr>
			<td>${customer.cid }</td>
			<td>${customer.cname }</td>
			<td>${customer.gender }</td>
			<td>${customer.birthday }</td>
			<td>${customer.cellphone }</td>
			<td>${customer.email }</td>
			<td>${customer.description }</td>
		</tr>
	</c:forEach>
	</table>
</div>	
	<!-- 3. -->
	<div class="first-footer">
		<div class="in-footer">
			<ul class="first-page">
				<li class="page-item"><a class="page-link">首页</a></li>
				<li class="page-item"><a class="page-link">前一页</a></li>			
				
				<li class="page-item"><a class="page-link">10</a></li>
				<li class="page-item"><a class="page-link">后一页</a></li>
				<li class="page-item"><a class="page-link">末页</a></li>
			</ul>

		</div>
	</div>
	<div id="footer">
		<div class="inner-footer">
			<ul class="page">
				<li class="page-item"><span class="page-link" id="firstpage">首页</span></li>
				<li class="page-item"><span class="page-link" id="previouspage">前一页</span></li>
				<li class="page-item">
				<span class="page-link">
				<span id="currentpage">1</span>/<span class="totalpage">1</span>
				</span>
				</li>
				<li class="page-item"><span class="page-link" id="nextpage">后一页</span></li>
				<li class="page-item"><span class="page-link" id="lastpage">末页</span></li>
			</ul>
			<div class="form">
				<span>共<span class="totalpage">1</span>页</span>
				<span>，到第</span>
				<input id="input-page" type="number" value="2" min="1" max="3">
				<span>页</span>
				<input type="button" class="input-page-submit" value="确定" id="to-page"/>
			</div>
		</div>
	</div>
</body>
</html>