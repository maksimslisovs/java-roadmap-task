<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Add book</title>
<body>
<h1><a href="<%=request.getContextPath()%>/">home</a></h1>
	<h3>Add Book</h3>
	<form method="POST" name="add_book" action="<%=request.getContextPath()%>/add/book">
	    Name: <input name="title"  type="text" />
		<br /><br />
		Author Name: <input name="authorName"  type="text" />
        <br /><br />
		<input value="Add Book" type="submit" />
	</form>

		<form method="POST" name="add_user" action="<%=request.getContextPath()%>/add/user">
    	    User Name: <input name="username"  type="text" />
    	            <br /><br />
            		<input value="Add User" type="submit" />
    	</form>
</body>
</html>