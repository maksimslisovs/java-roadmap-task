<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Book list</title>
<h1><a href="<%=request.getContextPath()%>/">home</a></h1>
<body>

			<h3>List of books</h3>
			<a href="<%=request.getContextPath()%>/add/">add</a>
			<table cellpadding="5" cellspacing="5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Author Name</th>
						<th>Is at the disposal of the user</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${bookList}">
						<tr>
							<td>${book.id}</td>
							<td>${book.title}</td>
							<td>${book.authorName}</td>
							<c:choose>
                                <c:when test="${book.user != null}">
							        <td>${book.user.username}</td>
							        </c:when>
                                <c:otherwise>
                                    <td><a href="<%=request.getContextPath()%>/book/${book.id}">assign to user</a></td>
							        <td>
							    </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${book.user != null}">

                                    <td><a href="<%=request.getContextPath()%>/return/book/${book.id}">return Book</a>
                                </c:when>
                                <c:otherwise>
							        <td><a href="<%=request.getContextPath()%>/update/book/${book.id}">Update</a>
							        <td><a href="<%=request.getContextPath()%>/delete/book/${book.id}"onclick="return confirm('Do you really want to delete?')">Delete</a></td>
                                </c:otherwise>
                            </c:choose>
					    </tr>
					</c:forEach>
				</tbody>
			</table>

</body>
</html>