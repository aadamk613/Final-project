package com.kh.finalproject.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileRenamePolicy implements FileRenamePolicy {
	
	// 반드시 미완성된 rename이라는 추상메소드를 오버라이딩해서 구현해야함!!
	// 기본파일을 전달받아서 파일명 수정작업 후 수정된 파일을 반환시켜줄 것
	
	@Override
	public File rename(File originFile) {
		
		// 원본 파일명!! => 매개변수로부터 전달받은 원본파일에서 뽑아내기!
		
		String originName = originFile.getName();
		
		// "aaa.jpg"
		// "bb.b.txt"
		// "c.c.c.png"
		
		// 수정파일명 만들기(규칙) => 최대한 이름이 안겹치게끔!!!!!!!!!!!!!!!!!!!!!
		
		// KakaoTalk_20230713_183458800.jpg
		
		// KH_파일이업로드된시간(년월일시분초) + 5자리랜던값(10000 ~ 99999) + 확장자
		
		/*
		 *  원본명						수정명
		 *  aaa.jpg		  =>			KH_20234092009293099999.jpg
		 */
		// 1. 파일이 업로드된 시간 추출 => String currentTime;
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2. 5자리 랜덤값 추출 => int randomNum;
		int randomNum = (int)(Math.random() * 90000) + 10000;
		
		// 3. 확장자 뽑기 => String ext
		// lastIndexOf(찾고자하는 문자열) + substring()
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 1 + 2 + 3조합해서 수정파일명을 변수에담기
		String changeName = "KH_" + currentTime + "_" + randomNum + ext;
		
		// 기존파일을 수정된 파일명으로 적용시켜서 반환
		return new File(originFile.getParent(), changeName);
		
	}
}