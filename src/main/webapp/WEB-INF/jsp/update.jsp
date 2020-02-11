<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book Update</title>
<h1><a href="<%=request.getContextPath()%>/">home</a></h1>
<body>

	<h3>Update Book</h3>
	<form method="POST" name="update_book"
		action="<%=request.getContextPath()%>/update/book">
		<input hidden="hidden" name="id" value="${id}" type="text" />
		Name: <input name="title" value="${bookDetail.title}" type="text" />
		<br />
		<br />Author Name: <input name="aname" value="${bookDetail.authorName}"
			type="text" />
		<br />
		<br /> <input value="Update Book" type="submit" />
	</form>
</body>
</html>