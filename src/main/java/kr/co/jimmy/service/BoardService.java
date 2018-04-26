package kr.co.jimmy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BoardDAO;

@Service
public class BoardService {
	
	
	@Autowired
	private BoardDAO dao;
	
	public void list() {
		
	}
}
