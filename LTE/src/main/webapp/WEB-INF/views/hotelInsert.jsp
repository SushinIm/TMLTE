<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
 	var pattern2 = new RegExp("( |^)(" + sel1.options[sel1.selectedIndex].value + ")( |$)");
 	for (var i = 0; i < clonedOptions.length; i++) {
  		if (clonedOptions[i].className.match(pattern1) || clonedOptions[i].className.match(pattern2)) {
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
dynamicSelect("nationSelect", "citySelect");
});
</script>
</head>
<body>
	<form action="fileFrm" method="post" enctype="multipart/form-data">
		<div>
			<input type="file" name="bfile" onChange="fileChk(this)" /> <input
				id="fileCheck" type="hidden" name="fileCheck" />
		</div>
		<div>
			<input type="text" name="krname" /> <input type="text" name="egname" />
		</div>
		<div>
			<select id="nationSelect">
				<option value="select">국가선택</option>
				<option value="sea">동남아</option>
				<option value="japen">일본</option>
				<option value="china">중국</option>
				<option value="sp">남태평양</option><!-- 유지보수 도시 선택 -->
				<option value="ae">미주/유럽</option><!-- 유지보수 도시 선택 -->
				<option value="korea">대한민국</option>
			</select> 
			<select id="citySelect">
				<option class="select" value="select">도시선택</option>
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
	</form>
</body>
</html>