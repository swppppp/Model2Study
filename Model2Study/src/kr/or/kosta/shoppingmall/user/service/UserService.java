package kr.or.kosta.shoppingmall.user.service;
/**
 * 고객 요구사항을 반영한 도메인(개발하고자 하는 업무영역)별 비즈니스 메소드 선언
 * 복잡한 트랜잭션처리, 예외처리 등
 * 기술에 종속적이지 않음..
 * @author 박시원
 */

import java.util.List;

import kr.or.kosta.shoppingmall.user.domain.User;

public interface UserService {
	/** 회원 검색 */
	public User search(String id) throws Exception;
	
	/** 회원 목록 */
	public List<User> list() throws Exception;
}
