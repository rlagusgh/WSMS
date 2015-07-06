<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="ScheduleFunction" prefix="schedule"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시간표 확인</title>
</head>
<body>
	<table>
		<tr>
			<td>과목:</td>
			<td>${week.title }</td>
		</tr>
		<tr>
			<td>시간:</td>
			<td>${schedule:DayKor(week.day) } ${schedule:getTime(week.start_time) }~${schedule:getTime(week.end_time) }</td>
		</tr>
		<tr>
			<td>메모:</td>
			<td>${week.contents }</td>
		</tr>
		<tr>
			<td>교실:</td>
			<td>${week.class_room }</td>
		</tr>
	</table>
	<a href="scheduleMod.do?day=${week.day }&start_time=${week.start_time }"><button>수정하기</button></a>
	<a href="scheduleDel.do?day=${week.day }&start_time=${week.start_time }"><button>삭제하기</button></a>
</body>
</html>