<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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

									<div>
										${param.message}
									</div>

									<div class="row justify-content-center">
										<div class="col-md-6">
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">로그인</h6>
												</div>
												<div class="card-body">


													<form action="./login" method="post" enctype="multipart/form-data">

														<%-- 서버에서 보낸 인증 실패 메시지 (아이디/비번 불일치) --%>
														<c:if test="${not empty error}">
															<div class="alert alert-danger" role="alert">${error}</div>
														</c:if>

														<div class="form-group">
															<label for="username">아이디</label>
															<input type="text" id="username" name="username"
															class="form-control" placeholder="아이디를 입력하세요"
															<%-- 쿠키에 저장된 아이디가 있다면 우선적으로 보여줌 --%>
															value="${not empty cookie.rememberId ? cookie.rememberId.value : param.username}" />
															<%-- 아이디 관련 에러 메시지가 필요할 경우 아래처럼 표시 가능 --%>
															<c:if test="${not empty usernameError}">
																<small class="text-danger">${usernameError}</small>
															</c:if>
														</div>

														<div class="form-group">
															<label for="password">비밀번호</label>
															<input type="password" id="password" name="password"
															class="form-control" placeholder="비밀번호를 입력하세요" />
															<%-- 비밀번호 에러 메시지 표시 공간 --%>
															<c:if test="${not empty passwordError}">
																<small class="text-danger">${passwordError}</small>
															</c:if>
														</div>

														<div class="text-center mt-3">
															<button type="submit"
															class="btn btn-primary px-5">로그인</button>
														</div>
														<%-- 체크박스 영역 수정 --%>
														<div class="form-group form-check">
															<input type="checkbox" class="form-check-input"
															id="rememberId" name="rememberId" value="1"
															<%-- 쿠키가 존재하면 체크박스를 선택된 상태로 표시 --%>
															${not empty cookie.rememberId ? 'checked' : ''}>
															<label class="form-check-label" for="rememberId">아이디 저장</label>
														</div>
														<%-- 로그인 유지 --%>
														<div class="form-group form-check">
															<input type="checkbox" class="form-check-input"
															id="rememberId" name="rememberMe">
															<label class="form-check-label" for="rememberId">로그인 유지</label>
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