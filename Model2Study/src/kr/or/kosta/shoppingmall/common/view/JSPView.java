package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPView implements View {
	
	private String path;
	
	public JSPView(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp인 경우 dispatch해서 view를 보여주기때문에 이렇게 구현됨..
		// 다른 기술이면 그 기술이 실행되는 코드가 들어감!
		RequestDispatcher dispatcher = null;
		if (path == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// dispatch기술 두가지(redirect, forward)모두 실행가능하도록
		// 접두어 redirect들어있으면(구분자 ':')
		if(path.startsWith("redirect")){// redirect
			String[] tokens = path.split(":", 2);
			response.sendRedirect(tokens[1]);
		}else{//forward
			dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
	}

}
