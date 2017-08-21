<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="resources/js/jquery-3.2.1.min.js"></script>
	<title>Insert title here</title>
<style>
</style>
</head>
<body>
<h1>판매자 정보수정 페이지</h1>
<form name="editinfosellerfrm" action="editinfosaveseller" method="post">
	<table width="700px"  height="300px" style="padding: 5px 0 5px 0; ">
		<tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
  		<tr>
	        <td>아이디 </td>
	        <td>
	           <input name="m_id" value="${sessionScope.id}" readonly="readonly" style="background-color:lightgray">
	        </td>
      	</tr>
      	<tr>
	        <td>이메일 *</td>
		    <td>
		    	<input type="text" name="m_email1" id="m_email1" value="${m_email1}">@
		    	<input type="text" name="m_email2" id="m_email2" value="${m_email2}">
		    	<select name="selectEmail" id="selectEmail">
	         		<option value="" selected>선택하기</option>
	                <option value="naver.com">naver.com</option>
	                <option value="daum.net">daum.net</option>
	                <option value="hanmail.net">hanmail.net</option>
	                <option value="gmail.com">gmail.com</option>
	                <option value="empal.com">empal.com</option>
	                <option value="msn.com">msn.com</option>
	                <option value="nate.com">nate.com</option>
	             	<option value="1">직접입력</option>	
              	</select>
		    </td>
      	</tr>
      	<tr>
	        <td>변경할 비밀번호 * </td>
	        <td>
	        	<input type="password" name="m_pw" id="pwOri" placeholder="PASSWORD" />
	        </td>
      	</tr>
      	<tr>
	        <td>비밀번호 확인 *</td>
	        <td>
	        	<input type="password" name="m_pw2" id="pwCheck" placeholder="PASSWORD" onchange="isSame()" /><br/><span id="same"></span>
	        </td>
      	</tr>
      	<tr>
	        <td>상호명 *</td>
		    <td>
		    	<input type="text" name="m_name" value="${member.m_name}">
		    </td>
      	</tr>
      	<tr>
	        <td>전화번호 *</td>
		    <td>
		    	<input type="text" name="m_phone1" id="m_phone1" value="${m_phone1}"> -
            	<input type="text" name="m_phone2" id="m_phone2" value="${m_phone2}"> -
            	<input type="text" name="m_phone3" id="m_phone3" value="${m_phone3}">
		    </td>
      	</tr>
      	<tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
	  	<tr>
	        <td colspan="2" align="center"><br/><br/>
	      	<input type="submit" value="수정">
	      	<input type="reset" value="다시작성">
	    	<input type="button" value="홈으로" onclick="main(this)">
	        </td>
	  	</tr>
	</table>
</form>
</body>
<script>
	function main(obj){
		document.editinfosellerfrm.action='main';
	    document.editinfosellerfrm.submit();
	}
	
	//이메일 select
	$(function(){ 
		$('#selectEmail').change(function(){               
		   if($('#selectEmail').val() == "1"){
		    	$("#m_email2").val(""); //값 초기화
		    	$("#m_email2").prop("readonly",false); //활성화
		    } else if($('#selectEmail').val() == ""){
		    	$("#m_email2").val(""); //값 초기화
		    	$("#m_email2").prop("readonly",true); //textBox 비활성화
		    } else {
		    	$("#m_email2").val($('#selectEmail').val()); //선택값 입력
		    	$("#m_email2").prop("readonly",true); //비활성화
		   }
		});
	 });
	
	//비밀번호 체크
	function isSame(){
		var pw1 = document.getElementById('pwOri').value
		var pw2 = document.getElementById('pwCheck').value
		var pwSame = document.getElementById('same')
		if(pw1!='' && pw2!=''){
			if(pw1==pw2){
				pwSame.innerHTML = '비밀번호 일치';
				pwSame.style.color='blue';
			}else{
				pwSame.innerHTML = '비밀번호 불일치';
				pwSame.style.color='red';
			}
		}
	} 
</script>
</html>