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
	<link rel="stylesheet" type="text/css" href="css/list.css" >
<script type="text/javascript">
	function doDelete(cid){
		if(confirm("确认删除")){
			window.location.href="<c:url value='/deleteCustomerByCid.do?cid='/>"+cid;
		}
	}
	function doUpdate(cid){
				window.location.href="<c:url value='/getCustomerByCid.do?cid='/>"+cid;				
	}
</script>	
</head>
<body>
${msg }
<div class="main">
<div class="selection">
	<label>符合条件的客户信息<span>共${pb.totalRecored }条</span></label>
	<form action="<c:url value='/getCustomersByConditionPage.do'/>" method="get">
		<div class="inputs">
<!-- 		<input type="hidden" name="method" value="showByPage" /> -->
		<span class="notes">姓名：</span><input type="text" name="cname" value="${customer.cname }" class="col-lg-1" />
		<span class="notes">性别：</span><select name="gender" class="col-lg-1" ><option value="" <c:if test="${empty customer.gender }" >selected</c:if>>请选择</option><option value="男" <c:if test="${customer.gender eq '男' }" >selected</c:if>>男</option><option value="女" <c:if test="${customer.gender eq '女' }" >selected</c:if>>女</option></select>
		<span class="notes">电话：</span><input type="text" name="cellphone" value="${customer.cellphone }" class="col-lg-2" />
		<span class="notes">邮箱：</span><input type="text" name="email" value="${customer.email }" class="col-lg-2" />
		<input type="submit" value="查询" class="btn-primary col-lg-1" />	
		</div>
	</form>

</div>	
		
<div class="list">
	<table class="table table-hover">
	<thead class="active">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>生日</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="customer" begin="0"  end="9"  items="${pb.beanList }">
		<tr>
			<td>${customer.cid }</td>
			<td>${customer.cname }</td>
			<td>${customer.gender }</td>
			<td>${customer.birthday }</td>
			<td>${customer.cellphone }</td>
			<td>${customer.email }</td>
			<td>${customer.description }</td>
			<td><a class="do" href="javascript:doUpdate('${customer.cid }')">修改</a>&nbsp;&nbsp;<a class="do" href="javascript:doDelete('${customer.cid }')">删除</a></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>	
</div>
	
<!-- pageIndex由客户端发送的index决定，如果没有值，则由Servlet设定默认值为1 -->
<div class="footer">
<p><form><span class="info">共${pb.totalPage }页，当前为第${pb.pageIndex }页，</span><span class="info">到第<input id="topage" type="number" name="topage" value="${pb.pageIndex }" />页<a class="do" href="javascript:(topage())"><button type="button" class="btn myb">确定</button></a></span></form></p>
	<ul class="pages">
	<c:if test="${pb.pageIndex>1 }"><!-- 当当前获取的页码大于1时，才会显示上一页的选项 -->
		<li class="pageextremity"><a href="${pb.url }&pageIndex=${pb.pageIndex-1 }"><span class="pagelink">&laquo;上一页</span></a></li>	
	</c:if>
	<!-- forEach循环遍历，由查到的总页数决定显示页码数量 -->

	<c:forEach var="i" begin="${pb.pageBegin }" end="${pb.pageEnd }">
		<c:choose>
			<c:when test="${pb.pageIndex==i }"><li class="pageitemfocu"><span class="pagelink">${i }</span></li></c:when>
			<c:otherwise>
				<li class="pageitem"><a href="${pb.url }&pageIndex=${i }"><span class="pagelink">${i }</span></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pb.pageIndex<pb.totalPage }">
		<li class="pageextremity"><a href="${pb.url }&pageIndex=${pb.pageIndex+1 }"><span class="pagelink">下一页&raquo;</span></a></li>
	</c:if>	
	</ul>
	</div>
</div>
<script>
	function topage(){
		var page = document.getElementById("topage").value;
		window.location.href="<c:url value='/CustomerServlet?method=showByPage&pageIndex='/>"+page;
	}
</script>	
</body>
</html>

<!-- 	<li class="active"><a href="#">1</a></li>
	<li class="disabled"><a href="#">2</a></li> -->

