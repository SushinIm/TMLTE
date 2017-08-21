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
		<form>
			<div>
				<ul>
					<li>예약자 성명 : <input type="text" name="m_name" value="${mbInfo.m_name}" /></li>
					<li>예약자 연락처 : <input type="text" name="m_phone" value="${mbInfo.m_phone}" /></li>
					<li>예약자 이메일 : <input type="text" name="m_email" value="${mbInfo.m_email}" /></li>
					<li>예약자 생년월일 : <input type="text" name="m_birth" value="${mbInfo.m_birth}" /></li>
				</ul>
			</div>
			<div>
				<input type="button" value="탑승객 등록" onclick="popUpAddFrm()"/>
			</div>
			<div id="seats">
				<input type="button" value="좌석 추가" onclick="formAdd()"/>
				<ul class="seatFrm">
					<li>
						등급 : <select>
							<option>퍼스트</option>
							<option>비즈니스</option>
							<option>이코노미</option>
						</select>
					</li>
					<li>
						좌석 열 : <select>
							<option>등급을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>	
						좌석 행 : <select>
							<option>좌석 행을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>
						<input type="button" value="탑승객 검색" onclick="viewList()"/>
					</li>
					<li>
						<input type="button" value="삭제" onclick="formRemove(this)"/>	
					</li>
				</ul>
			</div>
		</form>
	</div>
	<div id="footer">
	</div>
</body>
<div id="bgbox">
    <div id="ctbox">
        asdasd
    </div>
</div>
<script type="text/javascript">
    var cnt = 1;
    
    function formAdd(){
        $('.seatFrm').clone().appendTo('#seats');
        $('div#seats > ul').removeAttr('class');
        $('div#seats > ul:first').attr('class', 'seatFrm');
        console.log(++cnt);
    }
    
    function formRemove(obj){
        if(cnt<=1){
            alert('좌석이 한개 이하일 경우 폼 삭제가 불가능합니다.');
            console.log(cnt);
        }else{
            $(obj).parent().parent().remove();
            console.log(--cnt);
        }
    }

    function popUpAddFrm(){
        var popUrl = 'addpassenger.jsp';	//팝업창에 출력될 페이지 URL
        var popOption = 'width=800, height=450, resizable=no, scrollbars=auto, status=no, toolbar=no, menubar=no, directories=no;';
        window.open(popUrl,'탑승자 등록 페이지',popOption);
    }
    
    function viewList(){
        $('#bgbox').css('display', 'block');
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
    
    function examInsert(){
    	
    }
</script>
</html>