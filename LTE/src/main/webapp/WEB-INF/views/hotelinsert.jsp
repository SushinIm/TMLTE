<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
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
		dynamicSelect("nationSelect", "citySelect");
	});
	var count = 0;
	$(function() {

		$('#omg')
				.click(
						function() {
							count = count + 1;
							var frm = "<div>객실명 <input class='r_name' type='text' name='r_name" + count +"'/> 객실번호 <input class='r_num' type='text' name='r_num" + count +"'/>"
									+ "객실 가격 <input class='r_price' type='text' name='r_price" + count +"'/> 최대인원 <input class='r_person' type='text' name='r_person" + count +"'/>"
									+ "<input type='button' value='삭제' onclick='deleteClick(this)'/></div>";
							$('#frm').append($(frm));
							console.log(count);
						});

		$('#hotelinsert').click(function() {
			alert('클릭');
			document.hotelInsertFrm.action = "hotelinsert?count=" + count;
			document.hotelInsertFrm.submit();
		});
	})
	function deleteClick(obj) {
		count = count - 1;

		var childArr = $('#frm').children('div');
		console.log(childArr.length);

		var arr = $(obj).siblings();
		var num = arr[0].name.substring(6);

		$(obj).parent().remove();

		for (var i = num - 1; i < childArr.length; i++) {
			console.log($(childArr[i]).children('.r_name').attr("name"));
			console.log("r_name" + i);
			//childArr[i].name = "r_name" + i;
			$(childArr[i]).children('.r_name').attr("name", "r_name" + i);
			$(childArr[i]).children('.r_num').attr("name", "r_num" + i);
			$(childArr[i]).children('.r_price').attr("name", "r_price" + i);
			$(childArr[i]).children('.r_person').attr("name", "r_person" + i);
		}

	}
</script>
<body>
	<div>
		<c:if test="${login eq '숙박'}">
			<form name="hotelInsertFrm" method="post"
				enctype="multipart/form-data">
				<div>
					<input type="file" name="mainfile" onChange="fileChk(this)" /> <input
						id="fileCheck1" type="hidden" name="fileCheck1" />
				</div>
				<div>
					호텔명(한글)<input type="text" name="krname" /> <br /> 호텔명(영어)<input
						type="text" name="egname" /> <br /> 
					<select id="nationSelect" name="nationSel">
						<option value="select">국가선택</option>
						<option value="sea">동남아</option>
						<option value="japen">일본</option>
						<option value="china">중국</option>
						<option value="sp">남태평양</option>
						<!-- 유지보수 도시 선택 -->
						<option value="ae">미주/유럽</option>
						<!-- 유지보수 도시 선택 -->
						<option value="korea">대한민국</option>
					</select> 
					<select id="citySelect" name="citySel">
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
					</select><br /> 연락처&nbsp
					<select name="phonefirst">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="032">032</option>
					</select> - <input type="text" name="phonecenter" /> - <input type="text"
						name="phonelast" /><br /> 
						주소<input type="text" name="addr" />
				</div>
				<input type="button" value="추가" id="omg" />
				<div id="frm">
					객실명 <input type="text" name="r_name0" /> 객실번호 <input type="text"
						name="r_num0" /> 객실가격 <input type="text" name="r_price0" /> 최대인원 <input
						type="text" name="r_person0" />
				</div>
				<div>
					<input type="file" name="subfile" onChange="fileChk(this)" /> <input
						id="fileCheck2" type="hidden" name="fileCheck2" />
				</div>
				<div>
					<input type="file" name="etcfile" onChange="fileChk(this)" /> <input
						id="fileCheck3" type="hidden" name="fileCheck3" />
				</div>
				<input type="button" value="글작성" id="hotelinsert" />
			</form>
		</c:if>
	</div>
</body>
<script>
	function fileChk(elem) { //파일 element받음
		console.log(elem.value);
		if (elem.value == "") {
			console.log("empty");
			$("#fileCheck1").val(0);//파일첨부 안함
			$("#fileCheck2").val(0);//파일첨부 안함
			$("#fileCheck3").val(0);//파일첨부 안함
		} else {
			$("#fileCheck1").val(1);//파일첨부 함 
			$("#fileCheck2").val(1);//파일첨부 함 
			$("#fileCheck3").val(1);//파일첨부 함 

		}
	}//function End
</script>
</html>