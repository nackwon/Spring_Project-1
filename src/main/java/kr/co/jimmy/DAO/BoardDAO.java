package kr.co.jimmy.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.BoardVO;

@Repository
public class BoardDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVO> selectAll() {
		return sqlSession.selectList("board.selectByBoardList");
	}
	
	public int updateHit(String no) {
		return sqlSession.update("board.updateByBoardHit",no);
	}
	
	public BoardVO selectChoose(String no) {
		return sqlSession.selectOne("board.selectByBoardDetail", no);
	}
	
	public int insert(String title, String content, String user_no) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("content", content);
		map.put("user_no", user_no);
		return sqlSession.insert("board.insertByBoard", map);
	}
	
	public int update(String title, String content, String no) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("content", content);
		map.put("no", no);
		return sqlSession.update("board.updateByBoard", map);
	}
	
	public int delete(String no) {
		return sqlSession.delete("board.deleteByBoardDelete", no);
	}
}