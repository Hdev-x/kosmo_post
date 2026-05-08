<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Search -->
	<form
		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
		<div class="input-group">
			<input type="text" class="form-control bg-light border-0 small"
				placeholder="Search for..." aria-label="Search"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary" type="button">
					<i class="fas fa-search fa-sm"></i>
				</button>
			</div>
		</div>
	</form>


	<ul class="navbar-nav ml-auto">

    <%-- 1. 로그인한 사용자 전용 메뉴 --%>
    <sec:authorize access="isAuthenticated()">
        
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown">
                <i class="fas fa-bell fa-fw"></i>
                <span class="badge badge-danger badge-counter">3+</span>
            </a>
            </li>

        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown">
                <i class="fas fa-envelope fa-fw"></i>
                <span class="badge badge-danger badge-counter">7</span>
            </a>
            </li>

        <div class="topbar-divider d-none d-sm-block"></div>

        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown"
                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                    <sec:authentication property="principal.name"/>
                </span> 
                
                <img class="img-profile rounded-circle" 
                     src="/files/member/<sec:authentication property='principal.profileDTO.fileName'/>">
            </a> 
            
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="/member/mypage"> 
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
                </a> 
                <a class="dropdown-item" href="#"> 
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Settings
                </a> 
                <a class="dropdown-item" href="#"> 
                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Activity Log
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> 
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout
                </a>
            </div>
        </li>
    </sec:authorize>

    <%-- 2. 로그인하지 않은 사용자용 (가입/로그인 아이콘) --%>
    <sec:authorize access="isAnonymous()">
        <li class="nav-item mx-3">
            <a href="/member/join" title="회원가입"><i class="fas fa-user-plus"></i></a>
        </li>
        <li class="nav-item mx-3">
            <a href="/member/login" title="로그인"><i class="fas fa-sign-in-alt"></i></a>
        </li>
    </sec:authorize>

</ul>



</nav>
<!-- End of Topbar -->