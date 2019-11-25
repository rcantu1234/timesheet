<%@ page import="java.util.*,com.skillstorm.data.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<%
	List<TimeSheet> theTimeSheets = (List<TimeSheet>) request.getAttribute("timeSheets");
%>

<body>
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

		<%
			for (TimeSheet list : theTimeSheets) {
		%>
		<c:url var="tempLink" value="PunchCardControllerServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="timeSheetId" value="${list.timeSheetId}" />
		</c:url>
		<tr class=success align=center>
			<td align=center><%=list.getTimeSheetId()%></td>
			<td align=center><%=list.getMonday()%></td>
			<td align=center><%=list.getTuesday()%></td>
			<td align=center><%=list.getWednesday()%></td>
			<td align=center><%=list.getThursday()%></td>
			<td align=center><%=list.getFriday()%></td>
			<td align=center><%=list.getSaturday()%></td>
			<td align=center><%=list.getSunday()%></td>
			<td align=center><%=list.getTotalHours()%></td>
			<td align=center><%=list.getUserId()%></td>
			<td><a href="${tempLink}">Update</a></td>
		</tr>
		<%
			}
		%>

	</table>

	<div style="clear: both;"></div>
	<p>
		<a href="http://localhost:8080/punchCard/view_time_sheets.html">Back to List</a>
	</p>
</body>
</html>