<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.http.*" %>
<%@page import="java.util.*,java.text.*"%>
<%
java.util.Date currentDate = new java.util.Date();
java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
String Indate = format.format(currentDate);  //당일날짜
long lCurTime = currentDate.getTime();
currentDate = new java.util.Date(lCurTime+(1000*60*60*24*+1));
String Outdate = format.format(currentDate);  //당일다음날짜
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
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
<div>
		<c:if test="${login eq '숙박'}">
			<form name="prodForm">
				<input id="sellerProd" type="button" value="숙박상품등록"
					onclick="insertProdH()">
			</form>
		</c:if>
	</div>
	<div class="contents">
		<div>
			<table>
				<tr><td>국가</td><td><input type="select" id="nation" name="nation"/></td><td>&nbsp;&nbsp;</td><td>도시</td><td><input type="select" id="city" name="city"/></td></tr>
				<tr><td>체크인</td><td><input type="date" id="checkin" name=checkin min="<%=Indate%>" value="<%=Indate%>"/></td><td>&nbsp;&nbsp;</td><td>체크아웃</td><td><input type="date" id="checkout" name="checkout" min="<%=Outdate%>" value="<%=Outdate%>"/></td></tr>
				<tr><td>호텔명</td><td colspan="4"><input type="text" id="hname" name="hname"/></td></tr>
				<tr><td colspan="5" align="center" id="searchroom"><input type="button" id="search" value="검색하기"><input type="reset" id="reset" value="초기화" onclick="reset(this)")></td></tr>
			</table>
		</div>
		<div class="totbest">
			<div class="besttitle">추천호텔</div>
			${hotelbest}
		</div>
		<div class="totsearch">
			<div class="searchtitle">호텔검색 결과입니다.</div>
				<ul class="option">
					<li><a id="hotelname">호텔 이름순</a></li>
					<li><a id="hotelhigh">높은 가격순</a></li>
					<li><a id="hotellow">낮은 가격순</a></li>
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
	 		      url: 'ajax/hotelsearch',
	 		      data : {nation:$('#nation').val(), city:$('#city').val(), checkin:$('#checkin').val(), checkout:$('#checkout').val(), hname:$('#hname').val()},
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
	function insertProdH() {
		document.prodForm.action = "hotelinsertFrm";
		document.prodForm.submit();
	}
	
	function reset(obj){
		location.href="hotelbook";
	}
</script>
</html>