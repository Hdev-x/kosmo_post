<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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

					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">로그인</h1>
					</div>

					<div class="row justify-content-center">
						<div class="col-md-6">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">로그인</h6>
								</div>
								<div class="card-body">

									<c:if test="${error != null}">
										<div class="alert alert-danger" role="alert">${error}</div>
									</c:if>

									<form action="./login" method="post">

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

										<div class="text-center">
											<button type="submit" class="btn btn-primary px-5">로그인</button>
										</div>

									</form>

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