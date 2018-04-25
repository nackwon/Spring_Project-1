package kr.co.jimmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jimmy.DAO.MemberDAO;

@Controller
public class MainController {
	
	@Autowired
	private MemberDAO dao;
	
	// Main Page
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		return "/WEB-INF/views/main/index.jsp";
	}
	
	// Register Page
	@RequestMapping(value="/registform", method=RequestMethod.GET)
	public String joinform() {
		return "/WEB-INF/views/user/joinform.jsp";
	}
	
	// Login Page
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		return "/WEB-INF/views/user/loginform.jsp";
	}
	
	// Modify Page
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform() {
		return "/WEB-INF/views/user/modifyform.jsp";
	}
	
	// logout Page
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "/WEB-INF/views/main/index.jsp";
	}
	
}
