package kr.co.jimmy.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public void selectAll() {
		
	}
}
