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
	
	/**
	 * @param refNo : 첨부게시글 번호
	 * @param string : 첨부게시판 타입
	 * @return : 해당 게시글의 첨부파일 리스트
	 */
	public ArrayList<Attachment> selectFiles(int refNo, String string) {
		HashMap<Object, Object> map = new HashMap();
		map.put("refNo", refNo);
		map.put("refType", string);
		System.out.println(map);
		
		return commonService.selectFiles(map);
	}
	
	
	/** @author  
	 * @param upfile
	 * @param session
	 * @param savePathFolder : 자유게시판, 공지사항, 체험학습, 식물 각각의 게시판에 맞게 파일을 저장할 추가 경로
	 * @return
	 */
	
	public Attachment setFile(MultipartFile upfile, 
							  HttpSession session, 
							  String savePathFolder) {
		
		Attachment attchment = new Attachment();

		String originalName = upfile.getOriginalFilename();
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int ranNum = (int)(Math.random() * 9000) + 10000;
		String ext = originalName.substring(originalName.lastIndexOf("."));
		
		String updateName = currentTime + ranNum + ext;
		String path = "resources/uploadFiles/" + savePathFolder + "/";
		String savePath = session.getServletContext().getRealPath(path);
		
		try {
			upfile.transferTo(new File(savePath + updateName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		attchment.setOriginalName(originalName);
		attchment.setUpdateName(updateName);
		attchment.setFilePath(path);
		attchment.setRefType(savePathFolder);

		return attchment;
	}
	
	/**
	 * @param file : 업데이트할 파일
	 * @return : 결과값 1, 0
	 */
	public int updateFiles(Attachment file) {
		return commonService.updateFiles(file);
		
	}
	
	/**
	 * @param file : 삭제할 파일
	 * @return : 결과값 1, 0
	 */
	public int deleteFiles(Attachment file) {
		return commonService.deleteFiles(file);
		
	}


	
	
}
