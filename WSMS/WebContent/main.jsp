<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>스케줄</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function scheduleAdd() {
		window.open("SCHEDULE/addSchedule.do","스케줄 입력","width=500,height=300");
	}
	
	function pop_scheduleInfo(day,start_time){
		window.open("SCHEDULE/scheduleInfo.do?day="+day+"&start_time="+start_time,"스케줄 수정","width=500,height=300");
	}
	
	if(window.opener){
		window.opener.location.href = window.opener.location.href;
		
		window.parent.close();
	}
</script>
</head>
<body>
	<div class="container">
	<jsp:include page="Header.jsp" />
	<c:if test="${sessionScope.login_user.level.intValue() == 0 }">
		<a href="USER/UserInfo.do">회원 관리</a>
	</c:if>
	
	<button onclick="scheduleAdd()" style="float: right;">시간등록</button>
	
	<table border="1" width="800" class="table table-bordered col-sm-12">
		<tr>
			<th width="50px">시간</th>
			<th width="100px">월</th>
			<th width="100px">화</th>
			<th width="100px">수</th>
			<th width="100px">목</th>
			<th width="100px">금</th>
			<th width="100px">토</th>
			<th width="100px">일</th>
		</tr>
		
		<c:set var="time" value="8"/>
		<c:set var="minute" value=":00"/>
		<c:set var="mon_length" value="0"/>
		<c:set var="mon_row" value="0" />
		<c:set var="tues_length" value="0"/>
		<c:set var="tues_row" value="0" />
		<c:set var="wen_length" value="0"/>
		<c:set var="wen_row" value="0" />
		<c:set var="thur_length" value="0"/>
		<c:set var="thur_row" value="0" />
		<c:set var="fri_length" value="0"/>
		<c:set var="fri_row" value="0" />
		<c:set var="sat_length" value="0"/>
		<c:set var="sat_row" value="0" />
		<c:set var="sun_length" value="0"/>
		<c:set var="sun_row" value="0" />
		
		<c:forEach var="i" begin="1" end="25">
			<c:choose>
				<c:when test="${i%2==0 }">
					<c:set var="minute" value=":30"/>
				</c:when>
				<c:otherwise>
					<c:set var="minute" value=":00"/>
					<c:set var="time" value="${time+1 }"/>
				</c:otherwise>
			</c:choose>
			
			<tr>
				<td>${time }${minute }</td>
			
			<c:forEach var="mon" items="${mon_list }">
				<c:if test="${mon.start_time == i }">
					<c:set var="mon_length" value="${mon.end_time - mon.start_time }" />
					<c:set var="mon_row" value="${mon_length }" />
					<c:set var="mon_day" value="${mon }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${mon_row != 0 }">
					<td rowspan="${mon_row}" style="background-color: ${mon_day.color}"
					width="100" onclick="pop_scheduleInfo(1, ${mon_day.start_time})" class=""> 
					${mon_day.title}<br>
					${mon_day.class_room}
					</td>
					<c:set var="mon_row" value="0"/>
					<c:set var="mon_length" value="${mon_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${mon_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="mon_length" value="${mon_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
					
			<c:forEach var="tues" items="${tues_list }">
				<c:if test="${tues.start_time == i }">
					<c:set var="tues_length" value="${tues.end_time - tues.start_time }" />
					<c:set var="tues_row" value="${tues_length }" />
					<c:set var="tues_day" value="${tues }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${tues_row != 0 }">
					<td rowspan="${tues_row}" style="background-color: ${tues_day.color}"
					 width="100" onclick="pop_scheduleInfo(2, ${tues_day.start_time})">
					${tues_day.title}<br>
					${tues_day.class_room}
					</td>
					<c:set var="tues_row" value="0"/>
					<c:set var="tues_length" value="${tues_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${tues_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="tues_length" value="${tues_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		
		<c:forEach var="wen" items="${wen_list }">
				<c:if test="${wen.start_time == i }">
					<c:set var="wen_length" value="${wen.end_time - wen.start_time }" />
					<c:set var="wen_row" value="${wen_length }" />
					<c:set var="wen_day" value="${wen }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${wen_row != 0 }">
					<td rowspan="${wen_row}" style="background-color: ${wen_day.color}"
					width="100" onclick="pop_scheduleInfo(3, ${wen_day.start_time})"> 
					${wen_day.title}<br>
					${wen_day.class_room}
					</td>
					<c:set var="wen_row" value="0"/>
					<c:set var="wen_length" value="${wen_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${wen_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="wen_length" value="${wen_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="thur" items="${thur_list }">
				<c:if test="${thur.start_time == i }">
					<c:set var="thur_length" value="${thur.end_time - thur.start_time }" />
					<c:set var="thur_row" value="${thur_length }" />
					<c:set var="thur_day" value="${thur }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${thur_row != 0 }">
					<td rowspan="${thur_row}" style="background-color: ${thur_day.color}"
					width="100" onclick="pop_scheduleInfo(4, ${thur_day.start_time})"> 
					${thur_day.title}<br>
					${thur_day.class_room}
					</td>
					<c:set var="thur_row" value="0"/>
					<c:set var="thur_length" value="${thur_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${thur_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="thur_length" value="${thur_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="fri" items="${fri_list }">
				<c:if test="${fri.start_time == i }">
					<c:set var="fri_length" value="${fri.end_time - fri.start_time }" />
					<c:set var="fri_row" value="${fri_length }" />
					<c:set var="fri_day" value="${fri }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${fri_row != 0 }">
					<td rowspan="${fri_row}" style="background-color: ${fri_day.color}"
					width="100" onclick="pop_scheduleInfo(5, ${fri_day.start_time})"> 
					${fri_day.title}<br>
					${fri_day.class_room}
					</td>
					<c:set var="fri_row" value="0"/>
					<c:set var="fri_length" value="${fri_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${fri_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="fri_length" value="${fri_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
			<!-- 토요일 -->
			<c:forEach var="sat" items="${sat_list }">
				<c:if test="${sat.start_time == i }">
					<c:set var="sat_length" value="${sat.end_time - sat.start_time }" />
					<c:set var="sat_row" value="${sat_length }" />
					<c:set var="sat_day" value="${sat }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${sat_row != 0 }">
					<td rowspan="${sat_row}" style="background-color: ${sat_day.color}" 
					width="100" onclick="pop_scheduleInfo(6, ${sat_day.start_time})">
					${sat_day.title}<br>
					${sat_day.class_room}
					</td>
					<c:set var="sat_row" value="0"/>
					<c:set var="sat_length" value="${sat_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${sat_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="sat_length" value="${sat_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
			<!-- 일요일 -->
			<c:forEach var="sun" items="${sun_list }">
				<c:if test="${sun.start_time == i }">
					<c:set var="sun_length" value="${sun.end_time - sun.start_time }" />
					<c:set var="sun_row" value="${sun_length }" />
					<c:set var="sun_day" value="${sun }" /> 
				</c:if>
			</c:forEach>
			<c:choose>
				<c:when test="${sun_row != 0 }">
					<td rowspan="${sun_row}" style="background-color: ${sun_day.color}" 
					width="100" onclick="pop_scheduleInfo(7, ${sun_day.start_time})">
					${sun_day.title}<br>
					${sun_day.class_room}
					</td>
					<c:set var="sun_row" value="0"/>
					<c:set var="sun_length" value="${sun_length-1 }"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${sun_length==0 }">
							<td></td>
						</c:when>
						<c:otherwise>
							<c:set var="sun_length" value="${sun_length-1 }"/>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="Tail.jsp" />
	</div>
</body>
</html>