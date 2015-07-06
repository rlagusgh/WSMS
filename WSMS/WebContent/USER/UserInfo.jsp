<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/Header.jsp" />
	<c:if test="${login_user.level.intValue() == 1 }">
		<c:redirect url="../main.jsp"/>
	</c:if>
	
	<table border="1">
		<thead style="background: gray;">
			<tr>
				<td> ID </td>
				<td> 이름 </td>
				<td> E-MAIL </td>
				<td> 회원 등급 </td>
				<td> 가입일 </td>
				<td>관리자 부여</td>
			</tr>
		</thead>
		<c:forEach var="user" items="${users }">
		<tr>
			<td>${user.ID }</td>
			<td>${user.NAME }</td>
			<td>${user.EMAIL }</td>
			<td>${user.level }</td>
			<td>${user.JOIN_DATE }</td>
			<td>
			<c:choose>
					<c:when test="${user.level.intValue() == 1 }">
					<a href="UserLevelgrade.do?id=${user.ID}">
					<button>관리자 권한부여</button></a>
				</c:when>
				<c:otherwise>
					<a href="UserLevelgrade.do?id=${user.ID}">
					<button>관리자 권한해제</button></a>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="../Tail.jsp" />
</body>
</html>