<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/animate.css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" href="resources/bootstrap/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/bootstrap/css/fontello.css" />
<link rel="stylesheet" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" href="resources/bootstrap/css/owl.carousel.css" />
<link rel="stylesheet" href="resources/bootstrap/css/owl.theme.css" />
<link rel="stylesheet" href="resources/bootstrap/css/owl.transitions.css" />



<title>LTE 항공사 메인 페이지</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
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
		height: 100%
	}
	
	#imagesearch_layer.open, #login_layer.open {
		display: block;
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
		z-index: 100
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
		overflow: scroll
	}

</style>
</head>
<body>
	<h1>LTE 메인 페이지</h1>
	<div id="header">
		<div id="home">
			<a href="main.jsp"><img src="" /></a>
		</div>
		<div id="searchbar">
			<input type="text" id="searchtext" /><a href="#imagesearch_layer"
				onclick="searchview()">이미지검색</a>
		</div>
		<br/>
		<!-- 로그인전 -->
		<div><c:if test="${login==sessionScope.id}">
			<a class="atag" href="#login_layer" onclick="loginview()">로그인  </a> |
			<a class="atag" href="joinfrmbefore">회원가입  </a> |
			<a class="atag" href="#login_layer" onclick="loginview()">마이 페이지</a>
		</c:if></div> 
		<!-- 로그인 -->
		<div><c:if test="${login eq '일반'}">
			<a href="logout">로그아웃 (일반)</a> | 
			<a href="mypage">마이 페이지(일반)</a>
			<h1>${sessionScope.id}님 환영합니다.</h1>
		</c:if></div>
		<div><c:if test="${login eq '숙박' || login eq '레저/입장권'}">
			<a href="logout">로그아웃(판매자)  </a> | 
			<a href="mypageseller">마이 페이지(판매자)</a>
			<h1>${sessionScope.id}님 환영합니다.</h1>
		</c:if></div>
		<br/>
		<input type="hidden" value="판매자" name="m_part"/>
		<form name="mypageForm" method="get">
			<input type="button" value="판매자마이페이지" onclick="mypage(this)">
		</form>
		
	</div>
	<div id="menu">
		<ul class="horizonul">
			<li><span>국내</span>
				<ul class="verticalul">
					<li><a href="airInternals">항공</a></li>
					<li><a href="hotelInternals">숙박</a></li>
					<li><a href="ticketInternals">티켓</a></li>
				</ul></li>
			<li>해외</li>
			<li>정보</li>
		</ul>
	</div>
	<div id="main"></div>
	<div id="footer">
		<form name="prodForm">
			<input id="sellerProd" type="button" value="상품등록"
				onclick="insertProd()">
		</form>

	</div>
</body>
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
<<<<<<< HEAD
<a href="/tkdetail">눌러보시게</a>
<a href="/htdetail">눌러보시게</a>
<a href="/ardetail">눌러보시게</a>
=======
<div id="login_layer">
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
>>>>>>> 5dea1fb7d4188a66214634538c785d394404db0a
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