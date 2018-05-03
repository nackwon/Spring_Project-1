package kr.co.jimmy.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.GuestVO;

@Repository
public class GuestDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestVO> select(){
		return sqlSession.selectList("guest.selectByguest");
	}

	public int insert(String name, String password, String content) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		map.put("content", content);

		return sqlSession.insert("guest.insertByguest", map);
	}

	public int delete(String no, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("no", no);
		map.put("password", password);
		return sqlSession.delete("guest.deleteByguest", map);
	}
	
	// ajax
	public int insert(GuestVO vo) {	
		return sqlSession.insert("guest.insertByAjaxguest", vo);
	}
	
	// ajax 
	public GuestVO select(int no) {
		return sqlSession.selectOne("guest.selectByAjaxguest",no);
	}
	
	// ajax
	public int ajaxDelete(int no, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		return sqlSession.delete("guest.deleteByguest", map);
	}
	
	public List<GuestVO> select1(int start, int end){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("end", end);
		map.put("start",start);
		return sqlSession.selectList("guest.selectByguestScroll",map);
	}
}
