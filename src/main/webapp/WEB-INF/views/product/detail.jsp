<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.productName}-상세정보</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
<style>
/* 리스트의 디자인 포인트를 상세 페이지에도 적용 */
.detail-card {
	border-radius: 15px;
	overflow: hidden;
}

.detail-header-border {
	border-left: 8px solid #007bff; /* 기본값 */
}

.detail-header-border.loan {
	border-left-color: #dc3545;
}

.detail-header-border.savings {
	border-left-color: #28a745;
}

.detail-header-border.deposit {
	border-left-color: #ffc107;
}

.product-type-badge {
	font-size: 14px;
	padding: 5px 15px;
	background-color: #f8f9fc;
	border-radius: 20px;
	color: #4e73df;
	font-weight: bold;
	border: 1px solid #e3e6f0;
}

.rate-box {
	background: linear-gradient(135deg, #4e73df 0%, #224abe 100%);
	color: white;
	padding: 20px;
	border-radius: 12px;
	display: inline-block;
}

.img-container {
	width: 100%;
	max-height: 350px;
	overflow: hidden;
	border-radius: 10px;
	background-color: #f8f9fc;
	display: flex;
	align-items: center;
	justify-content: center;
}

/* 리뷰 스타일 추가 */
.review-item {
	padding: 20px;
	border-bottom: 1px solid #e3e6f0;
	transition: background-color 0.2s;
}

.review-item:last-child {
	border-bottom: none;
}

.review-item:hover {
	background-color: #f8f9fc;
}

.review-user {
	font-weight: bold;
	color: #4e73df;
	font-size: 0.9rem;
}

.review-date {
	font-size: 0.8rem;
	color: #858796;
}

.review-text {
	margin-top: 10px;
	color: #5a5c69;
	line-height: 1.6;
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
							<i class="fas fa-search-dollar mr-2"></i>상품 상세 내역
						</h1>
						<a href="./list" class="btn btn-sm btn-secondary shadow-sm"> <i
							class="fas fa-list fa-sm mr-1"></i> 목록으로
						</a>
					</div>

					<div class="row justify-content-center">
						<div class="col-xl-9 col-lg-10">
							<div class="card shadow detail-card mb-4 border-0">
								<div
									class="detail-header-border ${dto.productType == '대출' ? 'loan' : dto.productType == '적금' ? 'savings' : 'deposit'}">
									<div class="card-body p-4 p-md-5">
										<div class="row">
											<div class="col-md-5 mb-4 mb-md-0">
												<div class="img-container shadow-sm">
													<c:choose>
														<c:when test="${not empty dto.productFileDTO.fileName}">
															<img src="/files/product/${dto.productFileDTO.fileName}"
																class="img-fluid" alt="상품이미지">
														</c:when>
														<c:otherwise>
															<div class="text-center p-5">
																<i class="fas fa-university fa-5x text-gray-200"></i>
																<p class="mt-2 text-gray-400">이미지가 없는 상품입니다</p>
															</div>
														</c:otherwise>
													</c:choose>
												</div>
											</div>

											<div class="col-md-7">
												<div class="mb-3">
													<span class="product-type-badge">${dto.productType}</span>
												</div>
												<h2 class="display-5 font-weight-bold text-gray-900 mb-3">${dto.productName}</h2>

												<div class="mb-4">
													<label
														class="text-xs font-weight-bold text-uppercase text-muted mb-1 d-block">상품
														설명</label>
													<p class="text-gray-700 leading-relaxed">${dto.productContents}</p>
												</div>

												<div class="row mb-4">
													<div class="col-12">
														<div class="rate-box shadow">
															<small class="d-block mb-1 opacity-75">기준 금리 (연)</small>
															<span class="h1 font-weight-bold mb-0"> <fmt:formatNumber
																	value="${dto.productRate}" type="number"
																	maxFractionDigits="2" minFractionDigits="2" />
															</span> <span class="h4 mb-0">%</span>
														</div>
													</div>
												</div>

												<div class="d-flex flex-column flex-md-row pt-3 border-top">
													<a href="../account/create?productNum=${dto.productNum}"
														class="btn btn-primary btn-icon-split btn-lg mb-2 mb-md-0 mr-md-3">
														<span class="icon text-white-50"> <i
															class="fas fa-check"></i>
													</span> <span class="text">지금 바로 가입하기</span>
													</a>

													<button id="create" data-pn="${dto.productNum}"
														class="btn btn-danger btn-icon-split btn-lg">
														<span class="icon text-white-50"> <i
															class="fas fa-shopping-cart"></i>
														</span> <span class="text">장바구니 담기</span>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 리뷰 전체 -->
			<div class="container-fluid mb-5">
				<div class="row justify-content-center">
					<div class="col-xl-9 col-lg-10">


						<!-- 리뷰 작성 START -->
						<div class="card shadow mb-4 border-0">
						
							<!-- Header Start -->
							<div class="card-header py-3 bg-white">
								<h6 class="m-0 font-weight-bold text-primary">리뷰 작성</h6>
							</div>
							<!-- Header End -->

							<!-- BODY Start -->
							<div class="card-body">
							
								<!-- 별점 START -->
								<div class="form-group mb-3">
									<label class="small font-weight-bold text-dark">상품은
										만족스러우셨나요?</label>
									<div id="star_rating" class="star-rating h4 text-warning"
										style="cursor: pointer;">
										<i class="far fa-star" data-value="1"></i> <i
											class="far fa-star" data-value="2"></i> <i
											class="far fa-star" data-value="3"></i> <i
											class="far fa-star" data-value="4"></i> <i
											class="far fa-star" data-value="5"></i> <input type="hidden"
											id="review_star" value="0">
									</div>
								</div>
								<!-- 별점 END -->

								<!-- 댓글 입력창 START -->
								<div class="form-group">
									<textarea id="review_contents" class="form-control" rows="3"
										placeholder="이 상품에 대한 솔직한 후기를 남겨주세요." style="resize: none;"></textarea>
								</div>
								<!-- 댓글 입력창 END -->

								<!-- 리뷰 등록 버튼 START -->
								<div class="text-right">
									<button id="review_btn" class="btn btn-primary shadow-sm"
										data-pn="${dto.productNum}">
										<i class="fas fa-edit fa-sm text-white-50 mr-1"></i> 리뷰 등록
									</button>
								</div>
								<!-- 리뷰 등록 버튼 END -->
								
							</div>
							<!-- BODY End -->
							
						</div>
						<!-- 리뷰 작성 END -->


						<!-- 사용자 후기 START -->
						<div class="card shadow mb-4 border-0">
							<div
								class="card-header py-3 bg-white d-flex justify-content-between align-items-center">
								<h6 class="m-0 font-weight-bold text-primary">사용자 후기</h6>
								<span class="badge badge-secondary" id="review_count">0</span>
							</div>
							<div class="card-body p-0">
								<div id="review_list">
									<div class="text-center py-5 text-gray-400">
										<i class="fas fa-comment-slash fa-3x mb-3"></i>
										<p>등록된 리뷰가 없습니다. 첫 리뷰를 작성해보세요!</p>
									</div>
								</div>
							</div>
						</div>
						<!-- 사용자 후기 END -->

					</div>
				</div>
			</div>
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
	<script src="/js/cart/cart.js"></script>
	<script src="/js/review/review.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
			console.log("jQuery 준비 완료!"); // 콘솔창(F12)에 이게 뜨는지 확인하세요.

			// 이벤트 위임 방식으로 작성 (더 확실한 방법)
			$(document).on('click', '#star_rating i', function() {
				const value = $(this).data('value');
				console.log("클릭한 별점: " + value); // 클릭 시 숫자가 뜨는지 확인

				$('#review_star').val(value);

				// 별 모양 변경
				$('#star_rating i').each(function() {
					if ($(this).data('value') <= value) {
						$(this).removeClass('far').addClass('fas');
					} else {
						$(this).removeClass('fas').addClass('far');
					}
				});
			});
		});
	</script>
</body>
</html>