package kr.co.jimmy.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.FileVO;

@Repository
public class FileUploadDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<FileVO> selectAll() {
		return sqlSession.selectList("gallary.selectByGallaryList");
	}
	
	public int insert(FileVO fileVo) {
		return sqlSession.insert("gallary.insertByGallary",fileVo);
	}
	
	public int delete(int no) {
		return sqlSession.delete("gallary.deleteByGallary",no);
	}
}
