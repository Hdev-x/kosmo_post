<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
    <c:when test="${not empty list and list.size() > 0}">
        <c:forEach items="${list}" var="r">
            <div class="review-item border-bottom py-3" id="review_item_${r.reviewNum}">
                <div class="d-flex justify-content-between align-items-start mb-2">
                    <div>
                        <div class="review-user font-weight-bold">
                            <i class="fas fa-user-circle mr-1"></i> ${r.username}
                        </div>
                        <div class="review-date text-muted" style="font-size: 0.85rem;">
                            <i class="far fa-clock mr-1"></i> ${r.reviewDate.toLocalDate()} 
                            <small>${r.reviewDate.toLocalTime().toString().substring(0,5)}</small>
                            <c:if test="${not empty r.updateDate}">
                                <small class="text-primary font-italic">(수정됨)</small>
                            </c:if>
                        </div>
                    </div>
                    
                    
                    <div class="review-actions" id="action_btns_${r.reviewNum}">
                        <c:if test="${sessionScope.member.username eq r.username}">
                            <button type="button" class="btn btn-sm btn-outline-primary mr-1" 
                                    onclick="editMode(${r.reviewNum})">
                                <i class="fas fa-edit"></i> 수정
                            </button>
                            <button type="button" class="btn btn-sm btn-outline-danger" 
                                    onclick="deleteReview(${r.reviewNum})">
                                <i class="fas fa-trash-alt"></i> 삭제
                            </button>
                        </c:if>
                    </div>
                </div>

                <div class="review-star mb-2 text-warning">
                    <c:forEach begin="1" end="5" var="i">
                        <i class="${i <= r.reviewStar ? 'fas' : 'far'} fa-star"></i>
                    </c:forEach>
                </div>

                <div class="review-text text-secondary" id="review_text_${r.reviewNum}">
                    ${r.reviewContents}
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="text-center py-5 text-muted">
            <i class="fas fa-comment-slash fa-3x mb-3 text-light"></i>
            <p>등록된 리뷰가 없습니다. 첫 리뷰를 작성해보세요!</p>
        </div>
    </c:otherwise>
</c:choose>

