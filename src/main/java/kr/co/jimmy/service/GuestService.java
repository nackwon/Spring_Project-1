package kr.co.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.GuestDAO;
import kr.co.jimmy.VO.GuestVO;

@Service
public class GuestService {

	@Autowired
	private GuestDAO dao;

	public List<GuestVO> list() {
		return dao.select();
	}

	public int write(String name, String password, String content) {
		return dao.insert(name, password, content);
	}

	public int delte(String no, String password) {
		return dao.delete(no, password);
	}

	// ajax
	public GuestVO write(GuestVO vo) {
			dao.insert(vo);
		return dao.select(vo.getNo());
	}

	// ajax
	public boolean ajaxdel(int no, String password) {
		boolean flag = false;
		int number = dao.ajaxDelete(no, password);
		if (number == 1) {
			flag = true; // 삭제 완료
		} else {
			flag = false; // 노 삭제
		}
		return flag;
	}

	public List<GuestVO> list(int start, int end) {
		return dao.select1(start, end);
	}
}
