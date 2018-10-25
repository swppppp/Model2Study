package kr.or.kosta.shoppingmall.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 요청파라메터(post방식) 한글인코딩 처리 필터
 */
public class CharacterEncodingFilter implements Filter {
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//web.xml에 등록된 파라미터값을 읽어들임..(config를 통해서)
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리
		if(encoding != null){
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
		// 후처리
	}

	@Override
	//반드시 오버라이디해주래용
	public void destroy() {	}

	

}
