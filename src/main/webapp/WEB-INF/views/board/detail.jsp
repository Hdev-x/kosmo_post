<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<div>
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb bg-transparent p-0 mb-1">
									<li class="breadcrumb-item"><a href="./list">교수 관리</a></li>
									<li class="breadcrumb-item active" aria-current="page">상세
										정보</li>
								</ol>
							</nav>
							<h1 class="h3 mb-0 text-gray-800 font-weight-bold">
								<i class="fas fa-university mr-2"></i>학과 상세 보기
							</h1>
						</div>

						<a href="./list"
							class="d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm">
							<i class="fas fa-list fa-sm text-white-50 mr-1"></i> 목록으로 돌아가기
						</a>
					</div>

					<hr class="mb-4">

					<div class="row justify-content-center">
						<div class="col">
							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">${d.boardWriter}</h6>
								</div>
								<div class="card-body">
									<div class="row mb-3">
										<div class="col-sm-1 font-weight-bold">제목</div>
										<div class="col-sm-11 text-muted">${d.boardTitle}</div>
									</div>
									<div class="row mb-3">
										<div class="col-sm-1 font-weight-bold">작성자</div>
										<div class="col-sm-11 text-muted">${d.boardWriter}</div>
									</div>
									<div class="row mb-3">
										<div class="col-sm-1 font-weight-bold">내용</div>
										<div class="col-sm-11 text-muted">${d.boardContents}</div>
									</div>

									<div>
										<c:forEach var="file" items="${d.list}">
											<div>
											<img src="/files/${name}/${file.fileName}">
											</div>
										</c:forEach>


									</div>



									<div class="d-flex justify-content-end mt-4">
										<a class="btn btn-primary btn-icon-split mr-2"
											href="./update?boardNo=${d.boardNo}"> <span
											class="icon text-white-50"><i class="fas fa-edit"></i></span>
											<span class="text">수정</span>
										</a>

										<form action="./delete" method="post"
											onsubmit="return confirm('정말 삭제하시겠습니까?');">
											<input type="hidden" name="boardNo" value="${d.boardNo}">
											<button class="btn btn-danger btn-icon-split" type="submit">
												<span class="icon text-white-50"><i
													class="fas fa-trash"></i></span> <span class="text">삭제</span>
											</button>
										</form>
									</div>
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