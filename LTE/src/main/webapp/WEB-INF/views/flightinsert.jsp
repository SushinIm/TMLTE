<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>


function dynamicSelect(id1, id2) {
	if (document.getElementById && document.getElementsByTagName) {
		var sel1 = document.getElementById(id1);
		var sel2 = document.getElementById(id2);
		var clone = sel2.cloneNode(true);
		var clonedOptions = clone.getElementsByTagName("option");
		refreshDynamicSelectOptions(sel1, sel2, clonedOptions);
		sel1.onchange = function() {
			refreshDynamicSelectOptions(sel1, sel2, clonedOptions);
		};
	}
}

function refreshDynamicSelectOptions(sel1, sel2, clonedOptions) {
	while (sel2.options.length) {
		sel2.remove(0);
	}
	var pattern1 = /( |^)(select)( |$)/;
	var pattern2 = new RegExp("( |^)("
			+ sel1.options[sel1.selectedIndex].value + ")( |$)");
	for (var i = 0; i < clonedOptions.length; i++) {
		if (clonedOptions[i].className.match(pattern1)
				|| clonedOptions[i].className.match(pattern2)) {
			sel2.appendChild(clonedOptions[i].cloneNode(true));
		}
	}
}

function addLoadEvent(func) {
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
		window.onload = func;
	} else {
		window.onload = function() {
			if (oldonload) {
				oldonload();
			}
			func();
		}
	}
}

addLoadEvent(function() {
	dynamicSelect("SnationSelect", "ScitySelect");
	dynamicSelect("EnationSelect", "EcitySelect");
});
</script>
<body>

	기종 선택 : ${flightlist} 항공편 이름 :
	<input type="text" name="airname" />
	<div>
		<select id="SnationSelect" name="SnationSel">
			<option value="select">출발국가선택</option>
			<option value="sea">동남아</option>
			<option value="japen">일본</option>
			<option value="china">중국</option>
			<option value="sp">남태평양</option>
			<!-- 유지보수 도시 선택 -->
			<option value="ae">미주/유럽</option>
			<!-- 유지보수 도시 선택 -->
			<option value="korea">대한민국</option>
		</select> 
		<select id="ScitySelect" name="ScitySel">
			<option class="select" value="select">출발도시선택</option>
			<option class="sea" value="hongkong">홍콩</option>
			<option class="sea" value="bangkok">방콕</option>
			<option class="sea" value="manila">마닐라</option>
			<option class="sea" value="phuket">푸켓</option>
			<option class="sea" value="singapol">싱가포르</option>
			<option class="japen" value="dokyo">도쿄</option>
			<option class="japen" value="osaka">오사카</option>
			<option class="japen" value="okinawa">오키나와</option>
			<option class="japen" value="fukuoka">후쿠오카</option>
			<option class="china" value="shanghai">상하이</option>
			<option class="china" value="sanghae">상해</option>
			<option class="china" value="cheongdo">청도</option>
			<option class="china" value="shanghai">상하이</option>
			<option class="korea" value="seoul">서울</option>
			<option class="korea" value="busan">부산</option>
			<option class="korea" value="incheon">인천</option>
			<option class="korea" value="jejudo">제주도</option>
		</select>
	</div>
	<div>
	출발 시간 : 
		<select name="Shours">
	 		<option class="select" value="select">시</option>
	 		<option value="1시">1시</option>
	 		<option value="2시">2시</option>
	 		<option value="3시">3시</option>
	 		<option value="4시">4시</option>
	 		<option value="5시">5시</option>
	 		<option value="6시">6시</option>
	 		<option value="7시">7시</option>
	 		<option value="8시">8시</option>
	 		<option value="9시">9시</option>
	 		<option value="10시">10시</option>
	 		<option value="11시">11시</option>
	 		<option value="12시">12시</option>
	 		<option value="13시">13시</option>
	 		<option value="14시">14시</option>
	 		<option value="15시">15시</option>
	 		<option value="16시">16시</option>
	 		<option value="17시">17시</option>
	 		<option value="18시">18시</option>
	 		<option value="19시">19시</option>
	 		<option value="20시">20시</option>
	 		<option value="21시">21시</option>
	 		<option value="22시">22시</option>
	 		<option value="23시">23시</option>
	 		<option value="24시">24시</option>
	 	</select>
	 	<select name="Sminute">
			<option class="select" value="select">분</option>
			<option value="hongkong">00</option>
			<option value="bangkok">10</option>
			<option value="manila">20</option>
			<option value="manila">30</option>
			<option value="">40</option>
			<option value="">50</option>
		</select>
	 </div>
	 <div>
		<select id="EnationSelect" name="EnationSel">
			<option value="select">도착국가선택</option>
			<option value="sea">동남아</option>
			<option value="japen">일본</option>
			<option value="china">중국</option>
			<option value="sp">남태평양</option>
			<!-- 유지보수 도시 선택 -->
			<option value="ae">미주/유럽</option>
			<!-- 유지보수 도시 선택 -->
			<option value="korea">대한민국</option>
		</select> 
		<select id="EcitySelect" name="EcitySel">
			<option class="select" value="select">도착도시선택</option>
			<option class="sea" value="hongkong">홍콩</option>
			<option class="sea" value="bangkok">방콕</option>
			<option class="sea" value="manila">마닐라</option>
			<option class="sea" value="phuket">푸켓</option>
			<option class="sea" value="singapol">싱가포르</option>
			<option class="japen" value="dokyo">도쿄</option>
			<option class="japen" value="osaka">오사카</option>
			<option class="japen" value="okinawa">오키나와</option>
			<option class="japen" value="fukuoka">후쿠오카</option>
			<option class="china" value="shanghai">상하이</option>
			<option class="china" value="sanghae">상해</option>
			<option class="china" value="cheongdo">청도</option>
			<option class="china" value="shanghai">상하이</option>
			<option class="korea" value="seoul">서울</option>
			<option class="korea" value="busan">부산</option>
			<option class="korea" value="incheon">인천</option>
			<option class="korea" value="jejudo">제주도</option>
		</select>
	</div>
	<div>
	도착 시간 :
		<select name="Ehours">
	 		<option class="select" value="select">시</option>
	 		<option value="1시">1시</option>
	 		<option value="2시">2시</option>
	 		<option value="3시">3시</option>
	 		<option value="4시">4시</option>
	 		<option value="5시">5시</option>
	 		<option value="6시">6시</option>
	 		<option value="7시">7시</option>
	 		<option value="8시">8시</option>
	 		<option value="9시">9시</option>
	 		<option value="10시">10시</option>
	 		<option value="11시">11시</option>
	 		<option value="12시">12시</option>
	 		<option value="13시">13시</option>
	 		<option value="14시">14시</option>
	 		<option value="15시">15시</option>
	 		<option value="16시">16시</option>
	 		<option value="17시">17시</option>
	 		<option value="18시">18시</option>
	 		<option value="19시">19시</option>
	 		<option value="20시">20시</option>
	 		<option value="21시">21시</option>
	 		<option value="22시">22시</option>
	 		<option value="23시">23시</option>
	 		<option value="24시">24시</option>
	 	</select>
	 	<select name="Eminute">
			<option class="select" value="select">분</option>
			<option value="hongkong">00</option>
			<option value="bangkok">10</option>
			<option value="manila">20</option>
			<option value="manila">30</option>
			<option value="">40</option>
			<option value="">50</option>
		</select>
	 </div>
	 연락처 : 
	 경유 여부 : <input name="chkbox" type="checkbox" onClick="checkDisable(this.form)">텍스트박스 비활성<br>
	<input name="textbox" type="text">
</body>
</html>