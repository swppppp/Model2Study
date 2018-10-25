package kr.or.kosta.shoppingmall.common.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View 선택 및 실행
 * @author 김기정
 *
 */
public class ViewResolver{
	
	public View resolve(String path) throws ServletException{
		//path에 해당하는 기술..(path는 같고 path에 있는 내용은 달라요기술별로)
		View view = new JSPView(path);
//		View view = new XXXView(path);
		return view;
	}
}
