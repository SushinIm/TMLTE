<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="contents">
	</div>
</body>
<script>
	$(function(){
		$.ajax({
			type:'post',
			url:'./addPass',
			success:function(data){
				$('#contents').html(data);
			},
			error:function(error){
				console.log(error);
			}
		})
	});
</script>
</html>