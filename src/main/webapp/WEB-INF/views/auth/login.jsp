<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">

	<form action="/auth/loginProc" method="post">
	<br/>
		<input type="text" name="username" placeholder="Username" required>
	<br/>
		<input type="password" name="password" placeholder="Password" required>
	<br/>
		<input type="submit" value="로그인">
	</form>
	<hr/>
	
	<!-- 구글 로그인, 주소는 변경 불가-->
	<a href="/oauth2/authorization/google">
	<img src="http://localhost:8080/img/btn_google_signin_dark_normal_web.png"/>
	</a>
	<hr/>
	
	<!-- 페이스북 로그인, 주소는 변경 불가-->
	<a href="/oauth2/authorization/facebook">	
	<img src="http://localhost:8080/img/facebook.png"/>
	</a>

	<hr/>
	
	<!-- 네이버 로그인, 주소는 변경 불가-->
	<!-- YML에 적은 https://nid.naver.com/oauth2.0/authorize 실행 -->
	<a href="/oauth2/authorization/naver">
		<img src="http://localhost:8080/img/img_naverid05.png"/>
	</a>
	
	<hr/>
	
	<!-- YML에 적은 https://nid.naver.com/oauth2.0/authorize 실행 -->
	
	<a href="/oauth2/authorization/kakao">
		<img height="38px" src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png"/>
	</a>
	
	<hr/>
	
	<a href="/auth/join">회원가입 하러가기</a>
	
</div>
<%@include file="../layout/footer.jsp"%>