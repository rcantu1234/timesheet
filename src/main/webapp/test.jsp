<%@ page import="java.util.*,com.skillstorm.data.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Time Sheets</title>

<link rel='stylesheet'
	href='https:maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>
<script
	src='https:ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script
	src='https:maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>
</head>

<body>
	<%
		List<TimeSheet> theTimeSheets = (List<TimeSheet>) request.getAttribute("timeSheets");
	%>
	<table class="table table-hover" border=1 CELLPADDING=0 CELLSPACING=0
		WIDTH=50%>

		<thead>
			<th colspan=11>
				<h1 align=center>Time Sheets</h1>
			</th>
		</thead>

		<thead align=center>
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
			<th>Action</th>
		</thead>

		<c:forEach var="tempTimeSheets" items="<%=theTimeSheets%>">
			<c:url var="tempLink" value="/api">
				<c:param name="command" value="DELETE" />
				<c:param name="timeSheetId" value="${tempTimeSheets.timeSheetId}" />
			</c:url>
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
				<td><a href="${tempLink}" onclick="if(!(confirm('Are you sure you want to delete the timesheet?'))) return false">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<div style="clear: both;"></div>
	<p>
		<a href="http://localhost:8080/punchCard/view_time_sheets.html">Back
			to List</a>
	</p>
</body>
</html>