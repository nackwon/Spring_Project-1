package kr.co.jimmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	// Main Page
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		System.out.println("Main Page");
		return "main/index";
	}	
	
}
