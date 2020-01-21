<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Add book</title>
<body>
	<h2>Add Book</h2>
	<c:if test="${not empty msg}">
        ${msg}
    </c:if>
	<h3>Add Book</h3>
	<form method="POST" name="add_book"
		action="<%=request.getContextPath()%>add/book">
		Name: <input name="name" value="${name}" type="text" /> <br />
		<br /> Author Name: <input name="aname" value="${authorName}" type="text" />

			<br /> <br /> <input value="Add Book" type="submit" />
	</form>



<div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <input type='text' class="form-control" id='datetimepicker4' />
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker4').datetimepicker();
            });
        </script>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker2'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker2').datetimepicker({
                    locale: 'ru'
                });
            });
        </script>
    </div>
</div>

</body>
</html>