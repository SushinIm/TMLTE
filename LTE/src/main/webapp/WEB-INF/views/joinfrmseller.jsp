<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="resources/bootstrap/font-awesome.css" />
<script src="resources/js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<style>

</style>
</head>
<body>
<h1>회원정보입력(판매자)</h1>
<form name="joinsellerfrm" method="post">
   <table width="940" style="padding: 5px 0 5px p; ">
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <th>아이디 *</th>
         <td>
            <input type="text" id="id" name="m_id" placeholder="ID">
            <input type="hidden" id="part" value="2"/>
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
            <input type="text" id="phone1" name="m_phone1"> -
            <input type="text" id="phone2" name="m_phone2"> -
            <input type="text" id="phone3" name="m_phone3">
         </td>
      </tr>
      <tr>
         <th>판매자 구분 *</th>
         <td><input type="radio" name="m_radio" value="숙박" checked> 숙박
            <input type="radio" name="m_radio" value="레저/입장권"> 레저/입장권
        </td>
      </tr>
      <tr>
         <th>사업자번호 *</th>
         <td><input type="text" id="snum" name="m_bnum"></td>
      </tr>
      <tr>
         <th>상호명 *</th>
         <td><input type="text" id="name" name="m_name" placeholder="NAME"></td>
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
 		}else if($('#pwOri').val()==""){
			alert("비밀번호를 입력해주세요.");
			$('#pwOri').focus();
	    }else if($('#email1').val()==""||$('#email2').val()==""){
		   	alert("이메일을 입력해주세요.");
			$('#email1').focus();
	    }else if($('#phone1').val()==""||$('#phone2').val()==""||$('#phone3').val()==""){
			alert("전화번호를 입력해주세요.");
			$('#phone1').focus();
	    }else if($('#snum').val()==""){
		 	alert("사업자명을 입력해주세요.");
			$('#snum').focus();
	    }else if($('#name').val()==""){
		 	alert("상호명을 입력해주세요.");
			$('#name').focus();
	    }else{
		   	document.joinsellerfrm.action='sellerInsert';
	      	document.joinsellerfrm.submit();
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

   function openCheck(chk){
      var obj=document.getElementsByName("숙박");
      for(var i=0; i<obj.length; i++){
         if(obj[i]!=chk){
            obj[i].checked=false;
         }
      }
   }
   
   function main(obj){ //메인으로
       location.href="./";
	}

</script>
</body>
</html>