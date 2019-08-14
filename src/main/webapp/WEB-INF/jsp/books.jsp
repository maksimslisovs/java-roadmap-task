<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book list</title>
<body>

	<c:choose>
		<c:when test="${'1' != null}">
			<h3>List of Users</h3>
			<table cellpadding="5" cellspacing="5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Author Name</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="books" items="${book}">
						<tr>
							<td>${book.id}</td>
							<td>${book.name}</td>
							<td>${book.authorName}</td>

					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
        No User found in the DB!
        </c:otherwise>
	</c:choose>
</body>
</html>