<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 내역</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
<style>
    .cart-card { border-radius: 12px; overflow: hidden; border-left: 5px solid #4e73df; }
    .cart-img-wrapper { height: 160px; background-color: #f8f9fc; overflow: hidden; }
    .cart-img-wrapper img { width: 100%; height: 100%; object-fit: cover; }
    .card-header-custom { background-color: white; border-bottom: 1px solid #e3e6f0; display: flex; justify-content: space-between; align-items: center; padding: 10px 15px; }
    .control-panel { background-color: white; border-radius: 10px; padding: 15px; margin-bottom: 30px; border: 1px solid #e3e6f0; }
</style>
</head>
<body id="page-top">
    <div id="wrapper">
        <c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
                
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800 font-weight-bold">나의 장바구니</h1>

                    <div class="control-panel shadow-sm d-flex justify-content-between align-items-center">
                        <div>
                            <input type="checkbox" id="all"> <label for="all" class="font-weight-bold ml-1">전체 선택</label>
                        </div>
                        <div>
                            <button id="selectdel" class="btn btn-outline-danger btn-sm mr-1">선택 삭제</button>
                            <button class="btn btn-success btn-sm">선택 상품 가입</button>
                        </div>
                    </div>

                    <div class="row" id="list">
                        
                        
                        
                    </div>

                    <c:if test="${empty list}">
                        <div class="text-center py-5 text-gray-500">장바구니가 비어 있습니다.</div>
                    </c:if>
                </div>
            </div>
            <c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
        </div>
    </div>
    <c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
    <script src="/js/cart/list.js"></script>
</body>
</html>