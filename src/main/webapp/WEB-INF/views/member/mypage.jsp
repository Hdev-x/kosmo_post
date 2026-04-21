<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
</head>

<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>

				<div class="container-fluid">

					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">마이페이지</h1>
					</div>

					<div class="row">

						<!-- 회원 정보 수정 -->
						<div class="col-md-8">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">회원 정보 수정</h6>
								</div>
								<div class="card-body">

									<form action="./update" method="post">

										<div class="form-group">
											<label for="username" class="font-weight-bold">아이디</label>
											<input type="text" class="form-control" id="username"
												value="${member.username}" readonly>
											<input type="hidden" name="username"
												value="${member.username}">
										</div>

										<div class="form-group">
											<label for="name" class="font-weight-bold">이름</label>
											<input type="text" class="form-control" id="name"
												name="name" value="${member.name}" required>
										</div>

										<div class="form-group">
											<label for="password" class="font-weight-bold">비밀번호</label>
											<input type="password" class="form-control" id="password"
												name="password" value="${member.password}" required>
										</div>

										<div class="form-group">
											<label for="birth" class="font-weight-bold">생년월일</label>
											<input type="date" class="form-control" id="birth"
												name="birth" value="${member.birth}" required>
										</div>

										<div class="form-group">
											<label for="phone" class="font-weight-bold">휴대폰</label>
											<input type="tel" class="form-control" id="phone"
												name="phone" value="${member.phone}" required>
										</div>

										<div class="form-group">
											<label for="email" class="font-weight-bold">이메일</label>
											<input type="email" class="form-control" id="email"
												name="email" value="${member.email}" required>
										</div>

										<div class="text-right">
											<button type="submit" class="btn btn-primary">수정하기</button>
										</div>

									</form>

								</div>
							</div>
						</div>

						<!-- 사이드바 -->
						<div class="col-md-4">

							<!-- 프로필 이미지 -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">프로필</h6>
								</div>
								<div class="card-body text-center">

									<div class="mb-3">
										<img src="/files/profile/default.png"
											class="img-fluid rounded-circle"
											style="width: 150px; height: 150px; object-fit: cover;" alt="프로필">
									</div>

									<form action="./addProfile" method="post"
										enctype="multipart/form-data">
										<div class="form-group">
											<input type="file" class="form-control" name="file"
												accept="image/*" required>
										</div>
										<button type="submit" class="btn btn-sm btn-primary">
											프로필 업로드
										</button>
									</form>

								</div>
							</div>

							<!-- 버튼 -->
							<div class="card shadow">
								<div class="card-body text-center">
									<a href="./logout" class="btn btn-warning btn-block mb-2">로그아웃</a>
									<button type="button" class="btn btn-danger btn-block"
										data-toggle="modal" data-target="#deleteModal">회원탈퇴</button>
								</div>
							</div>

						</div>

					</div>

				</div>

			</div>

			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>

	<!-- 회원탈퇴 모달 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">회원탈퇴</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">정말로 탈퇴하시겠습니까?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<form action="./delete" method="post" style="display: inline;">
						<input type="hidden" name="username" value="${member.username}">
						<button type="submit" class="btn btn-danger">탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>

</body>
</html>