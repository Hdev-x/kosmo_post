<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
</head>
<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					
						



					<sec:authorize access="isAuthenticated()">
						<%-- 로그인 상태일 때: 다국어 환영 메시지 출력 --%>
						<h1 class="h3 mb-4 text-gray-800">
							<spring:message code="welcome.login"
								arguments="${member.username},${member.birth}"
								argumentSeparator="," />
							<%-- <-- 요 끝부분 '/>'가 잘 닫혀있는지 꼭 확인하세요! --%>
						</h1>
					</sec:authorize>

					<sec:authorize access="!isAuthenticated()">
						<%-- 비로그인 상태일 때 --%>
						<h1 class="h3 mb-4 text-gray-800">비로그인 상태</h1>
					</sec:authorize>





				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
</body>
</html>