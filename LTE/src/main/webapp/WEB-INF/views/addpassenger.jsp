<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
</head>
<style>
	ul{
		list-style: none;
		clear:both;
	}
	ul li{
		float:left;
		width:10%;
		padding: 3px;
	}
</style>
<body>
	<form name="addPassFrm" action="./addPass" method="get">
		<h2>탑승객 정보</h2>
		<p>불라불라불라불라불라불라불라불라불라불라불라불라</p>
		<p>불라불라불라불라불라불라불라불라불라불라불라불라</p>
		<p>불라불라불라불라불라불라불라불라불라불라불라불라</p>
		<p>불라불라불라불라불라불라불라불라불라불라불라불라</p>
		<p>불라불라불라불라불라불라불라불라불라불라불라불라</p>
		<input type="button" value="탑승객 추가" onclick="formAdd()"/>
		<ul>
			<li>구분</li>
			<li>성별</li>
			<li>영문 성</li>
			<li>영문 이름</li>
			<li>한글 이름</li>
			<li>생년월일</li>
		</ul>
		<div id="passenger">
			<ul class="passFrm">
				<li>
					<select name="part1">
						<option>구분 선택</option>
						<option value="0">성인</option>
						<option value="1">유아</option>
					</select>
				</li>
				<li>
					<select name="gender1">
						<option>성별 선택</option>
						<option value="남자">남자</option>
						<option value="여자">여자</option>
					</select>
				</li>
				<li>	
					<input type="text" name="engfname1" placeholder="영문 성 입력"/>
				</li>
				<li>
					<input type="text" name="englname1" placeholder="영문 이름 입력"/>
				</li>
				<li>
					<input type="text" name="korname1" placeholder="한글 이름 입력"/>
				</li>
				<li>
					<input type="text" name="birth1" placeholder="생년월일 입력"/>
				</li>
				<li>
					<input type="button" class="delBtn" value="삭제" onclick="formRemove(this)"/>
				</li>
			</ul>
		</div>
		<input type="button" value="등록" id="adder"/>
	</form>
</body>
<script>
	var cnt = 1;
	var del = '<li><input type="button" class="delBtn" value="삭제" onclick="formRemove(this)"/></li>';
	function formAdd(){
		console.log(++cnt);
		$('div#passenger > ul:last input.delBtn').parent().remove();
    	$('.passFrm').clone().appendTo('#passenger');
    	$('div#passenger > ul').removeAttr('class');
   	 	$('div#passenger > ul:first').attr('class', 'passFrm');
   	 	$('div#passenger > ul:last select[name=part1]').attr('name', 'part'+cnt);
   	 	$('div#passenger > ul:last select[name=gender1]').attr('name', 'gender'+cnt);
   	 	$('div#passenger > ul:last input[name=engfname1]').attr('name', 'engfname'+cnt);
   	 	$('div#passenger > ul:last input[name=englname1]').attr('name', 'englname'+cnt);
   	 	$('div#passenger > ul:last input[name=korname1]').attr('name', 'korname'+cnt);
   	 	$('div#passenger > ul:last input[name=birth1]').attr('name', 'birth'+cnt);
   	 	$("div#passenger > ul:last select[name=part1] option:eq(0)").prop("selected", true);
   	 	$("div#passenger > ul:last select[name=gender1] option:eq(0)").prop("selected", true);
	   	$('div#passenger > ul:last input[name=engfname1]').empty();
	   	$('div#passenger > ul:last input[name=englname1]').empty();
	   	$('div#passenger > ul:last input[name=korname1]').empty();
	   	$('div#passenger > ul:last input[name=birth1]').empty();
   	 	$('div#passenger > ul:last').append(del);
	}

	function formRemove(obj){
	    if(cnt<=1){
	        alert('좌석이 한개 이하일 경우 폼 삭제가 불가능합니다.');
	        console.log(cnt);
	    }else{
    	    $(obj).parent().parent().remove();
    	    $('div#passenger > ul:last').append(del);
    	    console.log(--cnt);
    	}
	}
	$(function(){
		$('#adder').click(function(){
			var hidcnt = '<input type="hidden" name="cnt" value="'+cnt+'" />';
			$('div#passenger').append(hidcnt);
			document.addPassFrm.submit();
		})
	})
</script>
</html>