<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>은행상품</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>

<style>
.product-card {
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	height: 100%;
	display: flex;
	flex-direction: column;
	cursor: pointer;
	border-left: 5px solid #007bff;
}

.product-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
}

.product-card.loan {
	border-left-color: #dc3545;
}

.product-card.savings {
	border-left-color: #28a745;
}

.product-card.deposit {
	border-left-color: #ffc107;
}

.product-body {
	padding: 20px;
	display: flex;
	flex-direction: column;
}

.product-name {
	font-weight: bold;
	font-size: 18px;
	margin-bottom: 10px;
	color: #333;
}

.product-type {
	font-size: 12px;
	padding: 4px 8px;
	background-color: #e9ecef;
	display: inline-block;
	border-radius: 4px;
	margin-bottom: 12px;
	width: fit-content;
}

.product-desc {
	font-size: 13px;
	color: #666;
	margin-bottom: 15px;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	overflow: hidden;
	flex-grow: 1;
}

.product-rate-section {
	border-top: 1px solid #e9ecef;
	padding-top: 15px;
	margin-top: auto;
}

.product-rate-label {
	font-size: 12px;
	color: #999;
	margin-bottom: 5px;
}

.product-rate {
	font-size: 24px;
	font-weight: bold;
	color: #007bff;
}

.product-rate-unit {
	font-size: 14px;
	color: #666;
}
</style>
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
						<h1 class="h3 mb-0 text-gray-800 font-weight-bold">
							<i class="fas fa-bank mr-2"></i>은행상품
						</h1>
						<a href="./create"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
							<i class="fas fa-plus fa-sm text-white-50 mr-1"></i> 상품추가
						</a>
					</div>

					<!-- 검색 영역 -->
					<div class="row justify-content-center my-4">
						<div class="col-md-7 col-lg-6">
							<form action="./list" method="get">
								<div class="input-group shadow-sm" style="border-radius: 5px;">
									<div class="input-group-prepend">
										<select class="custom-select border-right-0" name="kind"
											style="border-radius: 5px 0 0 5px;">
											<option value="v1" ${pager.kind == 'v1' ? 'selected' : ''}>상품명</option>
											<option value="v2" ${pager.kind == 'v2' ? 'selected' : ''}>설명</option>
										</select>
									</div>
									<input type="text" value="${pager.search}" class="form-control"
										placeholder="검색어를 입력하세요" name="search">
									<div class="input-group-append">
										<button class="btn btn-primary px-4" type="submit"
											style="border-radius: 0 5px 5px 0;">검색</button>
									</div>
								</div>
							</form>
						</div>
					</div>

					<!-- 상품 카드 그리드 -->
					<div class="row">
						<c:forEach items="${list}" var="d">
							<div class="col-lg-3 col-md-4 col-sm-6 mb-4">
								<a href="./detail?productNum=${d.productNum}"
									class="text-decoration-none text-dark">
									<div
										class="card product-card border-0 shadow-sm ${d.productType == '대출' ? 'loan' : d.productType == '적금' ? 'savings' : 'deposit'}">
										<div class="product-body">
											<h5 class="product-name">${d.productName}</h5>

											<div class="product-type">${d.productType}</div>

											<p class="product-desc">${d.productDesc}</p>

											<div class="product-rate-section">
												<div class="product-rate-label">기준이율</div>
												<div class="product-rate">
													<fmt:formatNumber value="${d.productRate}" type="number"
														maxFractionDigits="2" minFractionDigits="2" />
													<span class="product-rate-unit">%</span>
												</div>
											</div>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>

					<!-- 상품이 없을 때 -->
					<c:if test="${empty list}">
						<div class="alert alert-info text-center mt-5">
							<i class="fas fa-info-circle mr-2"></i>등록된 상품이 없습니다.
						</div>
					</c:if>

					<!-- 페이지네이션 -->
					<nav aria-label="Page navigation" class="mt-5">
						<ul class="pagination justify-content-center">
							<li class="page-item ${pager.pre?'':'disabled'}"><a
								class="page-link"
								href="./list?page=${pager.pre?pager.start-1:pager.start}&kind=${pager.kind}&search=${pager.search}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<c:forEach begin="${pager.start}" end="${pager.end}" var="i">
								<li class="page-item"><a class="page-link"
									href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
								</li>
							</c:forEach>
							<li class="page-item ${pager.next?'':'disabled'}"><a
								class="page-link"
								href="./list?page=${pager.next?pager.end+1:pager.end}&kind=${pager.kind}&search=${pager.search}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>

				</div>
			</div>
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
</body>
</html>