<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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

					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">회원가입</h1>
					</div>

					<!-- 회원가입 폼 -->
					<div class="row justify-content-center">
						<div class="col-md-8">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">회원 정보 입력</h6>
								</div>
								<div class="card-body">
									<form action="./join" method="post" enctype="multipart/form-data">

										<div class="form-group">
											<label for="username" class="font-weight-bold">아이디</label>
											<input type="text" class="form-control" id="username"
												name="username" placeholder="아이디를 입력하세요" required>
										</div>

										<div class="form-group">
											<label for="password" class="font-weight-bold">비밀번호</label>
											<input type="password" class="form-control" id="password"
												name="password" placeholder="비밀번호를 입력하세요" required>
										</div>

										<div class="form-group">
											<label for="passwordConfirm" class="font-weight-bold">비밀번호 확인</label>
											<input type="password" class="form-control" id="passwordConfirm"
												placeholder="비밀번호를 다시 입력하세요" required>
										</div>

										<div class="form-group">
											<label for="name" class="font-weight-bold">이름</label>
											<input type="text" class="form-control" id="name"
												name="name" placeholder="이름을 입력하세요" required>
										</div>

										<div class="form-group">
											<label for="birth" class="font-weight-bold">생년월일</label>
											<input type="date" class="form-control" id="birth"
												name="birth" required>
										</div>

										<div class="form-group">
											<label for="phone" class="font-weight-bold">휴대폰</label>
											<input type="tel" class="form-control" id="phone"
												name="phone" placeholder="010-0000-0000" required>
										</div>

										<div class="form-group">
											<label for="email" class="font-weight-bold">이메일</label>
											<input type="email" class="form-control" id="email"
												name="email" placeholder="example@example.com" required>
										</div>

										<div class="form-group">
											<label for="profileImage" class="font-weight-bold">프로필 이미지 (선택)</label>
											<input type="file" class="form-control" id="profileImage"
												name="file" accept="image/*">
											<small class="form-text text-muted">jpg, png 등의 이미지 파일만 업로드 가능합니다.</small>
										</div>

										<div class="text-right">
											<a href="/" class="btn btn-secondary mr-2">취소</a>
											<button type="submit" class="btn btn-primary px-4">가입하기</button>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>

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