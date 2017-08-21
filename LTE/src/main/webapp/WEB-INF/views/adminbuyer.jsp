<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
		console.log(today);
		location.href = "./blackbuyer?bnum="+bnum+"&stdate="+today+"&reason="+reason+"";
	}
</script>
</html>