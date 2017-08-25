<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h1>판매자 마이페이지</h1>

<h3>판매자 상품 리스트</h3>
<div>${sellerList}<span id="bestProd">${best}</span></div>
<p>${msg}</p>
<h3>판매자 상품 구매자 회원 리스트</h3>
<div>${memberList}</div>
<p>${msg}</p>
<input type="hidden" value="${id}" id="id"/>
<input type="hidden" value="${part}" name="part"/>
<input type="button" value="홈으로" onclick="javascript:location.href='/LTE/'"/>
</body>
<script>
function bestProd(){  /* //아이디 중복체크 */
		$.ajax({
		      type: 'post',
		      url: 'ajax/bestProd',
		      data : {id:$('#id').val(), num:$('#num').val()},
		      dataType : 'json',
		      success: function(data){
		        	$('#bestProd').html(data);
		        	$('#bestProd').css('color','blue');
		      },
		      error:
		         function(error){
		         console.log(error);
		      }
		   });//ajax End
}
function relProd(){  /* //아이디 중복체크 */
		$.ajax({
		      type: 'post',
		      url: 'ajax/relProd',
		      data : {id:$('#id').val(), num:$('#num').val()},
		      dataType : 'json',
		      success: function(data){
		        	$('#bestProd').html(data);
		        	$('#bestProd').css('color','blue');
		      },
		      error:
		         function(error){
		         console.log(error);
		      }
		   });//ajax End
}
</script>
</html>