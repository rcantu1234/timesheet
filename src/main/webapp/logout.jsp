<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log Out</title>
</head>
<body>
	<div class="topnav">
		<input type="hidden" name="command" value="HOME" />
		<div class="topnav">
			<input type="hidden" name="command" value="HOME" /> <a
				class="active" href="http://localhost:8080/punchCard/home.html">Home</a>
			<a href="http://localhost:8080/punchCard/enter_time.html">Create
				Time Sheet</a> <a
				href="http://localhost:8080/punchCard/view_time_sheets.html">View
				Time Sheets</a>
				<!-- <a href="http://localhost:8080/punchCard/delete_time.html"></a> -->
				<a href="http://localhost:8080/punchCard/logout.jsp">Logout</a>
		</div>
	</div>
	<%
		session.invalidate();
		response.sendRedirect("login.html");
	%>

</body>
</html>