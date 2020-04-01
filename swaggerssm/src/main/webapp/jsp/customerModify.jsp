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
	<link rel="stylesheet" type="text/css" href="css/customerModify.css" >
</head>
<body>
${msg }
<div class="main">
<div class="selection">
	<label>客户信息修改</label>
	<form action="<c:url value='/updateCustomer.do'/>" method="get">
		<div class="inputs">
<!-- 		<input type="hidden" name="method" value="updateCustomer" /> -->
		<input type="hidden" name="cid" value="${customer.cid }" />
		<span class="notes">姓名：</span><input type="text" name="cname" value="${customer.cname }" class="col-lg-1" /><br/>
		<span class="notes">性别：</span><select name="gender" class="col-lg-1" ><option value="" <c:if test="${empty customer.gender }" >selected</c:if>>请选择</option><option value="男" <c:if test="${customer.gender eq '男' }" >selected</c:if>>男</option><option value="女" <c:if test="${customer.gender eq '女' }" >selected</c:if>>女</option></select><br/>
		<span class="notes">生日：</span><input type="text" name="birthday" value="${customer.birthday }" class="col-lg-2" /><br/>
		<span class="notes">电话：</span><input type="text" name="cellphone" value="${customer.cellphone }" class="col-lg-2" /><br/>
		<span class="notes">邮箱：</span><input type="text" name="email" value="${customer.email }" class="col-lg-2" /><br/>
		<span class="notes">描述：</span><input type="text" name="description" value="${customer.description }" class="col-lg-2" /><br/>
		<input type="reset" value="重置"  class="btn-primary col-lg-1" /><input type="submit" value="确认" class="btn-primary col-lg-1" /><br/>
		</div>
	</form>

</div>	
</div>	

</body>
</html>


