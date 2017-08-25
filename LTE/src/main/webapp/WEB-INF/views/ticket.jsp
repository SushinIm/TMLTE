<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table{
		border: 1px solid black;
		padding: 30px;
		width:542px;
	}
	
	#searchroom{
		padding-top: 30px;
	}
	
	ul{
		list-style:none;
		padding:0px;
	}
	
	.best{
		display:inline-block;
		width: 250px;
		height: 300px;
	}
	
	.totbest{
		text-align:center;
		height: 650px;
		padding-top:50px;
		margin-top:30px;
		border:1px solid black;
	}
	
	.contents{
		width:542px;
	}
	
	.besttitle{
		margin-bottom:10px;
	}
	
	.option li{
		margin:0 0 0 0;
		padding-right:20px;
		border:0;
		float:right;
	}
	
	.totsearch{
		display:none;
		padding-top:20px;
		margin-top:30px;
		border:1px solid black;
	}
	
	.searchtitle{
		padding-left:10px;
		width:170px;
	}
</style>
</head>
<body>
	<div class="contents">
		<div>
			<table>
				<tr><td colspan="2">여행지역</td><td>상품유형</td><td>상품명</td></tr>
				<tr><td><input type="select" id="nation" name="nation" value="국가선택"/></td><td><input type="select" id="city" name="city" value="도시선택"/></td>
				<td><input type="select" id="type" name="type" value="상품유형"/></td><td><input type="text" id="tname" name="tname" placeholder="상품명입력"/></td></tr>
				<tr><td colspan="4" align="center" id="searchticket"><input type="button" id="search" value="검색하기"><input type="reset" id="reset" value="초기화" onclick="reset(this)")></td></tr>
			</table>
		</div>
		<div class="totbest">
			<div class="besttitle">추천티켓</div>
			${ticketbest}
		</div>
		<div class="totsearch">
			<div class="searchtitle">티켓검색 결과입니다.</div>
				<ul class="option">
					<li><a href="ticketname">티켓 이름순</a></li>
					<li><a href="tickethigh">높은 가격순</a></li>
					<li><a href="ticketlow">낮은 가격순</a></li>
				</ul>
			<div class='result'>
			</div>
		</div>
	</div>
</body>
<script>
	$('#search').click(function(){
		if($('#nation').val()==""){
			alert("국가를 선택해주세요.");
			$('#nation').focus();
		}else if($('#city').val()==""){
			alert("도시를 선택해주세요.");
			$('#city').focus();
		}else{
			$.ajax({
	 		      type: 'post',
	 		      url: 'ajax/ticketsearch',
	 		      data : {nation:$('#nation').val(), city:$('#city').val(), type:$('#type').val(), hname:$('#tname').val()},
	 		      dataType : 'json',
	 		      success: function(data){
	 		    	  $('.totbest').css('display','none');
	 		    	  $('.totsearch').css('display','block');
	 		    	  $('.result').html(data);
	 		      },
	 		      error:
	 		         function(error){
	 		         console.log(error);
	 		      }
	 		});//ajax End
		}
	});
	
	function reset(obj){
		location.href="ticket";
	}
</script>
</html>