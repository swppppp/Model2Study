package kr.or.kosta.shoppingmall.user.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.common.service.ServiceFactory;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImple;

/**
 *(/)hello.mall 요청에 대한 처리 클래스
 * @author 박시원
 *
 */
public class UserListController implements Controller {

	private UserService userService; // 인터페이스
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		ModelAndView mav = new ModelAndView();
		
		//userService객체를 얻기위해 service팩토리 얻어오기
		ServiceFactory factory = (ServiceFactory) request.getServletContext().getAttribute("serviceFactory");
		userService = (UserService) factory.getService(UserServiceImple.class);
		System.out.println("----------"+userService);
		
		List<User> list = null;
		try {
			list = userService.list();
		} catch (Exception e) {
			throw new ServletException("UserService.list() 예외 발생", e);
		}
		
		mav.addObject("list", list);
		mav.setView("/user/list.jsp");
		
		return mav;
	}

}
