package kr.co.jimmy.DAO;

import java.util.ArrayList;
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
}
