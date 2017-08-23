<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="header">
	</div>
	<div id="section">
		<form name="payAirFrm" action="payAir" method="get">
			<div>
				<ul>
					<li>예약자 성명 : <input type="text" name="m_name" value="${mbInfo.m_name}" /></li>
					<li>예약자 연락처 : <input type="text" name="m_phone" value="${mbInfo.m_phone}" /></li>
					<li>예약자 이메일 : <input type="text" name="m_email" value="${mbInfo.m_email}" /></li>
					<li>예약자 생년월일 : <input type="text" name="m_birth" value="${mbInfo.m_birth}" /></li>
				</ul>
			</div>
			<div>
				<input type="button" value="탑승객 등록" onclick="viewAddFrm()"/>
			</div>
			<div id="seats">
				<input type="button" value="좌석 추가" onclick="formAdd()"/>
				<ul class="seatFrm">
					<li>
						등급 : <select name="grade1" onchange="seatList(this)">
							<option value="퍼스트">퍼스트</option>
							<option value="비즈니스">비즈니스</option>
							<option value="이코노미">이코노미</option>
						</select>
					</li>
					<li>
						좌석 열 : <select name="row1">
							<option>등급을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>	
						좌석 행 : <select name="col1">
							<option>좌석 행을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>
						<input type="button" value="탑승객 검색" onclick="viewList()"/>
					</li>
					<li>
						<input type="button" class="delBtn" value="삭제" onclick="formRemove(this)"/>	
					</li>
				</ul>
			</div>
			<input type="button" value="예약 결제" id="adder" />
		</form>
	</div>
	<div id="footer">
	</div>
</body>
<div id="bgbox">
    <div id="ctbox">
    </div>
</div>
<script type="text/javascript">
    var cnt = 1;
    var del = '<li><input type="button" class="delBtn" value="삭제" onclick="formRemove(this)"/></li>';
    function formAdd(){
        console.log(++cnt);        
        $('div#seats > ul:last input.delBtn').parent().remove();
    	$('.seatFrm').clone().appendTo('#seats');
    	$('div#seats > ul').removeAttr('class');
   	 	$('div#seats > ul:first').attr('class', 'seatFrm');
   	 	$('div#seats > ul:last select[name=grade1]').attr('name', 'grade'+cnt);
	   	$('div#seats > ul:last input[name=row1]').attr('name', 'row'+cnt);
	   	$('div#seats > ul:last input[name=col1]').attr('name', 'col'+cnt);
   	 	$('div#seats > ul:last select[name=grade1]').attr('name', 'grade'+cnt);
   	 	$("div#seats > ul:last select[name=grade1] option:eq(0)").prop("selected", true);
	   	$('div#seats > ul:last input[name=row1]').empty();
	   	$('div#seats > ul:last input[name=col1]').empty();
   	 	$('div#seats > ul:last').append(del);
    }
    
    function formRemove(obj){
        if(cnt<=1){
            alert('좌석이 한개 이하일 경우 폼 삭제가 불가능합니다.');
            console.log(cnt);
        }else{
            $(obj).parent().parent().remove();
    	    $('div#seats > ul:last').append(del);
            console.log(--cnt);
        }
    }

    function seatList(obj){
    	console.log(obj);
    	console.log($(obj).val());
    }
    
    function viewAddFrm(){
    	$('#bgbox').attr('class', 'open');
    	$.ajax({
            type:'get',
            url:'./toAddPass',
            success:function(data){
                $('#ctbox').html(data);
            },
            error:function(error){
                console.log(error);
            }
        });
    }
    
    function viewList(){
    	$('#bgbox').attr('class', 'open');
        $.ajax({
            type:'get',
            url:'./passSelect',
            success:function(data){
                $('#ctbox').html(data);
            },
            error:function(error){
                console.log(error);
            }
        });
    }

	$(function(){
		$('#adder').click(function(){
			var hidcnt = '<input type="hidden" name="cnt" value="'+cnt+'" />';
			$('div#seats').append(hidcnt);
			document.payAirFrm.submit();
		})
	})
</script>
</html>