package kr.co.jimmy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jimmy.VO.BoardVO;
import kr.co.jimmy.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	String path = "/spring_mysite/";
	
	// board List
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardform() {
		ArrayList<BoardVO> list;
		return "board/list";
	}
	
	// board view
	@RequestMapping(value="/boardViewform", method=RequestMethod.GET)
	public String boardviewform() {
		
		return "board/view";
	}
	
	// board write
	@RequestMapping(value="/boardWriteform", method=RequestMethod.GET)
	public String boardwriteform() {
		
		return "board/write";
	}
	
	// board modify
	@RequestMapping(value="boardModifyform", method=RequestMethod.GET)
	public String boardmodifyform() {
		
		return "board/modify";
	}
}
