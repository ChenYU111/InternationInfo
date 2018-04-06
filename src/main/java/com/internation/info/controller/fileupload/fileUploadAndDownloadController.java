package com.internation.info.controller.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class fileUploadAndDownloadController {
	@RequestMapping("/fileUpload")
	public String fileUpload(){
		return "fileUpload/fileUpload";
	}
	
	@RequestMapping("/fileUploadAndDownload")
	public String fileUploadAndDounload(){
		return "fileUpload/fileTemplate";
	}
	//文件上传
	@RequestMapping(value = "/uploadFileAction", method = RequestMethod.POST)  
	public ModelAndView uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Long id) {  
	    ModelAndView modelAndView = new ModelAndView();  
	    modelAndView.setViewName("fileUpload/fileUpload");  
	    InputStream fis = null;  
	    OutputStream outputStream = null;  
	    try {  
	        fis = uploadFile.getInputStream();  
	        outputStream = new FileOutputStream("D:\\uploadfile\\"+uploadFile.getOriginalFilename());  
	        IOUtils.copy(fis,outputStream);  
	        modelAndView.addObject("fileUpload/uploadSuccess");
	        return modelAndView;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }finally {  
	        if(fis != null){  
	            try {  
	                fis.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        if(outputStream != null){  
	            try {  
	                outputStream.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    modelAndView.addObject("fileUpload/uploadfail");
	    return modelAndView;  
	}
	
	@RequestMapping("/fileDownload")
	public String fileDownload(){
		return "fileUpload/downloadFile";
	}
	
	//下载
	@RequestMapping("/downloadFileAction")  
	public void downloadFileAction(HttpServletRequest request, HttpServletResponse response) {  
	  
	    response.setCharacterEncoding(request.getCharacterEncoding());  
	    response.setContentType("application/octet-stream");  
	    FileInputStream fis = null;  
	    try {  
	        File file = new File("D:\\uploadfile\\logo.jpg");  
	        fis = new FileInputStream(file);  
	        response.setHeader("Content-Disposition", "attachment; filename="+file.getName());  
	        IOUtils.copy(fis,response.getOutputStream());  
	        response.flushBuffer();  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (fis != null) {  
	            try {  
	                fis.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	}  
}
