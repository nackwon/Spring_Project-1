package kr.co.jimmy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jimmy.VO.GuestVO;
import kr.co.jimmy.service.GuestService;

@Controller
@RequestMapping(value="/guest")
public class GuestBookController {
	
	@Autowired
	private GuestService guestService;
	String path = "/spring_mysite/";
	
	// Guest Write form
	@RequestMapping(value="/guestform", method=RequestMethod.GET)
	public String guestWriteform(Model model) {
		System.out.println("guestform");
		ArrayList<GuestVO> list = (ArrayList<GuestVO>)guestService.list();
		model.addAttribute("userList", list);
		return "guestbook/list";
	}
	
	// Guest Write
	@RequestMapping(value="/guestwrite", method=RequestMethod.POST)
	public String guestWrite(@RequestParam("name")String name, @RequestParam("password")String password, @RequestParam("content")String content) {
		System.out.println("guestwrite");
		guestService.write(name, password, content);
		return "redirect: "+path+"guest/guestform";
	}
	
	// Guest Delete form
	@RequestMapping(value="/guestdeleteform", method=RequestMethod.GET)
	public String guestDeleteform() {
		return "guestbook/deleteform";
	}
	
	// Guest Delete
	@RequestMapping(value="/guestdelete", method=RequestMethod.POST)
	public String guestDelete(@RequestParam("no")String no, @RequestParam("password")String password) {
		guestService.delte(no, password);
		return "redirect: "+path+"guest/guestform";
	}
}
