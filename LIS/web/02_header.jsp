<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>도서관정보시스템 LIS</title>
    <link rel="stylesheet" href="css/header_footer.css?ver=2">
    <link rel="stylesheet" href="css/register.css?ver=1">
    <link rel="stylesheet" href="css/LIS.css?ver=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<header style="min-width: 1400px">
    <nav>
        <div class="menu-icon"><i class="fa fa-bars fa-2x"></i></div>
        <div class="logo">LOGO</div>
        <div class="menu">
            <ul>
                <li><a href="/login.do">Login</a></li>
                <li><a href="/register.do">Register</a></li>
            </ul>
        </div>
    </nav>
</header>
<script>
    $(document).ready(function() {
        $(".menu-icon").on("click", function() {$("nav ul").toggleClass("showing");});
    });

    // Scrolling Effect
    $(window).on("scroll", function() {
        if($(window).scrollTop()) {$('nav').addClass('black');}
        else {$('nav').removeClass('black');}
    })
</script>
