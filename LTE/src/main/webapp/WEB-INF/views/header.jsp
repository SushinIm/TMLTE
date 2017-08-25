<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
        <meta name="description" content="company is a free job board template">
        <meta name="author" content="Ohidul">
        <meta name="keyword" content="html, css, bootstrap, job-board">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="icon" href="favicon.ico" type="image/x-icon">

        <link rel="stylesheet" href="resources/bootstrap/css/normalize.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/font-awesome.min.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/fontello.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/animate.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/owl.carousel.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/css/owl.theme.css" />
        <link rel="stylesheet" href="resources/bootstrap/css/owl.transitions.css" /> 
        <link rel="stylesheet" href="resources/bootstrap/style.css"> 
        <link rel="stylesheet" href="resources/bootstrap/responsive.css">
        
        <!-- <script src="js/vendor/modernizr-2.6.2.min.js"></script> -->
        <script src="resources/js/jquery-3.2.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="resources/bootstrap/js/vendor/jquery-1.10.2.min.js"><\/script>')</script>
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/bootstrap/js/owl.carousel.min.js"></script>
        <script src="resources/bootstrap/js/wow.js"></script>
        <script src="resources/bootstrap/js/main.js"></script>
   
    <style>
   .atag{
      text-decoration:none;
   }
   #imagesearch_layer , #login_layer{
      display: none;
      position: fixed;
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      margin: auto;

   }
   
   #imagesearch_layer.open, #login_layer.open {
      display: block;
      z-index:500;
      color: red
   }
   
   #imagesearch_layer #bg_layer1, #login_layer #bg_layer2 {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: #000;
      opacity: .5;
      filter: alpha(opacity = 50);
      z-index: 100;
      margin: auto;
   }
   
   #contents_layer {
      position: absolute;
      top: 40%;
      left: 40%;
      width: 400px;
      height: 400px;
      margin: -150px 0 0 -194px;
      padding: 28px 28px 0 28px;
      border: 2px solid #555;
      background: #fff;
      font-size: 12px;
      z-index: 200;
      color: #767676;
      line-height: normal;
      white-space: normal;
      overflow: scroll;
      margin: auto;
   }
   
   .front{
   		z-index:301;
   }
   
   .back{
   		z-index:300;
   }
    #loginId{
    	background-color:white;
    	margin-left:20px;
    	margin-right:5px;
    	border:medium none;
    	font-size:18px;
    	/* font-wieght:bolder; */
    	padding:8px 20px;
    	border-radius:2px;
    	margin-top:8px;
    	margin-bottom:8px;
    	text-transform: capitalize;
    }
</style>
    </head>
    <body>
        <div id="preloader">
            <div id="status">&nbsp;</div>
        </div>
        <!-- Body content -->
      
        <div class="header-connect">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 col-sm-8 col-xs-8">
                        <div class="header-half header-call">
                            <p>
                                <span><i class="icon-cloud"></i>+010 3075 8831</span>
                                <span><i class="icon-mail"></i>ujin8831@gmail.com</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <nav class="navbar navbar-default front">
      <div class="container">
         <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">LTE navigation</span>
               <span class="icon-bar"></span>         
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
            </button>
            <a href="./" class="navbar-brand href="#"><img src="resources/bootstrap/img/logo7.png" alt></a>
         </div>
         
         <!-- Class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <!-- 로그인 전 -->
              <div class="button navbar-right"><c:if test="${login==null}">
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" href="#login_layer" onclick="loginview()">Login</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='joinfrmbefore'">Sign up</button>
              </c:if></div>
              <!-- 로그인 -->
              <div class="button navbar-right"><c:if test="${login eq '일반'}">
                  <button id="loginId"><b>${sessionScope.id}</b>님</button>
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" onClick="location.href='logout'">Logout</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='mypage'">My page</button>
              </c:if></div>
              <!-- 로그인(판매자) -->
               <div class="button navbar-right"><c:if test="${login eq '숙박' || login eq '레저/입장권'}">
                  <button id="loginId"><b>${sessionScope.id}</b>님</button>   
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" onClick="location.href='logout'">Logout</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='mypageseller'">My page</button>
              </c:if></div>
              
              <ul class="main-nav nav navbar-nav navbar-right">
                <li class="wow fadeInDown" data-wow-delay="0s"><a class="active" href="main">Home</a></li>
                <li id="other" class="wow fadeInDown dropdown" data-wow-delay="0.1s">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="internals">국내</a>
	                	<ul class="dropdown-menu">
	                		<li><a href="airInternals">항공</a></li>
							<li><a href="hotelInternals">숙박</a></li>
							<li><a href="ticketInternals">티켓</a></li>	                	
	                	</ul>
                </li>
                <li class="wow fadeInDown dropdown" data-wow-delay="0.2s">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="overseas">해외</a>
                		<ul class="dropdown-menu">
	                		<li><a href="airOverseas">항공</a></li>
							<li><a href="hotelOverseas">숙박</a></li>
							<li><a href="">티켓</a></li>	                	
	                	</ul>
                </li>
                <li class="wow fadeInDown" data-wow-delay="0.3s"><a href="#">공지사항</a></li>
              </ul>
            </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
   </nav><!-- Header End -->