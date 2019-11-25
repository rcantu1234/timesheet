<%@ page import="java.util.*,com.skillstorm.data.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Time</title>

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

<%
	List<TimeSheet> theTimeSheets = (List<TimeSheet>) request.getAttribute("timeSheets");
%>
<body>

	<div class="topnav">
		<input type="hidden" name="command" value="HOME" />
		<div class="topnav">
			<input type="hidden" name="command" value="HOME" /> <a
				class="active" href="http://localhost:8080/punchCard/home.html">Home</a>
			<a href="http://localhost:8080/punchCard/enter_time.html">Create
				Timesheet</a> <a
				href="http://localhost:8080/punchCard/view_time_sheets.html">View
				Time Sheets</a><a
				href="http://localhost:8080/punchCard/delete_time.html">Delete
				Time Sheets</a> <a href="#contact">Contact</a>
		</div>
	</div>

	<div style="margin-top: 70px;">
		<div>
			<form method="GET" action="/punchCard/api/"> 
			<!-- <form method="GET" action="PunchCardControllerServlet"> -->
				<input type="hidden" name="command" value="UPDATE" />
				<input type="hidden" name="timeSheetId" value="${theTimeSheets.timeSheetId}" />

				<table class="table" border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50%
					align=center>
					<thead class="bg-success">
						<th colspan=9>
							<h1 style="color: white;">Weekly Hours</h1>
						</th>
					</thead>

					<thead align="center">
						<th><p>Monday</p></th>
						<th><p>Tuesday</p></th>
						<th><p>Wednesday</p></th>
						<th><p>Thursday</p></th>
						<th><p>Friday</p></th>
						<th><p>Saturday</p></th>
						<th><p>Sunday</p></th>
						<th><p>User ID</p></th>
					</thead>

					<tr align="center" class="bg-success">
						<td align=center><input type="text" name="monday" value="${theTimeSheets.monday}"/></td>
						<td align=center><input type="text" name="tuesday" value="${theTimeSheets.tuesday}"/></td>
						<td align=center><input type="text" name="wednesday" value="${theTimeSheets.wednesday}"/></td>
						<td align=center><input type="text" name="thursday" value="${theTimeSheets.thursday}"/></td>
						<td align=center><input type="text" name="friday" value="${theTimeSheets.friday}"/></td>
						<td align=center><input type="text" name="saturday" value="${theTimeSheets.saturday}"/></td>
						<td align=center><input type="text" name="sunday" value="${theTimeSheets.sunday}"/></td>
						<td align=center><input type="text" name=userId value="${theTimeSheets.userId}"/></td>
					</tr>
					<tr>
						<td align=center colspan=9><input type=submit
							class="save" value=Save /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>