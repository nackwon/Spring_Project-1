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
}
