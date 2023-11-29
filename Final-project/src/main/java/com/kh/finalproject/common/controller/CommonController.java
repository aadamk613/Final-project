package com.kh.finalproject.common.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.common.model.service.CommonService;
import com.kh.finalproject.common.model.vo.Attachment;

@Controller
public class CommonController {
	
	@Autowired
	private CommonService commonService;

	@RequestMapping("main")
	public String mainPage() {
		return "common/main";
	}
	
	
	public ArrayList<Attachment> selectFiles(int refNo, String string) {
		HashMap<Object, Object> map = new HashMap();
		map.put("refNo", refNo);
		map.put("refType", string);
		
		return commonService.selectFiles(map);
	}
	
	
	/** @author  
	 * @param upfile
	 * @param session
	 * @param savePathFolder : 자유게시판, 공지사항, 체험학습, 식물 각각의 게시판에 맞게 파일을 저장할 추가 경로
	 * @return
	 */
	public Attachment setFile(MultipartFile upfile, HttpSession session, String savePathFolder) {
		
		Attachment file = new Attachment();

		String originalName = upfile.getOriginalFilename();
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int ranNum = (int)(Math.random() * 9000) + 10000;
		String ext = originalName.substring(originalName.lastIndexOf("."));
		
		String changeName = currentTime + ranNum + ext;
		String savePath = session.getServletContext().getRealPath("resources/uploadFiles/" + savePathFolder + "/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		file.setFilePath("resources/uploadFiles/" + savePathFolder + "/");
		file.setRefType(savePathFolder);
		file.setOriginalName(originalName);
		file.setUpdateName(changeName);

		return file;
	}
	
	public int updateFiles(Attachment file) {
		
		return commonService.updateFiles(file);
		
	}
	
	public int deleteFiles(Attachment file) {
		
		return commonService.deleteFiles(file);
		
	}


	
	
}
