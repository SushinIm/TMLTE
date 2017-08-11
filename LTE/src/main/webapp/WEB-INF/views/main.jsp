<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LTE 여행사 메인 페이지</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<style>
#imagesearch_layer {
	display: none;
	position: fixed;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%
}

#imagesearch_layer.open {
	display: block;
	color: red
}

#imagesearch_layer #bg_layer {
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
	<div id="header">
		<div id="home">
			<a href="main.jsp"><img src="" /></a>
		</div>
		<div id="searchbar">
			<input type="text" id="searchtext" /><a href="#imagesearch_layer"
				onclick="searchview()">이미지검색</a>
		</div>
		<input type="hidden" value="판매자" name="m_part" />
		<form name="mypageForm" method="get">
			<input type="hidden" value="1" name="testvalue" /> <input
				type="button" value="판매자마이페이지" onclick="mypage(this)">
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
	<div id="bg_layer"></div>
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

	function searchview() {
		console.log('aa');
		$('#imagesearch_layer').addClass('open');
		/* console.log('bb');
		$.ajax({
			type:'get',
			url:'contents',
			timeout:30000, //대기시간(3초)이 지날 경우 에러 상태
			//dataType:html,json
			success:function(data){
				$('#contents_layer').html(data);
			},
			error:function(error){
				alert("error");
				console.log(error);
			}
		}); //ajax End*/
	} //function End 

	//LightBox 해제
	$(function() { //$(documnet).ready(function(){})
		var layerWindow = $('#imagesearch_layer');
		layerWindow.find('#bg_layer').mousedown(function(enver) {
			layerWindow.removeClass('open');
			return;
		}); //find End
		$(document).keydown(function(event) {
			console.log(event); //ESC 키 번호	
			if (event.keyCode != 27)
				return;
			if (layerWindow.hasClass('open')) {
				layerWindow.removeClass('open');
			}
		});//keydown End
	}); //function End
</script>
</html>