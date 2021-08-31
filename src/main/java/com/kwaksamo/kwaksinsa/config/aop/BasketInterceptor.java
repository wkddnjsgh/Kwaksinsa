package com.kwaksamo.kwaksinsa.config.aop;

import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class BasketInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		LoginUser principal = (LoginUser)session.getAttribute("loginUser");
		
		if(principal.getAddress().equals("입력필요")) {
			PrintWriter out =  response.getWriter();
			out.print("<script>");
			out.print("alert('주소 입력이 필요합니다.');");
			out.print("location.href='/user/update';");
			out.print("</script>");
			return false;
		}
		return true;
	}
	
}
