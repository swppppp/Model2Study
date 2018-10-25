package kr.or.kosta.shoppingmall.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 사용자 로그인 여부 체크 필터
 */
public class LoginCheckFilter implements Filter {
	
//	private String loginPage = "/user/login.jsp"; //로그인 안했을 경우 이동할 page
	private String loginPage;

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	loginPage = filterConfig.getInitParameter("loginPage");
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	System.out.println("[디버깅] : LoginCheckFiler 실행..");
		boolean isLogin = false;
		Cookie[] cookies = ((HttpServletRequest)request).getCookies(); //그냥servletrequest는 쿠키안돼 형변환 필요
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("loginId")) {
					isLogin = true;
					break;
				}
			}
		}
		
		if(isLogin) {
			chain.doFilter(request, response);
		}else {
			//로그인 안했을 경우..
			//로그인 페이지가 없는경우 예외 발생
			if(loginPage == null) {
				throw new ServletException("LoginCheckFilter에 loginPage가 설정되어 있지 않습니다.");
			}
			request.setAttribute("uri", ((HttpServletRequest)request).getRequestURI()); //페이지uri를 요청에 등록해서 포워드함..(보여주려고)
			request.getServletContext().getRequestDispatcher(loginPage).forward(request, response); //로그인페이지로 forward
		}
	}
    
    @Override
	public void destroy() {}

}
