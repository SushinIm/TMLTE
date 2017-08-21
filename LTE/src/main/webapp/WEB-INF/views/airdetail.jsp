<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
</head>
    <style>
        div#indexFrm div#index ul{
            list-style: none;
            display: inline-table;
            text-align: center;
            width: 100%;
        }
        
        div#indexFrm div#index ul li{
            float:left;
            width: 10%;
        }
    </style>
<body>
	<div></div>
	<div>
        <p>도착지</p>
        <p>가격</p>
        <p>출발 시간</p>
        <p>도착 시간</p>
    </div>
	<div id="indexFrm">
		<div id="index">
			<ul id="menues">
				<li>항공기종</li>
				<li>출발지</li>
				<li>도착지</li>
				<li>출발일시</li>
				<li>도착일시</li>
				<li>경유여부</li>
				<li>잔여 좌석 수</li>
                <li>예약하기</li>
            </ul>
			<form action="airReserve" method="get">
                <input type="hidden" name="prodnum" value="ap1"/>
				<ul>
					<li>A503 <input type="hidden" name="type" value="A503"/></li>
					<li>인천 국제공항<input type="hidden" name="from" value="인천 국제공항"/></li>
					<li>도쿄 국제공항<input type="hidden" name="end" value="일본-도쿄-국제공항"/></li>
					<li>2017-09-02/22:15 <input type="hidden" name="stime" value="2017-09-02/22:15"/></li>
					<li>2017-09-03/00:30 <input type="hidden" name="etime" value="2017-09-03/00:30"/></li>
					<li>1회 <input type="hidden" name="watp" value="1"/></li>
					<li>97석 <input type="hidden" name="seats" value="97"/></li>
                    <li><input type="submit" value="예약하기"/></li>
				</ul>
			</form>
            <form action="airReserve" method="get">
                <input type="hidden" name="prodnum" value="ap2"/>
                <ul>
					<li>B503 <input type="hidden" name="type" value="A503"/></li>
					<li>인천 국제공항 <input type="hidden" name="from" value="인천 국제공항"/></li>
					<li>중국 베이징 <input type="hidden" name="to" value="일본 삿포로"/></li>
					<li>2017-09-15/18:15 <input type="hidden" name="stime" value="2017-09-02/22:15"/></li>
					<li>2017-09-15/22:30 <input type="hidden" name="etime" value="2017-09-03/00:30"/></li>
					<li>0회<input type="hidden" name="watp" value="0"/></li>
					<li>68석 <input type="hidden" name="seats" value="68"/></li>
                    <li><input type="submit" value="예약하기"/></li>
				</ul>
            </form>
		</div>
	</div>
	<div></div>
</body>
</html>