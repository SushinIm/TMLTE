<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div id="imageupload">
		<form action="imageupload" method="post" enctype="multipart/form-data">
			<input type="file" name="image"/>
			<button id="imagesearch">검색</button>
		</form>
	</div>
</body>
<script>
$('#imagesearch').click(function({
	$.ajax({
		type:'get',
		url:'ajax/imagesearch',
		data:{bnum:bnum, comment:$("#comment").val()},
		//$('#rFrm').serialize(),  폼 전체 데이터 전송 ->값이 많을때			
		timeout:3000, //대기시간(3초)이 지날 경우 에러 상태
		dataType:'json',
		//dataType:html,json
		success:function(data){ //댓글 리스트 json형태로 반환
			console.log(data); //map->json구조 파악
			//console.log(data[0]);
			//console.log(data[0].r_contents);
 			var rlist='';
			for(var i=0; i<data.length; i++){
				rlist+='<tr height="25" align="center">'
					+'<td width="100">'+data[i].r_id+'</td>'
					+'<td width="200">'+data[i].r_contents+'</td>'
					+'<td width="200">'+data[i].r_date+'</td></tr>';
			}
			$('#rTable').html(rlist);  
			/* var rlist='';
			var obj=object.values(data)[0];
			for(var i=0; i<obj.length; i++){
				rlist+='<tr height="25" align="center">'
					+'<td width="100">'+obj[i].r_id+'</td>'
					+'<td width="200">'+obj[i].r_contents+'</td>'
					+'<td width="200">'+obj[i].r_date+'</td></tr>';
			}
			$('#rTable').html(rlist);  */
			//$('#contents_layer').html(data);
		},
		error:function(error){
			alert("error");
			console.log(error);
		}
	}); //ajax End
}) //replyInsert End
</script>
</html>