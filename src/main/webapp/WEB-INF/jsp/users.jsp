<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book list</title>
<h1><a href="<%=request.getContextPath()%>/">home</a></h1>
<body>

			<h3>List of users</h3>
			<table cellpadding="5" cellspacing="5">
				<thead>
					<tr>
						<th>User ID</th>
						<th>User Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${usersList}">
						<tr>
							<td>${user.id}</td>
							<td>${user.username}</td>
<%--							<c:choose>
                                <c:when test="${empty user.books}">
							        <td><a href="<%=request.getContextPath()%>/update/user/${user.id}">Update</a>
                                    <td><a href="<%=request.getContextPath()%>/delete/user/${user.id}"onclick="return confirm('Do you really want to delete?')">Delete</a></td>
                                </c:when>
							    <c:otherwise>

							        <c:forEach var="book" items="${user.books}">
                                        <td>${book.title}</td>
                                    </c:forEach>

                                </c:otherwise>
                            </c:choose>
--%>
					    </tr>
					</c:forEach>
				</tbody>
			</table>

</body>
</html>