<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book Update</title>
<body>
	<c:if test="${not empty msg}">
        ${msg}
    </c:if>
	<h3>Update Book</h3>
	<form method="POST" name="update_book"
		action="<%=request.getContextPath()%>/update/user">
		<input hidden="hidden" name="userId" value="${userId}" type="text" />
		Name: <input name="userName" value="${userDetail.userName}" type="text" />
		<br />
		<br /> <input value="Update user" type="submit" />
	</form>
</body>
</html>