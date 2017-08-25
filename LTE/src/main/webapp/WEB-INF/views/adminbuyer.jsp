<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>

<script>
$(function(){
	var msg ="${msg}"; 
	
	if(msg==""){
		
	}else if(msg=="${msg}"){
		alert(msg);
	}
});
</script>


<script>
	//$('#select').val();
	function addblack(m_id, obj){//추가버튼
		console.log(m_id);
	
		var str = $(obj).attr('class');
		var selectArr = $('td').children('select');
		var reason= "";
		//----------------------------------------------------
		for(var i=0;i<selectArr.length;i++){
			var object = selectArr[i];
			if($(object).attr('class')==str){
				reason = $(object).val();
			}
		}
	//----------------------------------------------------
		//alert("블랙리스트에 추가되었습니다.");
		location.href = "./blackbuyer?m_id="+m_id+"&reason="+reason;
	}
</script>

</head>
<body>
<p>구매자 리스트</p>
${buyerList}
<input type ="button" name = "돌아가기" value = "돌아가기" onclick = "location.href='./adminpage'">
</body>
</html>