package kr.co.jimmy.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jimmy.VO.GuestVO;
import kr.co.jimmy.service.GuestService;

// ajax로 통신하는 놈을 위한 controller
@Controller
@RequestMapping("/guest/gb")
public class ApiGuestBookController {
	
	@Autowired
	private GuestService guestService;
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public ArrayList<GuestVO> list() {
		ArrayList<GuestVO> list = (ArrayList<GuestVO>)guestService.list();
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public GuestVO add(@ModelAttribute GuestVO vo) {
		return guestService.write(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public boolean del(@RequestParam("no") int no, @RequestParam("password") String password) {
		return guestService.ajaxdel(no, password);
	}
	
	@ResponseBody
	@RequestMapping(value="/scroll", method=RequestMethod.GET)
	public ArrayList<GuestVO> list_scroll(@RequestParam("start") int start, @RequestParam("end") int end){
		ArrayList<GuestVO> list = (ArrayList<GuestVO>)guestService.list(start, end);
		return list;
	}
}
