<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
						<h1 class="h3 mb-0 text-gray-800">회원정보수정</h1>
					</div>

					<div class="row justify-content-center">
						<div class="col-md-8">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">회원 정보 입력</h6>
								</div>
								<div class="card-body">

									<form:form method="post" modelAttribute="memberDTO"
										enctype="multipart/form-data" id="joinForm">

										<!-- 이름 -->
										<div class="form-group">
											<label for="name" class="font-weight-bold">이름</label>
											<form:input path="name" cssClass="form-control" id="name"
												placeholder="이름을 입력하세요"  />
											<form:errors path="name" cssClass="text-danger"></form:errors>
										</div>

										<!-- 생년월일 -->
										<div class="form-group">
											<label for="birth" class="font-weight-bold">생년월일</label>
											<form:input type="date" path="birth" cssClass="form-control"
												id="birth"  />
											<form:errors path="birth" cssClass="text-danger"></form:errors>
										</div>

										<!-- 휴대폰 -->
										<div class="form-group">
											<label for="phone" class="font-weight-bold">휴대폰</label>
											<form:input path="phone" cssClass="form-control" id="phone"
												placeholder="010-0000-0000"  />
											<form:errors path="phone" cssClass="text-danger"></form:errors>
										</div>

										<!-- 이메일 -->
										<div class="form-group">
											<label for="email" class="font-weight-bold">이메일</label>
											<form:input type="email" path="email" cssClass="form-control"
												id="email" placeholder="example@example.com"
												 />
											<form:errors path="email" cssClass="text-danger"></form:errors>
										</div>

										<!-- 프로필 이미지 (MultipartFile 필드) -->
										<div class="form-group">
											<label for="profileImage" class="font-weight-bold">프로필
												이미지 (선택)</label>
											<!-- name="file"은 컨트롤러의 MultipartFile 매개변수명과 일치해야 합니다 -->
											<input type="file" class="form-control" id="profileImage"
												name="file" accept="image/*">
										</div>

										<div class="text-right">
											<a href="/" class="btn btn-secondary mr-2">취소</a>
											<!-- type을 submit으로 변경해야 엔터키나 클릭 시 서버로 전송됩니다. -->
											<button type="submit" id="joinBtn"
												class="btn btn-primary px-4">수정하기</button>
										</div>

									</form:form>


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
	<!-- <script src="/js/member/join.js"></script> -->
</body>

</html>