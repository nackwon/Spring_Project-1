package kr.co.jimmy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jimmy.VO.BoardVO;
import kr.co.jimmy.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	String path = "/spring_mysite/";

	// board List
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardform(Model model) {
		ArrayList<BoardVO> list = (ArrayList<BoardVO>) boardService.list();
		model.addAttribute("boardList", list);
		return "board/list";
	}

	// board view
	@RequestMapping(value = "/boardViewform", method = RequestMethod.GET)
	public String boardviewform(Model model, @RequestParam("no") String no) {
		BoardVO vo = boardService.selectChoose(no);
		System.out.println(vo.toString());
		model.addAttribute("updateVo", vo);
		return "board/view";
	}

	// board write form
	@RequestMapping(value = "/boardWriteform", method = RequestMethod.GET)
	public String boardwriteform() {
		return "board/write";
	}

	// board write
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public String boardWrite(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("no") String user_no) {
		boardService.insertBoard(title, content, user_no);
		return "board/list";
	}

	// board modify form
	@RequestMapping(value = "boardModifyform", method = {RequestMethod.GET,RequestMethod.POST})
	public String boardmodifyform(Model model, @RequestParam("no") String no) {
		BoardVO vo = boardService.selectChoose(no);
		model.addAttribute("modifyVo",vo);
		return "board/modify";
	}

	// board modify
	@RequestMapping(value = "boardModify", method = RequestMethod.POST)
	public String boardmodify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("no") String no) {
		boardService.updateBoard(title, content, no);
		return "redirect: "+path+"board/boardList";
	}

	// board delete
	@RequestMapping(value = "boardDelete", method = RequestMethod.GET)
	public String boarddelete(@RequestParam("no") String no) {
		boardService.deleteBoard(no);
		return "redirect: "+path+"board/boardList";
	}
}
