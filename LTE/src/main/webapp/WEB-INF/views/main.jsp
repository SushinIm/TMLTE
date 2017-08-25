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
    	padding:8px 20px;
    	/* border-radius:2px; */
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
                  <button id="loginId"><b>${sessionScope.id}</b>&nbsp;님</button>
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" onClick="location.href='logout'">Logout</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='mypage'">My page</button>
              </c:if></div>
              <!-- 로그인(판매자) -->
               <div class="button navbar-right"><c:if test="${login eq '숙박' || login eq '레저/입장권'}">
                  <button id="loginId"><b>${sessionScope.id}</b>&nbsp;님</button>   
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" onClick="location.href='logout'">Logout</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='mypageFrm'">My page</button>
              </c:if></div>
               <div class="button navbar-right"><c:if test="${login eq '관리자'}">
                  <button id="loginId"><b>${sessionScope.id}</b>&nbsp;님</button>   
                  <button class="navbar-btn nav-button wow bounceInRight login" data-wow-delay="0.8s" onClick="location.href='logout'">Logout</button>
                  <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.6s" onClick="location.href='adminpage'">관리자</button>
              </c:if></div>
              <ul class="main-nav nav navbar-nav navbar-right">
                <li class="wow fadeInDown" data-wow-delay="0s"><a class="active" href="main">Home</a></li>
                <li id="other" class="wow fadeInDown dropdown" data-wow-delay="0.1s">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="internals">국내</a>
	                	<ul class="dropdown-menu">
	                		<li><a href="airbook">항공</a></li>
							<li><a href="hotelbook">숙박</a></li>
							<li><a href="ticket">티켓</a></li>	                	
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

        <div class="slider-area back">
            <div class="slider">
                <div id="bg-slider" class="owl-carousel owl-theme">
                  <div class="item"><img src="resources/bootstrap/img/img1.jpg" alt="img1"></div>
                  <div class="item"><img src="resources/bootstrap/img/img2.jpg" alt="img2"></div>
                  <div class="item"><img src="resources/bootstrap/img/img3.jpg" alt="img3"></div>
                  <div class="item"><img src="resources/bootstrap/img/img4.jpg" alt="img4"></div>
                  <div class="item"><img src="resources/bootstrap/img/img5.jpg" alt="img5"></div>
                </div>
            </div>
         <div class="container slider-content">
           <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
                    <h2>LTE(Life Travel Enjoy)</h2><br><br>
                    <div class="search-form wow pulse" data-wow-delay="0.8s">
                     <form action="" class=" form-inline">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="이미지검색" />
                        		<input type="button" class="btn" href="#imagesearch_layer" onclick="searchview()" value="Search" /> 
                            </div>
                  	 </form>
                    </div>
                </div>
            </div>
          </div>
        </div>

        <div class="content-area">
            <div class="container">
                <div class="row page-title text-center wow zoomInDown" data-wow-delay="1s">
                    <h2>최고의 여행을 즐길 준비가 되셨습니까?</h2>
                    <p>LTE Air와 함께하세요!</p>
                </div>
                <div class="row how-it-work text-center">
                    <div class="col-md-4">
                        <div class="single-work wow fadeInUp" data-wow-delay="0.8s">
                            <img src="img/how-work1.png" alt="">
                            <h3>Searching for the best job</h3>
                            <p>Using the outcomes from the job, we will put together a plan for the most effective marketing strategy to get the best results.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-work  wow fadeInUp"  data-wow-delay="0.9s">
                            <img src="img/how-work2.png" alt="">
                            <h3>Searching for the best job</h3>
                            <p>Using the outcomes from the job, we will put together a plan for the most effective marketing strategy to get the best results.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-work wow fadeInUp"  data-wow-delay="1s">
                            <img src="img/how-work3.png" alt="">
                            <h3>Searching for the best job</h3>
                            <p>Using the outcomes from the job, we will put together a plan for the most effective marketing strategy to get the best results.</p>
                        </div>
                    </div>
                </div>
            </div>
            <hr>

            <div class="container">
                <div class="row job-posting wow fadeInUp" data-wow-delay="1s">
                    <div role="tabpanel">
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#job-seekers" aria-controls="home" role="tab" data-toggle="tab">Hotel</a></li>
                        <li role="presentation"><a href="#employeers" aria-controls="profile" role="tab" data-toggle="tab">Ticket</a></li>
                      </ul>

                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade in active" id="job-seekers">
                            <ul class="list-inline job-seeker">
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel1.jpg" alt="">
                                        <div class="overlay"><h3>dokyo</h3><!-- <p>Web Designer</p> --></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel2.jpg" alt="">
                                        <div class="overlay"><h3>shanghai</h3><!-- <p>CEO</p> --></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel3.jpg" alt="">
                                        <div class="overlay"><h3>manila</h3><!-- <p>Graphic Designer</p> --></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel4.jpg" alt="">
                                        <div class="overlay"><h3>hongkong</h3><!-- <p>Graphic Designer</p> --></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel5.jpg" alt="">
                                        <div class="overlay"><h3>jejudo</h3><!-- <p>Founder</p> --></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/hotel6.jpg" alt="">
                                        <div class="overlay"><h3>bangkok</h3><!-- <p>Web Developer</p> --></div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="employeers">
                            <ul class="list-inline">
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket1.jpg" alt="">
                                        <div class="overlay"><h3>유니버셜</h3></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket2.jpg" alt="">
                                        <div class="overlay"><h3>프랑스</h3></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket3.jpg" alt="">
                                        <div class="overlay"><h3>런던</h3></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket4.jpg" alt="">
                                        <div class="overlay"><h3>동남아</h3></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket5.jpg" alt="">
                                        <div class="overlay"><h3>이탈리아</h3></div>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img width="170" height="190" src="resources/bootstrap/img/ticket6.JPG" alt="">
                                        <div class="overlay"><h3>마드리드</h3></div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                      </div>

                    </div>
                </div>
            </div>
            <hr>

<!--             <div class="container">
                <div class="row page-title text-center wow bounce"  data-wow-delay="1s">
                    <h5>Recent Jobs</h5>
                    <h2><span>54716</span> Available jobs for you</h2>
                </div>
                <div class="row jobs">
                    <div class="col-md-9">
                        <div class="job-posts table-responsive">
                            <table class="table">
                                <tr class="odd wow fadeInUp" data-wow-delay="1s">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo1.png" alt=""></td>
                                    <td class="tbl-title"><h4>Web Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>dribbble community</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="even wow fadeInUp" data-wow-delay="1.1s">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo2.png" alt=""></td>
                                    <td class="tbl-title"><h4>Front End Developer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Jolil corporation</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="odd wow fadeInUp" data-wow-delay="1.2s">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo3.png" alt=""></td>
                                    <td class="tbl-title"><h4>HR Manager <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Fanta bevarage</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="even wow fadeInUp" data-wow-delay="1.3s">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo4.png" alt=""></td>
                                    <td class="tbl-title"><h4>Internship Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Google</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="odd wow fadeInUp" data-wow-delay="1.4s">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo5.png" alt=""></td>
                                    <td class="tbl-title"><h4>Software Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Microsoft</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="even hide-jobs">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo4.png" alt=""></td>
                                    <td class="tbl-title"><h4>Internship Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Google</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="odd hide-jobs">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo5.png" alt=""></td>
                                    <td class="tbl-title"><h4>Software Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Microsoft</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="even hide-jobs">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo4.png" alt=""></td>
                                    <td class="tbl-title"><h4>Internship Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Google</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                                <tr class="odd hide-jobs">
                                    <td class="tbl-logo"><img src="resources/bootstrap/img/job-logo5.png" alt=""></td>
                                    <td class="tbl-title"><h4>Software Designer <br><span class="job-type">full time</span></h4></td>
                                    <td><p>Microsoft</p></td>
                                    <td><p><i class="icon-location"></i>San Franciso, USA</p></td>
                                    <td><p>&dollar; 14000</p></td>
                                    <td class="tbl-apply"><a href="#">Apply now</a></td>
                                </tr>
                            </table>
                        </div>
                        <div class="more-jobs">
                            <a href=""> <i class="fa fa-refresh"></i>View more jobs</a>
                        </div>
                    </div>
                    <div class="col-md-3 hidden-sm">
                        <div class="job-add wow fadeInRight" data-wow-delay="1.5s">
                            <h2>Seeking a job?</h2>
                            <a href="#">Create a Account</a>
                        </div>
                    </div>
                </div>
            </div>
            <hr>-->
            <div class="container">
                <div class="row page-title text-center  wow bounce"  data-wow-delay=".7s">
                    <h5>공지사항</h5>
                    <h2>NOTICE</h2>
                </div>
                <div class="row testimonial">
                    <div class="col-md-12">
                        <div id="testimonial-slider">
                            <div class="item">
                                <div class="client-text">                                
                                    <p>Jobify offer an amazing service and I couldn’t be happier! They 
                                    are dedicated to helping recruiters find great candidates, wonderful service!</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face wow fadeInRight" data-wow-delay=".9s"> 
                                    <img src="resources/bootstrap/img/client-face1.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">                                
                                    <p>Jobify offer an amazing service and I couldn’t be happier! They 
                                    are dedicated to helping recruiters find great candidates, wonderful service!</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="resources/bootstrap/img/client-face2.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">                                
                                    <p>Jobify offer an amazing service and I couldn’t be happier! They 
                                    are dedicated to helping recruiters find great candidates, wonderful service!</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="resources/bootstrap/img/client-face1.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">                                
                                    <p>Jobify offer an amazing service and I couldn’t be happier! They 
                                    are dedicated to helping recruiters find great candidates, wonderful service!</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="resources/bootstrap/img/client-face2.png" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
         <div class="footer-area">
            <div class="container">
                <div class="row footer">
                    <div class="col-md-4">
                        <div class="single-footer">
                            <img src="resources/bootstrap/img/footer-logo.png" alt="" class="wow pulse" data-wow-delay="1s">
                            <!-- <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati architecto quaerat facere blanditiis tempora sequi nulla accusamus, possimus cum necessitatibus suscipit quia autem mollitia, similique quisquam molestias. Vel unde, blanditiis.</p> --> 
                        </div>
                    </div>
                  <div class="col-md-4">
                        <div class="single-footer">
                            <h4>LTE AIR</h4>
                            <div class="twitter-updates">
                                <div class="single-tweets">
                                    <h5>찾아오시는 길 : </h5>
                                    <p><strong>070-4699-0972,</strong><br>
                                      	 인천 남구 매소홀로488번길 6-32 태승빌딩 5층<br>
									[지번] 인천 남구 학익동 663-1 태승빌딩 5층</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-footer">
                            <h4>LTE TEAM</h4>
                            <div class="footer-links">
                                <ul class="list-unstyled">
                                    <li><a href="#">Lee Hong Kyu</a></li>
                                    <li><a href="#" class="active">Sim Young Ho</a></li>
                                    <li><a href="#">Bae Mi Jung</a></li>
                                    <li><a href="#">Im Su Sin</a></li>
                                    <li><a href="#">An Yu Jin</a></li>
                                </ul>
                            </div>
                        </div>
                    </div> 
                </div>
                <div class="row footer-copy">
                    <p><span>Copyright @ Allrights Reserved</span> | <span>Graphic Designed by <a>LTE TEAM</a></span> | <span> Web Designed by <a>LTE TEAM</a></span> </p>
                </div>
            </div>
        </div>
<div id="imagesearch_layer">
   <div id="bg_layer1"></div>
   <div id="contents_layer">
      <div id="searchpage">
         <form action="imagesearch" method="post"
            enctype="multipart/form-data">
            <input type="file" name="image" />
            <button>검색</button> 
         </form>
      </div>
      ${airlist} ${hotellist} ${ticketlist}
   </div>
</div>
<div id="login_layer" >
   <div id="bg_layer2"></div>
         <div id="contents_layer">
         <div id="loginpage">
            <form name="loginfrm" action="access" method="post" >
                        아이디 : <input type="text" name="m_id" placeholder="아이디"/></br>
                        비밀번호 : <input type="password" name="m_pw" placeholder="비밀번호"/>
               <input type="submit" value="로그인" />
            </form></br>
            <a href="finding"><button>아이디/비밀번호찾기</button></a></br>
            <a href="joinfrmbefore"><button>회원가입</button></a>
         </div>
      </div>
</div>
</body>

<script>
   function insertProd() {
      document.prodForm.action = "insertProdFrm";
      document.prodForm.submit();
   }
   function mypage(elem) { //파일 element받음
      var frm = document.mypageForm;
      //if('${m_part}' == "판매자"){
      frm.action = "mypageFrm";
      frm.submit();
      //}
   }//function End

   function searchview() { //LightBox 열기
      console.log('aa');
      $('#imagesearch_layer').addClass('open');
      } //ajax End

   function loginview() {
         $('#login_layer').addClass('open');
      } //function End 
   //LightBox 해제
   $('#internals').click(function(){
	   $('.dropdown-toggle').dropdown();
   })
   
   $('#overseas').click(function(){
	   $('.dropdown-toggle').dropdown();
   })
      
   $(function() { //$(documnet).ready(function(){})
      var layerWindow1 = $('#imagesearch_layer');
      layerWindow1.find('#bg_layer1').mousedown(function(enver) {
         layerWindow1.removeClass('open');
         return;
      }); //find End
      $(document).keydown(function(event) {
         console.log(event); //ESC 키 번호   
         if (event.keyCode != 27)
            return;
         if (layerWindow1.hasClass('open')) {
            layerWindow1.removeClass('open');
         }
      });//keydown End
   }); //function End
   
   $(function() { //$(documnet).ready(function(){})
      var layerWindow2 = $('#login_layer');
      layerWindow2.find('#bg_layer2').mousedown(function(enver) {
         layerWindow2.removeClass('open');
         return;
      }); //find End
      $(document).keydown(function(event) {
         console.log(event); //ESC 키 번호   
         if (event.keyCode != 27)
            return;
         if (layerWindow2.hasClass('open')) {
            layerWindow2.removeClass('open');
         }
      });//keydown End
   }); //function End
</script>
</html>