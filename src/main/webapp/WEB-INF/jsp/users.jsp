<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book list</title>
<body>

			<h3>List of books</h3>
			<table cellpadding="5" cellspacing="5">
				<thead>
					<tr>
						<th>User ID</th>
						<th>User Name</th>
						<th>Book ID</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${usersList}">
						<tr>
							<td>${user.userId}</td>
							<td>${user.userName}</td>
							<td>${user.ID}</td>

					    </tr>
					</c:forEach>
				</tbody>
			</table>

</body>
</html>