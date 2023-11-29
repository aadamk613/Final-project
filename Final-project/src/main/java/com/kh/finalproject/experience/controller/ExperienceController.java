package com.kh.finalproject.experience.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private CommonController commonController;
	
	// 체험학습 목록조회
	/**
	 * @param currentPage : 체험학습 게시글 현재 페이지 (기본값 1페이지)
	 * @param model : 데이터를 담아 보내줄 Model
	 * @return : 체험학습 게시글 목록으로 이동
	 */
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model){
		// 페이지는 기본값 1로 설정
		// System.out.println(success);
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		model.addAttribute("experienceList", experienceService.selectExperienceList(pi));
		model.addAttribute("pi", pi);
		return "experience/experienceListView";
	}
	
	// 체험학습 게시글 상세조회
	/**
	 * @param expNo : 게시글 번호
	 * @param model : 데이터 담아 보내줄 Model객체
	 * @param session : 데이터 담아 보내줄 Session객체
	 * @return : 성공 시 상세조회 페이지로 이동 / 실패 시 게시글 목록으로 이동
	 */
	@RequestMapping("yrdetail.exp")
	public String selectExperience(int expNo, Model model, HttpSession session) {
		System.out.println(expNo);
		
		// 조회수 증가 성공 시 
		if(experienceService.increaseCount(expNo) > 0 ) { 
			// 게시글 상세조회
			model.addAttribute("exp", experienceService.selectExperience(expNo));
			
			System.out.println("파일넘버 나와라");
			System.out.println(model.getAttribute("exp"));
			
			// 첨부파일 조회
			// HashMap map = new HashMap();
			// map.put("refNo", expNo);
			// map.put("refType", "EXPERIENCE");
			model.addAttribute("files", commonController.selectFiles(expNo, "experience"));
			System.out.println("왜 아무것도 안나와");
			System.out.println(model.getAttribute("files"));
			return "experience/experienceDetailView";
		// 조회수 증가 실패 시	
		} else {
			session.setAttribute("error", "게시글 상세조회에 실패하셨습니다. 다시 이용해 주세요.");
			// model.addAttribute("error", "조회수 증가 실패");
			return "redirect:yrlist.exp";
			// return "experience/experienceListView";
		}
	}
	
	// 수정하기 페이지로 이동
	// insert메소드 같이 써도 될거같은데 단일책임의원칙 걸려서 따로 씀
	/**
	 * @param exp : 수정할 때 기본으로 입력되어있을 게시글 정보
	 * @param fileNo : 게시글에 첨부된 파일 번호
	 * @param filePath : 게시글에 첨부된 파일 경로
	 * @param updateName : 게시글에 첨부된 파일 수정된 이름
	 * @param fileAnnotation : 게시글에 첨부된 파일 주석
	 * @param mv : 데이터 담아 보내줄 ModelAndView 객체
	 * @return : ModelAndView객체로 게시글 작성 페이지로 이동
	 */
	@PostMapping("yrupdateExpForm.exp")
	public ModelAndView updateExperienceForm(Experience exp, Integer[] fileNo, String[] filePath, String[] updateName, String[] fileAnnotation, ModelAndView mv) {
		ArrayList<Attachment> files = new ArrayList();
		for(int i = 0; i < filePath.length; i++) {
			Attachment file = new Attachment();
			file.setFileNo(fileNo[i]);
			file.setFilePath(filePath[i]);
			file.setUpdateName(updateName[i]);
			file.setFileAnnotation(fileAnnotation[i]);
			files.add(file);
		}
		System.out.println("파일이다 받아라");
		System.out.println(files.toString());
		mv.addObject("exp", exp)
		  .addObject("files", files)
		  .setViewName("experience/experienceWrite");
		return mv;
	}
	
	// 게시글 수정
	/**
	 * @param exp : 수정된 게시글 정보
	 * @param upfiles : 게시글에 첨부된 파일 배열
	 * @param anno : 게시글에 첨부된 파일의 주석 배열
	 * @param oldFiles : 게시글에 첨부되었다가 수정되기 전 파일의 (경로 + 수정명)의 배열
	 * @param session : Session 객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrupdateExp.exp")
	public String updateExperience(Experience exp, MultipartFile[] upfiles, String[] anno, String[] oldFiles, HttpSession session) {
		
		System.out.println("나와라 기존파일!!!!!!!!!!!!!!!!!!!!");
		System.out.println(anno.toString());
		
		// 1. 원래 있던 파일 지우고 oldFileNo delete
		
		for(String oldFile : oldFiles) {
			System.out.println("지워질 파일 이름");
			System.out.println(oldFile);
			System.out.println(session.getServletContext().getRealPath(oldFile));
			new File(session.getServletContext().getRealPath(oldFile)).delete();
			
			// oldFile.substring(oldFile.lastIndexOf("/"));
		}
		
		System.out.println("여기까진 온거야?");
		
		// 2. 새로 들어온 파일 MultipartFile insert 
		ArrayList<Attachment> fileList = new ArrayList();
		System.out.println(fileList);
		for(int i = 0; i < upfiles.length; i++) {
			
			Attachment file = new Attachment();
			
			if(!upfiles[i].getOriginalFilename().equals("")) {
				System.out.println("하나하나");
				System.out.println(upfiles[i]);
				file = commonController.setFile(upfiles[i], session, "experience");
				file.setRefNo(exp.getExpNo());
				System.out.println("여기가 문제라고?");
			} else {
				continue;
			}
			System.out.println(anno[i]);
			file.setFileAnnotation(anno[i]);
			fileList.add(file);
			
		} 
		
		if(experienceService.updateExperience(exp, fileList, oldFiles) > 0) {
			session.setAttribute("alertMsg", "게시글이 수정되었습니다.");
		} else {
			session.setAttribute("alertMsg", "게시글 수정에 실패하셨습니다.");
		}
		return "redirect:yrlist.exp";
	}
	
	// 게시글 작성 페이지로 이동
	/**
	 * @return : 작성화면으로 이동
	 */
	@GetMapping("yrinsertExpForm.exp")
	public String insertExperienceoForm() {
		return "experience/experienceWrite";
	}
	
	// 게시글 작성
	/**
	 * @param exp : 작성된 게시글의 정보
	 * @param upfiles : 게시글에 첨부된 파일 배열
	 * @param anno : 게시글에 첨부된 파일의 주석 배열
	 * @param session : Session객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrinsertExp.exp")
	public String insertExperience(Experience exp, 
								   MultipartFile[] upfiles, 
								   String[] anno, 
								   HttpSession session) {
		// for(MultipartFile upfile : upfiles) {
//		System.out.println("인서트");
//		System.out.println(upfiles);
//		System.out.println(upfiles[0]);
//		System.out.println(exp);
		
		System.out.println("갑자기 널?");
		System.out.println(upfiles[0]);
		System.out.println(anno[0]);
		
		ArrayList<Attachment> fileList = new ArrayList();
		for(int i = 0; i < upfiles.length; i++) {
			
			System.out.println(i);
			
			if(!upfiles[i].getOriginalFilename().equals("")) {
				
				System.out.println("나오자 좀");
				System.out.println(upfiles[i]);
				
				Attachment file = commonController.setFile(upfiles[i], session, "experience");
				System.out.println("엥  여기 번호가 있음?????");
				System.out.println(exp.getExpNo()); // 0
				//file.setRefNo(exp.getExpNo());
				System.out.println(anno[i]);
				file.setFileAnnotation(anno[i]);
				fileList.add(file);
			} 
		}
		
		if(experienceService.insertExperience(exp, fileList) > 0) {
			session.setAttribute("alertMsg", "게시글이 등록되었습니다.");
		} else {
			session.setAttribute("alertMsg", "게시글 등록에 실패하셨습니다.");
		}
		return "redirect:yrlist.exp";
	}
	
	// 게시글 삭제
	/**
	 * @param expNo : 삭제할 게시글 번호
	 * @param session : Alert 데이터 담을 Session객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrdeleteExp.exp")
	public String deleteExperience(int expNo, HttpSession session) {
		if(experienceService.deleteExperience(expNo) > 0) {
			session.setAttribute("success", "체험학습 게시글이 삭제되었습니다.");
		} else {
			session.setAttribute("error", "체험학습 게시글 삭제에 실패하셨습니다.");
		}
		// ResponseEntity<String> re = new ResponseEntity<String>("체험학습 게시글이 삭제되었습니다.", HttpStatus.OK);
		// JSONObject객체로 키-밸류로 넘길수도 있음
		// 이렇게 넘기면 한글은 0~255를 넘기기 때문에 POST방식으로 받아야 함
		// 받을 때 데이터를 @RequestBody String str 이렇게 받음
		return "redirect:yrlist.exp";
	}
	
	// 댓글 조회
	/**
	 * @param expNo : 조회할 댓글의 게시글 번호
	 * @return : 댓글의 리스트 반환
	 */
	@ResponseBody
	@RequestMapping(value="yrselectExpReplyList.exp", produces="application/json; charset=UTF-8")
	public String selectExpReplyList(int expNo) {
		System.out.println("하하");
		System.out.println(experienceService.selectExpReplyList(expNo).toString());
		return new Gson().toJson(experienceService.selectExpReplyList(expNo));
	}
	
	// 댓글 작성
	/**
	 * @param newReply : 작성한 댓글의 정보
	 * @return : 댓글작성 성공여부
	 * @throws ParseException
	 */
	@ResponseBody
	@PostMapping("yrinsertExpReply.exp")
	public String insertExpReply(@RequestBody ExperienceReply newReply) throws ParseException {
		
		System.out.println("왜 이건 안들어가지");
		newReply.setReplySecret((Integer.parseInt(newReply.getReplySecret()) > 0) ? "Y" : "N");
		System.out.println(newReply.toString());
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// 버전 2.8.6
		//JsonObject jobj = JsonParser.parseString(expReply).getAsJsonObject();
		// 버전 2.8.5
		//JsonObject jobj = new JsonParser().parse(newReply).getAsJsonObject();
		// System.out.println(jobj);
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// System.out.println(jobj.get("expNo").getAsInt()); // 61
		// System.out.println(jobj.get("replyContent")); // "ㅁㄴㅇㄹ"
		
		// set해서 넣어줄 수 밖에 없음 (null일수도 있으니까 이렇게 해주는게 맞음)
//		ExperienceReply expReply = new ExperienceReply();
//		expReply.setExpNo(jobj.get("expNo").getAsInt());
//		expReply.setReplyContent(jobj.get("replyContent").getAsString());
//		expReply.setReplyWriter(jobj.get("replyWriter").getAsString());
//		expReply.setReplySecret((jobj.get("replySecret").getAsInt() > 0) ? "Y" : "N");
		
		return (experienceService.insertExpReply(newReply) > 0) ? "success" : "fail";
	}
	
	// 좋아요 눌려있는지 체크
	/**
	 * @param expNo : 좋아요 누른 게시글 번호
	 * @param memNo : 좋아요 누른 회원 번호
	 * @return
	 */
	@ResponseBody
	@GetMapping("yrexpLikeCheck")
	public int expLikeCheck(int expNo, int memNo) {
		
		HashMap map = new HashMap();
		map.put("expNo", expNo);
		map.put("memNo", memNo);
		
		return experienceService.selectExpLike(map);
	}

	// 좋아요 등록/취소
	/**
	 * @param expNo : 좋아요 누른 게시글 번호
	 * @param likeVal : 좋아요 눌렀으면 1, 취소하면 0
	 * @param memNo : 좋아요 누른 회원 번호
	 * @return
	 */
	@ResponseBody
	@GetMapping(value="yrexpLike")
	public int expLike(int expNo, int likeVal, int memNo) {
		
		HashMap map = new HashMap();
		map.put("expNo", expNo);
		map.put("memNo", memNo);
		
		// 좋아요라면 
		if(likeVal > 0) {
			// 좋아요 등록
			return experienceService.insertExpLike(map);
			
		} else {
			// 좋아요 취소
			return experienceService.deleteExpLike(map);
		}
	}
	
	// 체험학습 댓글 삭제
	@ResponseBody
	@PostMapping("yrdeleteExpReply")
	public String deleteExpReply(int expReplyNo) {
		
		if(experienceService.deleteExpReply(expReplyNo) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
