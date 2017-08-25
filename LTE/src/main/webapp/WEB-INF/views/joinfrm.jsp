<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style_mj.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<title>Insert title here</title>
<style>
	#all{
		width: 100%; 
		height: 50%;
		padding: 5px 0 5px p; 
		margin:auto;
	}
</style>
</head>
<body> 
<jsp:include page="header.jsp"/>
<div class="content-area">
<div class="container"><br><br>
<h4>회원가입(일반)</h4><br><br><br>

<form name="joinnormalfrm" method="post">
   <table id="all">
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <th>아이디 *</th>
         <td>
            <input type="text" id="id" name="m_id" placeholder="ID">
            <input type="hidden" id="part" value="1"/>
            <input type="hidden" id="clone"/>
            <input type="hidden" id="val"/>
            <input type="button" id="idcheckbtn" onclick="idcheck()" value="중복체크 "><span id="idcheck"></span>
         </td>
      </tr>
      <tr>
         <th>비밀번호 *</th>
         <td>
            <input type="password" id="pwOri" name="m_pw" placeholder="PASSWORD">
         </td>
      </tr>
      <tr>
         <th>비밀번호 확인 *</th>
         <td>
            <input type="password" id="pwCheck" name="m_pw2" placeholder="PASSWORD" onchange="isSame()"/>&nbsp;&nbsp;<span id="same"></span>
         </td>
      </tr>
      <tr>
         <th>이름 *</th>
         <td>
            <input type="text" id="name" name="m_name" placeholder="NAME">
         </td>
      </tr>
      <tr>
         <th>성별 *</th>
         <td class="s">
            <input type="radio" name="m_gender" value="M" checked> 남
            <input type="radio" name="m_gender" value="W"> 여
         </td>
      </tr>
      <tr>
         <th>이메일 *</th>
         <td>
            <input type="text" id="email1" name="m_email1" >@
            <input type="text" id="email2" name="m_email2" readonly>
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
         <th>전화번호 *</th>
         <td>
            <input type="text" id="phone1" name="m_phone1" readonly="readonly"  style="background-color:lightgray" value="010"> -
            <input type="text" id="phone2" name="m_phone2"> -
            <input type="text" id="phone3" name="m_phone3">
         </td>
      </tr>
      <tr>
         <th>생년월일 *</th>
         <td><select name="m_birth1">
         <!-- <td><input type="date" name="m_birth"></td> -->
       <option value="2016">2016</option>
       <option value="2015">2015</option>
       <option value="2014">2014</option>
       <option value="2013">2013</option>
       <option value="2012">2012</option>
       <option value="2011">2011</option>
       <option value="2010">2010</option>
       <option value="2009">2009</option>
       <option value="2008">2008</option>
       <option value="2007">2007</option>
       <option value="2006">2006</option>
       <option value="2005">2005</option>
       <option value="2004">2004</option>
       <option value="2003">2003</option>
       <option value="2002">2002</option>
       <option value="2001">2001</option>
       <option value="2000">2000</option>
       <option value="1999">1999</option>
       <option value="1998">1998</option>
       <option value="1997">1997</option>
       <option value="1996">1996</option>
       <option value="1995">1995</option>
       <option value="1994">1994</option>
       <option value="1993">1993</option>
       <option value="1992">1992</option>
       <option value="1991">1991</option>
       <option value="1990">1990</option>
       <option value="1989">1989</option>
       <option value="1988">1988</option>
       <option value="1987">1987</option>
       <option value="1986">1986</option>
       <option value="1985">1985</option>
       <option value="1984">1984</option>
       <option value="1983">1983</option>
       <option value="1982">1982</option>
       <option value="1981">1981</option>
       <option value="1980">1980</option>
           
       
     </select>년&nbsp;
     <select name="m_birth2">
       <option value="01">01</option>
       <option value="02">02</option>
       <option value="03">03</option>
       <option value="04">04</option>
       <option value="05">05</option>
       <option value="06">06</option>
       <option value="07">07</option>
       <option value="08">08</option>
       <option value="09">09</option>
       <option value="10">10</option>
       <option value="11">11</option>
       <option value="12">12</option>
       
     </select>월&nbsp;
     <select name="m_birth3">
       <option value="01">01</option>
       <option value="02">02</option>
       <option value="03">03</option>
       <option value="04">04</option>
       <option value="05">05</option>
       <option value="06">06</option>
       <option value="07">07</option>
       <option value="08">08</option>
       <option value="09">09</option>
       <option value="10">10</option>
       <option value="11">11</option>
       <option value="12">12</option>
       <option value="13">13</option>
       <option value="14">14</option>
       <option value="15">15</option>
       <option value="16">16</option>
       <option value="17">17</option>
       <option value="18">18</option>
       <option value="19">19</option>
       <option value="20">20</option>
       <option value="21">21</option>
       <option value="22">22</option>
       <option value="23">23</option>
       <option value="24">24</option>
       <option value="25">25</option>
       <option value="26">26</option>
       <option value="27">27</option>
       <option value="28">28</option>
       <option value="29">29</option>
       <option value="30">30</option>
       <option value="31">31</option>
     </select>일</td>
      </tr>
      
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <td colspan="2" align="center">
            <input type="button" id="sm" value="회원가입하기">
            <input type="reset" value="홈으로" onclick="main(this)">
         </td>
      </tr>
   </table>
</form>
<br><br><br>
</div>
</div>
</body>

<script>
   function idcheck(){  /* //아이디 중복체크 */
	   if($('#id').val()==""||$('#id').val()==null){
		   alert("아이디를 입력해주세요.");
	   }else{
   		$.ajax({
   		      type: 'post',
   		      url: 'ajax/idcheck',
   		      data : {id:$('#id').val(), part:$('#part').val()},
   		      dataType : 'json',
   		      success: function(data){
   		         if(data==1){
   		        	$('#idcheck').html(" 이미 존재합니다.");
   		        	$('#idcheck').css('color','red')
   		         }else{
   		        	$('#idcheck').html(" 쓸 수 있는 아이디입니다.");
   		        	$('#idcheck').css('color','blue') 
   		         }
   		        	$('#val').val(data)
   		        	$('#clone').val($('#id').val());
   		      },
   		      error:
   		         function(error){
   		         console.log(error);
   		      }
   		   });//ajax End
	   }
   }
   
	$(function(){ 
		//이메일 select
		$('#selectEmail').change(function(){               
		   if($('#selectEmail').val() == "1"){
		    	$("#email2").val(""); //값 초기화
		    	$("#email2").prop("readonly",false); //활성화
		    } else if($('#selectEmail').val() == ""){
		    	$("#email2").val(""); //값 초기화
		    	$("#email2").prop("readonly",true); //textBox 비활성화
		    } else {
		    	$("#email2").val($('#selectEmail').val()); //선택값 입력
		    	$("#email2").prop("readonly",true); //비활성화
		   }
		});
	 });
	
    $('#sm').click(function(){
 	   if($('#id').val()==""){
			alert("아이디를 입력해주세요.");
			$('#id').focus();
	   	}else if($('#idcheck').html()==""){
		    alert("중복체크를 해주세요.");
			$('#idcheck').focus();
		}else if($('#val').val()==1){
			alert("아이디를 변경해주세요.");
			$('#id').focus();
  		}else if($('#id').val()!=$('#clone').val()){
	    	alert("중복체크를 해주세요.");
			$('#idcheck').focus();
    	}else if($('#pw').val()==""){
			alert("비밀번호를 입력해주세요.");
			$('#pw').focus();
	    }else if($('#name').val()==""){
		 	alert("이름을 입력해주세요.");
			$('#name').focus();
	    }else if($('#email1').val()==""||$('#email2').val()==""){
		   	alert("이메일을 입력해주세요.");
			$('#email1').focus();
	    }else if($('#phone1').val()==""||$('#phone2').val()==""||$('#phone3').val()==""){
			alert("전화번호를 입력해주세요.");
			$('#phone1').focus();
	    }else{
		   	document.joinnormalfrm.action='memberInsert';
	      	document.joinnormalfrm.submit();
	   }
   })
   
  
    //비밀번호 체크
   function isSame(){
      var pw1 = document.getElementById('pwOri').value
      var pw2 = document.getElementById('pwCheck').value
      var pwSame = document.getElementById('same')
      if(pw1!='' && pw2!=''){
         if(pw1==pw2){
            pwSame.innerHTML = '비밀번호가 일치합니다.';
            pwSame.style.color='blue';
         }else{
            pwSame.innerHTML = '비밀번호가 일치하지 않습니다.';
            pwSame.style.color='red';
         }
      }
   }
    
   function main(obj){ //메인으로
       location.href="./";
	}

</script>
</html>
<jsp:include page="footer.jsp" />