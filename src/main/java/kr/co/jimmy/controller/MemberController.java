package kr.co.jimmy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jimmy.VO.MemberVO;
import kr.co.jimmy.service.MemberService;

@Controller
@RequestMapping(value = "/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	String path = "/spring_mysite";

	// join form
	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}

	// join
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute MemberVO vo) {
		memberService.join(vo);
		return "user/joinsuccess";
	}

	// join check(email check)
	@ResponseBody
	@RequestMapping(value = "/emailcheck", method = RequestMethod.POST)
	public boolean exists(@RequestParam("email") String email) {
		return memberService.memberCheck(email);
	}

	// login form
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}

	// login check (session)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		MemberVO authMember = memberService.login(email, password);

		if (authMember != null) {
			session.setAttribute("authUser", authMember);
			return "redirect: " + path + "/main";
		} else {
			return "redirect: " + path + "/user/loginform?result=fail";
		}
	}

	// logout (session invalidate)
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: " + path + "/main";
	}

	// modifyform
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform() {
		return "user/modifyform";
	}

	// modify
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, @RequestParam("no") String no, @ModelAttribute MemberVO vo) {
		if (session != null) {
			memberService.modify(no, vo);
			return "main/index";
		} else {
			return "redirect: " + path + "/loginform";
		}
	}
}
