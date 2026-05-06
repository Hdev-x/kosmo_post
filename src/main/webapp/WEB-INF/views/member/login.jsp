<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>

				<div class="container-fluid">

					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">로그인</h1>
					</div>

					<div class="row justify-content-center">
						<div class="col-md-6">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">로그인</h6>
								</div>
								<div class="card-body">


									<form:form action="./login" modelAttribute="memberDTO"
										method="post" enctype="multipart/form-data">

										<%-- 서버에서 보낸 인증 실패 메시지 (아이디/비번 불일치) --%>
										<c:if test="${not empty error}">
											<div class="alert alert-danger" role="alert">${error}</div>
										</c:if>

										<div class="form-group">
											<form:label path="username">아이디</form:label>
											<form:input path="username" cssClass="form-control"
												placeholder="아이디를 입력하세요" />
											<%-- 아이디 에러 메시지 표시 (빨간색 글씨 스타일 추가) --%>
											<form:errors path="username" cssClass="text-danger" />
										</div>

										<div class="form-group">
											<form:label path="password">비밀번호</form:label>
											<form:password path="password" cssClass="form-control"
												placeholder="비밀번호를 입력하세요" />
											<%-- 비밀번호 에러 메시지 표시 공간 추가 --%>
											<form:errors path="password" cssClass="text-danger" />
										</div>

										<div class="text-center mt-3">
											<button type="submit" class="btn btn-primary px-5">로그인</button>
										</div>

									</form:form>

									<hr>

									<div class="text-center">
										<p class="mb-0">
											아직 회원이 아니신가요? <a href="./join">회원가입</a>
										</p>
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>

	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>

</body>
</html>