package kr.co.jimmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.VO.FileVO;
import kr.co.jimmy.service.FileUploadService;

@Controller
@RequestMapping("/gallary")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	String path = "/spring_mysite/";
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String uploadForm(Model model) {
		List<FileVO> list = fileUploadService.selectAll();
		model.addAttribute("fileList",list);
		return "fileupload/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("user_no") int user_no) {
		System.out.println(file.toString());
		System.out.println(file.getOriginalFilename());
		System.out.println(user_no);
		fileUploadService.restore(file,user_no);
		return "redirect: "+path+"gallary/list";
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public String delete(@RequestParam("no") int no) {
		fileUploadService.delete(no);
		return "redirect: "+path+"gallary/list";
	}
}
