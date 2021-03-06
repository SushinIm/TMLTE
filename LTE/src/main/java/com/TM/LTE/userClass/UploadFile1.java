package com.TM.LTE.userClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.TM.LTE.dao.SellerDao;

public class UploadFile1 {
	//파일 업로드 메소드	
	//String fullPath="I:/Work2/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/SpringMVC-Board2/resources/upload/";
	@Autowired
	private SellerDao sDao;
	
	public LinkedHashMap<String, String> fileUp(MultipartHttpServletRequest multi, String pnum, String detail){
		System.out.println("fileUp");
		//1.저장경로 찾기
		String root=multi.getSession().getServletContext().getRealPath("\\");
		String path=root+"\\resources\\hotel\\";
		//2.폴더 생성을 꼭 할것...
		File dir=new File(path);
		if(!dir.isDirectory()){  //upload 폴더 없다면
			dir.mkdir();  //upload 폴더 생성
		}
		//3.파일을 가져오기-일태그 이름들 반환
		Iterator<String> files=multi.getFileNames();  //파일 업로드 2개 이상
		LinkedHashMap<String,String> fMap=new LinkedHashMap<String, String>();
		int i =0;
		while(files.hasNext()){
			String fileTagName=files.next();
			//파일 메모리에 저장
			MultipartFile mf=multi.getFile(fileTagName);  //실제파일
			String oriFileName=mf.getOriginalFilename();  //a.txt
			fMap.put("oriFileName"+i, oriFileName);
			//4.시스템파일이름 생성  a.txt  ==>112323242424.txt
			String sysFileName=System.currentTimeMillis()+"."
					+oriFileName.substring(oriFileName.lastIndexOf(".")+1);
			fMap.put("sysFileName"+i, sysFileName);
			fMap.put("path"+i, path);
			fMap.put("pnum"+i, pnum);
			fMap.put("detail"+i, detail);
			//5.메모리->실제 파일 업로드
			try {
				mf.transferTo(new File(path+sysFileName));
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		return fMap;
	}
	
	//파일 다운로드 메소드
	//파일 삭제 메소드
	
	
}

