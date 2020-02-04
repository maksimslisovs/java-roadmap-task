<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <title>Book</title>
 <h1><a href="<%=request.getContextPath()%>/">home</a></h1>
 <body>

 	<h2>Book Information</h2>
    Id : ${book.id}
 	<br /> Title : ${book.title}
 	<br /> Author Name ${book.authorName}
 	<br />

    <h2>User list</h2>
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
							<td><form method="POST" name="assign_book"
                                 action="<%=request.getContextPath()%>/assign/user">
                                 <input hidden="hidden" name="id" value="${id}" type="text" />
                                 <input hidden="hidden" name="name" value="${book.title}" type="text" />
                                 <input hidden="hidden" name="aname" value="${book.authorName}" type="text" />
                                 <input hidden="hidden" name="userId" value="${user.id}" type="text" />
                                 <input hidden="hidden" name="username" value="${user.username}" type="text" />
                                  <br /> <input value="assign Book" type="submit" />
                            </form></td>
							</tr>
        </c:forEach>
        				</tbody>
        			</table>



 </body>
 </html>