<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="resources/js/jquery-3.2.1.min.js"></script>
<style>

</style>
</head>
<body>
    <fieldset>
        <legend class="screen_out">아이디 찾기 폼</legend>
 
        <div class="box name">
            <label for="name">이름</label>
            <input type="text" id="name" name="m_name" autofocus autocomplete="off" required />
        </div>
        <!-- // .box.name -->
        <div class="box email">
            <label for="email">이메일</label>
            <input type="text" id="email1" name="m_email" autofocus autocomplete="off" required />
        </div>
        <!-- // .box.email -->
        <div class="box btn">
            <button id="findidbtn" class="btn join">
                <i class="fa fa-envelope"></i>
       	     아이디 찾기
            </button>
        </div>
 		<div><span id="resultid"></span></div>
        <!-- // .box.btn -->
    </fieldset>
	
	<form id="findpwForm" name="findpwForm" method="post">
    <fieldset>
        <legend class="screen_out">비밀번호 찾기 폼</legend>
 
        <div class="box id">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" autofocus autocomplete="off" required />
        </div>
        <!-- // .box.id -->
        <div class="box email">
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" autofocus autocomplete="off" required />
        </div>
        <!-- // .box.email -->
 
        <div class="box btn">
            <input type="button" id="findpwbtn" class="btn join" value="비밀번호 찾기">
                <i class="fa fa-envelope"></i>
        </div>
        <div><span id="resultpw"></span></div>
        <!-- // .box.btn -->
    </fieldset>
	</form>
</body>
<script>
	$('#findidbtn').click(function(){  //아이디 찾기
		   if($('#name').val()==""||$('#email1').val()==""){
			   alert("정보를 입력해주세요.");
		   }else{
			$.ajax({
			      type: 'post',
			      url: 'ajax/findid',
			      data : {m_name:$('#name').val(), m_email:$('#email1').val()},
			      dataType : 'json',
			      success: function(data){
			    	  $('#resultid').html(data);
			      },
			      error:
			         function(error){
			         console.log(error);
			      }
			   });//ajax End
		   }
	});

	$('#findpwbtn').click(function(){  //비번 찾기
		   if($('#id').val()==""||$('#email').val()==""){
			   alert("정보를 입력해주세요.");
		   }else{
			$.ajax({
			      type: 'post',
			      url: 'ajax/findpw',
			      data : {m_id:$('#id').val(), m_email:$('#email').val()},
			      dataType : 'json',
			      success: function(data){
			    	  if(data==1){
			    		  document.findpwForm.action="sendMail/pw";
			    		  document.findpwForm.submit();
			    		  alert("이메일로 임시비밀번호가 전송되었습니다.");
			     	 }else{
			     		alert("아이디 또는 이메일이 잘못되었습니다.");
			     	 }
			      },
			      error:
			         function(error){
			         console.log(error);
			      }
			   });//ajax End
		   }
	});
</script>
</html>