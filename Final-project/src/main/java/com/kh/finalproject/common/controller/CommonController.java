package com.kh.finalproject.common.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.common.model.vo.Files;

@Controller
public class CommonController {

	@RequestMapping("main")
	public String mainPage() {
		return "common/main";
	}
	
	/** @author  
	 * @param upfile
	 * @param session
	 * @param savePathFolder : 자유게시판, 공지사항, 체험학습, 식물 각각의 게시판에 맞게 파일을 저장할 추가 경로
	 * @return
	 */
	public Files setFile(MultipartFile upfile, HttpSession session, String savePathFolder) {
		
		Files file = new Files();

		String originalName = upfile.getOriginalFilename();
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int ranNum = (int)Math.random() * 9000 + 10000;
			
		String ext = originalName.substring(originalName.lastIndexOf("."));
		String changeName = currentTime + ranNum + ext;
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/" + savePathFolder);
		
		
		file.setFilePath("/resources/uploadFiles/" + savePathFolder);
		file.setRefType(savePathFolder);
		file.setOriginalName(originalName);
		file.setUpdateName(changeName);

		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
			return file;
		}
	
	
}
