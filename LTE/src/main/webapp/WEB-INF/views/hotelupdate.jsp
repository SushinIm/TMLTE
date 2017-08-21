<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>

</head>
<script>
	var maxcount = '${count}';
	maxcount = Number(maxcount);
	$(function() {
		$('#omg')
				.click(
						function() {
							maxcount = maxcount + 1;
							var frm = "<div>객실명 <input type='text' name='r_name" + maxcount +"'/> 객실번호 <input type='text' name='r_num" + maxcount +"'/>"
									+ "객실 가격 <input type='text' name='r_price" + maxcount +"'/> 최대인원 <input type='text' name='r_person" + maxcount +"'/></div>";
							$('#frm1').append($(frm));
							console.log(maxcount);
						});
	});

	var br_num = 0;
	function deleteClick(obj, count, a) {
		br_num = document.getElementsByName("br_num" + a)[0].value;
		console.log(br_num);
		deleteclck(obj, count);
	}

	function deleteclck(obj, count) {

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
	function allupdateclick() {
		document.hotelupdateFrm.action = "allupdateclick?maxcount=" + maxcount
				+ "&br_num=" + br_num;
		document.hotelupdateFrm.submit();
	}
</script>
<body>
	<form name="hotelupdateFrm" method="post" enctype="multipart/form-data">
		${updatehtml}<br> <input type="hidden" name="pnum"
			value="${pnum}" /> <input type="hidden" name="count" id='maxcount'
			value="${count}" />
	</form>
</body>
<script>
	function updateClick(obj, a) {
		var r_num = document.getElementsByName("r_num" + a)[0].value;
		var r_name = document.getElementsByName("r_name" + a)[0].value;
		var r_price = document.getElementsByName("r_price" + a)[0].value;
		var r_person = document.getElementsByName("r_person" + a)[0].value;
		
		/* document.hotelupdateFrm.action = "updateClick?r_num=" + r_num
				+ "&r_name=" + r_name + "&r_price=" + r_price + "&r_person="
				+ r_person;
		document.hotelupdateFrm.submit(); */
		
		$.ajax({
			type:'get',
			url:'ajax/updateClick',
			data:{r_num:r_num, r_name:r_name, r_price:r_price, r_person:r_person},
			//$('#rFrm').serialize(), 폼 전체 데이터 전송
			timeout:3000,	//대기시간(3초)이 지날 경우 에러 상태
			dataType:'json',
			success:function(data){//댓글 리스트 json형태로 반환
				console.log(data);//json 구조 파악
				$('#ajaxroom').html(plist); 
			},
			error:function(error){
				alert("error");
				console.lot(error);
			}
		});//ajax End

	}

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