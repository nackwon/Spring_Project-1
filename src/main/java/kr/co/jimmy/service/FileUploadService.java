package kr.co.jimmy.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.DAO.FileUploadDAO;
import kr.co.jimmy.VO.FileVO;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadDAO dao;

	public FileVO restore(MultipartFile file, int user_no) {
		String saveDir = "D:\\dev\\fileupload"; // 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName : " + exName);

		// 저장파일명 시간 +난수 긴 것을 준다.
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString();
		System.out.println("saveName : " + saveName);

		// 파일 패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);

		// 파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);

		FileVO fileVo = new FileVO(filePath, orgName, saveName, fileSize, user_no);
		System.out.println(fileVo.toString());
		dao.insert(fileVo);
		
		// 서버에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);

			if (bout != null)
				bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileVo;
	}

	public List<FileVO> selectAll() {
		List<FileVO> list = dao.selectAll();
		return list;
	}
	
	public int delete(int no) {
		return dao.delete(no);
	}
}
