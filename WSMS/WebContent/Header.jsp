<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="background-color: #00008b; color:#ffffff; height:30px; padding:5px;">
	<a href="<%=request.getContextPath() %>/schedule.do" style="color: white; text-decoration: none;">
	SPMS(Web Schedule Management System)</a>
	<span style="float: right;">
		<c:choose>
			<c:when test="${login_user.level.intValue() == 0 }">
				관리자(${sessionScope.login_user.ID })
			</c:when>
			<c:otherwise>
				${sessionScope.login_user.ID }
			</c:otherwise>
		</c:choose>
		<a style="color: white; text-decoration: none;" 
			href="<%=request.getContextPath() %>/USER/logout.do">로그아웃</a>
	</span>
</div>