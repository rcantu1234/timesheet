<%@ page import="java.util.*,com.skillstorm.data.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkillStorm Home Page</title>

<link href="resources/time_sheet.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="topnav">
		<input type="hidden" name="command" value="HOME" />
		<div class="topnav">
			<input type="hidden" name="command" value="HOME" /> <a
				class="active" href="http://localhost:8080/punchCard/home.jsp">Home</a>
			<a href="http://localhost:8080/punchCard/enter_time.html">Create
				Time Sheet</a> <a
				href="http://localhost:8080/punchCard/view_time_sheets.html">View
				Time Sheets</a>
				<!-- <a href="http://localhost:8080/punchCard/delete_time.html"></a> -->
				<a href="http://localhost:8080/punchCard/logout.jsp">Logout</a>
		</div>
	</div>
	
	<%
		List<TimeSheet> theTimeSheets = (List<TimeSheet>) request.getAttribute("theTimeSheet");
	%>
	<table class="table table-hover" border=1 CELLPADDING=0 CELLSPACING=0
		WIDTH=50%>
		<input type="hidden" name="command" value="HOME" /> 

		<thead>
			<th colspan=10>
				<h1 align=center>Time Sheets</h1>
			</th>
		</thead>

		<thead>
			<th>Time Sheet Id</th>
			<th>Monday</th>
			<th>Tuesday</th>
			<th>Wednesday</th>
			<th>Thursday</th>
			<th>Friday</th>
			<th>Saturday</th>
			<th>Sunday</th>
			<th>Total Hours</th>
			<th>User Id</th>
		</thead>

		<c:forEach var="tempTimeSheets" items="<%=theTimeSheets%>">
			<tr class=success align=center>
				<td align=center>${tempTimeSheets.timeSheetId}</td>
				<td align=center>${tempTimeSheets.monday}</td>
				<td align=center>${tempTimeSheets.tuesday}</td>
				<td align=center>${tempTimeSheets.wednesday}</td>
				<td align=center>${tempTimeSheets.thursday}</td>
				<td align=center>${tempTimeSheets.friday}</td>
				<td align=center>${tempTimeSheets.saturday}</td>
				<td align=center>${tempTimeSheets.sunday}</td>
				<td align=center>${tempTimeSheets.totalHours}</td>
				<td align=center>${tempTimeSheets.userId}</td>
			</tr>
		</c:forEach>
	</table>

	<div style="clear: both;"></div>
</body>
</html>