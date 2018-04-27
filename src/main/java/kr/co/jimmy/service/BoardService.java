package kr.co.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BoardDAO;
import kr.co.jimmy.VO.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;

	public List<BoardVO> list() {
		return dao.selectAll();
	}

	public BoardVO selectChoose(String no) {
		dao.updateHit(no);
		return dao.selectChoose(no);
	}

	public int insertBoard(String title, String content, String user_no) {
		return dao.insert(title, content, user_no);
	}

	public int updateBoard(String title, String content, String no) {
		return dao.update(title, content, no);
	}

	public int deleteBoard(String no) {
		return dao.delete(no);
	}
}
