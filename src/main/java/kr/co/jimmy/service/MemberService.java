package kr.co.jimmy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.MemberDAO;
import kr.co.jimmy.VO.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public int join(MemberVO vo) {
		return dao.insert(vo);
	}
	
	public MemberVO login(String email, String password) {
		return dao.getMember(email, password);
	}
	
	public int modify(String no,MemberVO vo) {
		return dao.updateMember(no, vo);
	}
	
	public boolean memberCheck(String email) {
		MemberVO vo = dao.select(email);
		
		if(vo != null)
			return false; // 이미 회원 존재
		else
			return true; // 회원 없음
	}
}
