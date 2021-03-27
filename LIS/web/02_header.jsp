<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>도서관정보시스템 LIS</title>
    <link rel="stylesheet" href="css/LIS.css?ver=1">
    <link rel="stylesheet" href="css/header_footer.css?ver=1">
    <link rel="stylesheet" href="css/register.css?ver=2">
    <link rel="stylesheet" href="css/LIS_Board.css?ver=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700">

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
<script>
    $(document).ready(function() {
        $(".menu-icon").on("click", function() {$("nav ul").toggleClass("showing");});
    });

    // Scrolling Effect
    $(window).on("scroll", function() {
        if($(window).scrollTop()) {
            $('nav').addClass('black');
            $('#nav_top').css('display','none');
            $('#nav_bottom').css('background','#42AEEC');
            $('#logo1').css('display','none');
            $('#logo2').css('display','inline');
        }
        else {
            $('nav').removeClass('black');
            $('#nav_top').css('display','block');
            $('#nav_bottom').css('background','white');
            $('#logo2').css('display','none');
            $('#logo1').css('display','inline');
        }
    })
</script>
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
                <ul id="nav_bottom" style="text-align: center; background: white">
                    <li id="logo1">
                        <a href="/main.do">
                            <img src="/img/LIS_logo.PNG" style="max-height: 92px; width: 250px">
                        </a>
                    </li>
                    <li id="logo2" style="display: none">
                        <a href="/main.do">
                            <img src="/img/LIS_logo_blue.PNG" style="max-height: 92px; width: 250px;">
                        </a>
                    </li>
                    <li><a href="#">Search</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="/boardMain.do?boardId=1">Board</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">My Library</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
