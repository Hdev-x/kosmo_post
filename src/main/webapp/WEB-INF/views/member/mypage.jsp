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

					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">마이페이지</h1>
					</div>

					<div class="row">

						<!-- 회원 정보 조회 영역 -->
						<div class="col-md-8">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">회원 정보</h6>
								</div>
								<div class="card-body">

									<!-- 1. 프로필 이미지 항목 추가 (맨 윗줄) -->
									<div class="form-group border-bottom pb-3 text-center">
										<label
											class="font-weight-bold text-gray-600 d-block text-left">프로필
											사진</label> <img
											src="${empty member.profileDTO ? '/files/profile/default.png' : '/files/profile/'.concat(member.profileDTO.fileName)}"
											class="img-fluid rounded-circle"
											style="width: 120px; height: 120px; object-fit: cover;"
											alt="프로필">
									</div>

									<!-- 2. 기존 정보 리스트 -->
									<div class="form-group">
										<label class="font-weight-bold text-gray-600">아이디</label>
										<p class="form-control-plaintext border-bottom">${member.username}</p>
									</div>

									<div class="form-group">
										<label class="font-weight-bold text-gray-600">이름</label>
										<p class="form-control-plaintext border-bottom">${member.name}</p>
									</div>

									<div class="form-group">
										<label class="font-weight-bold text-gray-600">생년월일</label>
										<p class="form-control-plaintext border-bottom">${member.birth}</p>
									</div>

									<div class="form-group">
										<label class="font-weight-bold text-gray-600">휴대폰</label>
										<p class="form-control-plaintext border-bottom">${member.phone}</p>
									</div>

									<div class="form-group">
										<label class="font-weight-bold text-gray-600">이메일</label>
										<p class="form-control-plaintext border-bottom">${member.email}</p>
									</div>

									<div class="text-right mt-4">
										<a href="./update" class="btn btn-primary px-4">정보 수정하기</a>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>

	</div>


	<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>

	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>

</body>
</html>