<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>도서관정보시스템 LIS</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/LIS.css?ver=1">
    <link rel="stylesheet" href="css/header_footer.css?ver=3">
    <link rel="stylesheet" href="css/register.css?ver=2">
    <link rel="stylesheet" href="css/LIS_Board.css?ver=3">
    <link rel="stylesheet" href="css/LIS_Search.css?ver=3">
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700">--%>

    <%--image slide library - slick --%>
    <link rel="stylesheet" type="text/css" href="slick/slick.css?ver=1"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css?ver=1"/>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <%--image slide library - slick --%>
    <script type="text/javascript" src="slick/slick.min.js"></script>
</head>
<body style="padding-top: 100px;">
<header style="min-width: 1400px; position: fixed; top: 0; z-index: 99999999">
    <nav>
        <div class="menu">
            <div style="position: relative;margin: 0 auto;font-weight: 600;z-index: 30;">
                <ul id="nav_top" style="line-height: 50px">
                    <c:if test="${loginUser == null}">
                        <li style="padding: 10px 40px">
                            <a href="/login.do">
                                <img src="/img/login_icon.PNG" style="width: 30px;">  Login
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${loginUser != null}">
                        <li style="padding: 0 40px">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${loginUser.name}님</a>
                            <ul class="dropdown-menu" style="text-align: center">
                                <li style="display: block; padding: 10px;"><a href="/myInfo.do">My Info</a></li>
                                <li style="display: block; padding: 10px;"><a href="/logout.do">Logout</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
                <ul id="nav_bottom" style="text-align: center; background: white; margin: 0 auto">
                    <li id="logo1" style="margin-top: 5px; width: 20%"><a href="/main.do"><img src="/img/LIS_logo.PNG" style="max-height: 92px; width: 250px"></a></li>
                    <li id="logo2" style="margin-top: 5px; width: 20%; display: none"><a href="/main.do"><img src="/img/LIS_logo_blue.PNG" style="max-height: 92px; width: 250px;"></a></li>
                    <li>
                        <a href="#">Search</a>
                        <ul class="nav-submenu">
                            <li><a class="nav-submenu-item" href="#">통합검색</a></li>
                            <li><a class="nav-submenu-item" href="/collectionSearch.do">소장자료</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Collections</a>
                        <ul class="nav-submenu">
                            <li><a class="nav-submenu-item" href="#">신착도서</a></li>
                            <li><a class="nav-submenu-item" href="#">추천100선</a></li>
                            <li><a class="nav-submenu-item" href="#">테마추천도서</a></li>
                            <li><a class="nav-submenu-item" href="#">베스트대출</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/boardMain.do?boardId=1">Board</a>
                        <ul class="nav-submenu">
                            <li><a class="nav-submenu-item" href="/boardMain.do?boardId=1">공지사항</a></li>
                            <li><a class="nav-submenu-item" href="/boardMain.do?boardId=2">Q&A</a></li>
                            <li><a class="nav-submenu-item" href="/boardMain.do?boardId=3">FAQ</a></li>
                            <li><a class="nav-submenu-item" href="/boardMain.do?boardId=4">독서토론방</a></li>
                            <li><a class="nav-submenu-item" href="/boardMain.do?boardId=5">일정</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">About</a>
                        <ul class="nav-submenu">
                            <li><a class="nav-submenu-item" href="#">도서관소개</a></li>
                            <li><a class="nav-submenu-item" href="#">도서관통계</a></li>
                            <li><a class="nav-submenu-item" href="#">HELP</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">My Library</a>
                        <ul class="nav-submenu">
                            <li><a class="nav-submenu-item" href="/manageMyLoanInfo.do">대출/예약/신청도서 관리</a></li>
                            <li><a class="nav-submenu-item" href="#">희망도서 신청 및 조회</a></li>
                            <li><a class="nav-submenu-item" href="#">독서이력 조회</a></li>
                            <li><a class="nav-submenu-item" href="#">연체료 납부현황 조회</a></li>
                            <li><a class="nav-submenu-item" href="#">즐겨찾는 도서</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script>
    $(document).ready(function() {
        $(".menu-icon").on("click", function() {$("nav ul").toggleClass("showing");});
    });

    // Scrolling Effect
    $(window).on("scroll", function() {
        if($(window).scrollTop()) {
            $('#nav_top').css('display','none');
            $('#nav_bottom').css('background','#42AEEC');
            $('#logo1').css('display','none');
            $('#logo2').css('display','inline');
        }
        else {
            $('#nav_top').css('display','block');
            $('#nav_bottom').css('background','white');
            $('#logo2').css('display','none');
            $('#logo1').css('display','inline');
        }
    })

    // navbar mouseover event
    $("#nav_bottom").hover(function() {
        $(".nav-submenu").addClass('active');
        $("#nav_bottom").css("background","#f6f6f6");
    }, function(){
        $(".nav-submenu").removeClass('active');
        if($(window).scrollTop()) {
            $("#nav_bottom").css("background", "#42AEEC");
        }else{
            $("#nav_bottom").css("background", "white");
        }
    });
</script>
