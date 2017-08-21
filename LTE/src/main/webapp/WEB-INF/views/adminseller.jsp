<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<판매자 리스트>
${mlist}
</body>

<script>
	//$('#select').val();
	function addblack(bnum){//추가버튼
		//val value = $('#select option:selected').val();
		alert("블랙리스트에 추가되었습니다.");
		//--------------------------------------------------
		
		
		
		//----------------------------------------------------
		var reason = $("select option:selected").val();
		var date = new Date();
		var yy = date.getFullYear();
		var mm = date.getMonth()+1;
		var dd = date.getDate();
		if(mm<10){
			if(dd<10){
				var today = yy+'-0'+mm+'-0'+dd;	
			}
			else{
				var today = yy+'-0'+mm+'-'+dd;
			}
		}else{
			if(dd<10){
				var today = yy+'-'+mm+'-0'+dd;
			}else{
				var today = yy+'-'+mm+'-'+dd;
			}
		}
		console.log("asdasd?!#S");
		location.href = "./blackseller?m_bnum="+bnum+"&stdate="+today+"&reason="+reason+"";
	}
</script>

</html>