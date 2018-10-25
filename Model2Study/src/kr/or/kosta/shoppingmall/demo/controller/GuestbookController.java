package kr.or.kosta.shoppingmall.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 *(/)hello.mall 요청에 대한 처리 클래스
 * @author 박시원
 *
 */
public class GuestbookController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		ModelAndView mav = new ModelAndView();
		
		String message = "방명록 목록입니다.";
		
//		mav.addObject("message", message);
		request.getSession().setAttribute("message", message); //redirect해서 값 가져가려고 session에 저장
		
		//mav.setView("/demo/guestbook.jsp"); //포워드
		mav.setView("redirect:/model2/demo/guestbook.jsp"); //redirect.. 요청,응답객체 새로만들기때문에 set한 자료들이 다 날라감..->세션에 저장쓰
		return mav;
	}

}
