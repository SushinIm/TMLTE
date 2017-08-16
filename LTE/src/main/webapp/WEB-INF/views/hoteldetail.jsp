<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.8.3.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div id="prodimg"></div>
	<div id="prodinfo">
		<p>숙박지 명(한글)EL</p>
		<p>숙박지 위치EL</p>
		<p>주소EL</p>
		<p>연락처EL</p>
	</div>
	<div id="reserveplan">
		<form method="get" id="booking">
			<input type="text" id="checkIn" name="checkIn" readonly="readonly"
				placeholder="체크인 날짜"> ~ <input type="text" id="checkOut"
				name="checkOut" readonly="readonly" placeholder="체크아웃 날짜"> <select
				name="rooms">
				<option value="0">객실</option>
				<option value="1">1인실</option>
				<option value="2">2인실</option>
				<option value="3">3인실</option>
				<option value="4">4인실</option>
				<option value="5">5인 이상</option>
			</select> 
			<input type="hidden" value="숙박지명EL"/>
			<input type="hidden" value="영어 숙박지 명 EL" />
			<input type="button" value="방 검색" onclick="Aj()" />
		</form>
	</div>
	<div id="reservereal"></div>
</body>
<div></div>
<script type="text/javascript">
 $(function() { 
    $("#checkIn").datepicker({
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일','월','화','수','목','금','토'],
        monthNamesShort: [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ],
        changeMonth : true,
        changeYear : true,
        showOtherMonths : true,
        beforeShowDay:noBefore
    });
 
    $("#checkOut").datepicker({
	   dateFormat: 'yy-mm-dd',
	   dayNamesMin: ['일','월','화','수','목','금','토'],
	   monthNamesShort: [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ],
	   changeMonth : true,
	   changeYear : true,
	   showOtherMonths : true,
	   beforeShowDay: noCheckIn
    });

	function noBefore(date){
	    if (date < new Date())
	        return [false];
	    return [true];
	}
    
    function noCheckIn(date){
        var today = date;
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear(); 
        var strArray = ($('#checkIn').val()).split('-');
        var dates = 1*(strArray[0]+strArray[1]+strArray[2]);
        if(mm<10){
            var tdates = yyyy+'0'+mm+''+dd    
        }
        else if(dd<10){
            var tdates = yyyy+''+mm+'0'+dd   
        }
        else{
            var tdates = yyyy+''+mm+''+dd   
        }
        if(tdates*1 < dates+1)
            return [false];
        return [true];
    }
    
    function Aj(){
    	var params = $('#booking').serialize();
    	$.ajax({
	 		
    		type : "post",
	 		url : "hotelBooking",
	 		data : params,
	 		success : function(data){
	 			$('#reservereal').html('${lrTable}');
	 		},
	 		error : function(error){
	 			alert(error+'발생');
	 		}
		});
    }
 });
</script>
</html>