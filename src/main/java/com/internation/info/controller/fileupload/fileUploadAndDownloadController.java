package com.internation.info.controller.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.internation.info.model.FileDownload;
import com.internation.info.model.FileUpload;
import com.internation.info.model.User;
import com.internation.info.service.FileUploadService;
import com.internation.info.vo.fileUploadVo;

@Controller
public class fileUploadAndDownloadController {
	//https://blog.csdn.net/zknxx/article/details/72633444
	
	@Autowired
	FileUpload fileUpload;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	FileDownload fileDownload;
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
	public ModelAndView uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Long id,FileUpload fLoad,HttpServletRequest req) {  
	    ModelAndView modelAndView = new ModelAndView();  
	    modelAndView.setViewName("fileUpload/fileUpload");  
	    InputStream fis = null;  
	    OutputStream outputStream = null;  
	    try {  
	        fis = uploadFile.getInputStream();  
	        String fileUrl="D:\\uploadfile\\"+uploadFile.getOriginalFilename();
	        outputStream = new FileOutputStream("D:\\uploadfile\\"+uploadFile.getOriginalFilename());  
	        IOUtils.copy(fis,outputStream);  
	        modelAndView.addObject("fileUpload/uploadSuccess");
	        fileUpload.setFileName(fLoad.getFileName());
	        fileUpload.setFileDescription(fLoad.getFileDescription());
	        fileUpload.setCreateTime(new Date());
	        fileUpload.setFileUrl(fileUrl);
	        int userId = (int) req.getSession().getAttribute("userId");
	        fileUpload.setuId(userId);
	        fileUploadService.insertFileUpload(fileUpload);   
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
	
	@RequestMapping("/fileDetail/{id}")
	public String seeFileDetail(@PathVariable("id") Integer fileId,Model model,HttpServletRequest req){
		//查看数量+1
		FileUpload fUpload = fileUploadService.findFileUpload(fileId);
	    int seeCount = fUpload.getSeecount()==null?0:fUpload.getSeecount();
	    fUpload.setSeecount(seeCount+1);
	    fileUploadService.updateFileUpload(fUpload);
		FileUpload fileUpload2 = fileUploadService.FileDetail(fileId);
		if(fileUpload2!=null&&!fileUpload2.equals("")){
			fileUploadVo fileVo = new fileUploadVo();
			fileVo.setCreateTime(fileUpload2.getCreateTime());
			fileVo.setFileName(fileUpload2.getFileName());
			User user = fileUploadService.findUserById(fileUpload2.getuId());
			if(user!=null&&!user.equals("")){
				fileVo.setUserName(user.getUserName());
			}
			fileVo.setFileDescription(fileUpload2.getFileDescription());
			fileVo.setSeecount(fileUpload2.getSeecount());
			fileVo.setDownLoadCount(fileUpload2.getDownLoadCount());
			fileVo.setId(fileUpload2.getId());
			fileVo.setFileUrl(fileUpload2.getFileUrl());
			model.addAttribute("fileUploadVo", fileVo);
			HttpSession session = req.getSession();
			session.setAttribute("fileUrl", fileVo.getFileUrl());
			session.setAttribute("fileId", fileVo.getId());
	}
		return "fileUpload/fileDetail";
	}

	
	//下载
	@RequestMapping("/downloadFileAction")  
	public void downloadFileAction(HttpServletRequest request, HttpServletResponse response,HttpServletRequest req) {  
		HttpSession session = req.getSession();
		String fileUrl = (String) session.getAttribute("fileUrl");
		int fileId = (int) session.getAttribute("fileId");
	    response.setCharacterEncoding(request.getCharacterEncoding());  
	    response.setContentType("application/octet-stream");  
	    FileInputStream fis = null;  
	    try {  
	        File file = new File(fileUrl);  
	        fis = new FileInputStream(file);  
	        response.setHeader("Content-Disposition", "attachment; filename="+file.getName());  
	        IOUtils.copy(fis,response.getOutputStream());  
	        response.flushBuffer();  
	        fileDownload.setFileId(fileId);
	        fileDownload.setuId((int)session.getAttribute("userId"));
	        fileDownload.setDownLoadTime(new Date());
	        fileUploadService.insertFileDown(fileDownload);
	        FileUpload fUpload = fileUploadService.findFileUpload(fileId);
	        int downLoadCount = fUpload.getDownLoadCount()==null?0:fUpload.getDownLoadCount();
	        fUpload.setDownLoadCount(downLoadCount+1);
	        fileUploadService.updateFileUpload(fUpload);
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
	

	@RequestMapping("/fileList")
	public String findFileList(Model model){
		List<FileUpload> findAllFileList = fileUploadService.findAllFileList();
		List<fileUploadVo> findAllFileVoList = new ArrayList<>();
		if(null!=findAllFileList&&!findAllFileList.equals("")){
			for (FileUpload fileUp : findAllFileList) {
				if(fileUp!=null&&!fileUp.equals("")){
					fileUploadVo fileVo = new fileUploadVo();
					fileVo.setCreateTime(fileUp.getCreateTime());
					fileVo.setFileName(fileUp.getFileName());
					User user = fileUploadService.findUserById(fileUp.getuId());
					if(user!=null&&!user.equals("")){
						fileVo.setUserName(user.getUserName());
					}
					fileVo.setId(fileUp.getId());
					findAllFileVoList.add(fileVo);
				}
			}
		}
		model.addAttribute("findAllFileVoList", findAllFileVoList);
		return "fileUpload/fileCanDownloadList";
	}
	
	@RequestMapping("/myUploadList")
	public String findMyFileUploadList(HttpServletRequest req,Model model){
		List<FileUpload> fileUpLoadList = fileUploadService.findMyFileUpLoadList((int)req.getSession().getAttribute("userId"));
		List<fileUploadVo> findAllFileVoList = new ArrayList<>();
		if(fileUpLoadList!=null&&!fileUpLoadList.equals("")){
			for (FileUpload fileUp : fileUpLoadList) {
				if(fileUp!=null&&!fileUp.equals("")){
					fileUploadVo fileVo = new fileUploadVo();
					fileVo.setCreateTime(fileUp.getCreateTime());
					fileVo.setFileName(fileUp.getFileName());
					User user = fileUploadService.findUserById(fileUp.getuId());
					if(user!=null&&!user.equals("")){
						fileVo.setUserName(user.getUserName());
					}
					fileVo.setId(fileUp.getId());
					findAllFileVoList.add(fileVo);
				}
			}
		}
		model.addAttribute("findAllFileVoList", findAllFileVoList);
		return "fileUpload/fileMyUploadList";
	}
	
	@RequestMapping("/myDownList")
	public String myDownLoadFileList(HttpServletRequest req,Model model){
		List<FileDownload> downLoadList = fileUploadService.findMyFileDownLoadList((int)req.getSession().getAttribute("userId"));
		List<fileUploadVo> findAllFileVoList = new ArrayList<>();
		if(downLoadList!=null&&!downLoadList.equals("")){
			for (FileDownload fileDownload : downLoadList) {
				Integer fileId = fileDownload.getFileId();
				FileUpload fileUp = fileUploadService.findFileUpload(fileId);
				fileUploadVo fileVo = new fileUploadVo();
				fileVo.setCreateTime(fileUp.getCreateTime());
				fileVo.setFileName(fileUp.getFileName());
				User user = fileUploadService.findUserById(fileUp.getuId());
				if(user!=null&&!user.equals("")){
					fileVo.setUserName(user.getUserName());
				}
				fileVo.setId(fileUp.getId());
				findAllFileVoList.add(fileVo);
			}
		}
		model.addAttribute("findAllFileVoList", findAllFileVoList);
		return "fileUpload/fileMyDownloadList";
	}
}
