package kr.co.jimmy.DAO;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// insert
	public int insert(MemberVO vo) {
		return sqlSession.insert("member.insertMember",vo);
	}
	
	// getMember
	public MemberVO getMember(String email, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("member.selectMemberByEmailPassword", map);
	}
	
	// updateMemer
	public int updateMember(String no, MemberVO vo) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", vo.getName());
		map.put("password", vo.getPassword());
		map.put("gender", vo.getGender());
		map.put("no", no);
		
		return sqlSession.update("member.updateMemberByemailPasswordgender",map);
	}
	
	public MemberVO select(String email) {
		return sqlSession.selectOne("member.selectMemberByPossible",email);
	}
}
