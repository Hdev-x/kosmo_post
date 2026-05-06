<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${list}" var="p" varStatus="i">
	<div class="col-xl-3 col-lg-4 col-md-6 mb-4">
		<div class="card cart-card border-0 shadow-sm">
			<div class="card-header-custom">
				<input class="ch" data-pn="${p.productNum}" type="checkbox"
					id="ch${i.index}">
				<button class="btn btn-sm btn-info del">삭제</button>
			</div>

			<div class="cart-img-wrapper">
				<c:choose>
					<c:when test="${not empty p.productFileDTO.fileName}">
						<img src="/files/product/${p.productFileDTO.fileName}" alt="...">
					</c:when>
					<c:otherwise>
						<div
							class="d-flex align-items-center justify-content-center h-100 text-gray-300">
							<i class="fas fa-image fa-3x"></i>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="card-body">
				<h5 class="card-title font-weight-bold text-gray-800">${p.productName}</h5>
				<a href="/product/detail?productNum=${p.productNum}"
					class="btn btn-primary btn-block btn-sm">상세보기</a>
			</div>
		</div>
	</div>
</c:forEach>