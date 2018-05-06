package com.internation.info.controller;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internation.info.model.OtherInfo;
import com.internation.info.service.OtherInfoService;
import com.internation.info.vo.OtherInfoVo;
@Controller
public class otherInfoController {
	@Autowired
	OtherInfoService otherInfoService;
	@RequestMapping("/otherInfo")
	public String findAllOtherInfo(Model model){
		List<OtherInfo> list = otherInfoService.findAllOtherInfo();
		model.addAttribute("otherInfoList", list);
		return "otherInfoList";
	}
	@RequestMapping("/otherInfoTop10")
	public String findOtherInfoTop10(Model model) {
		List<OtherInfo> list = otherInfoService.findOtherInfoTop10();
		List<OtherInfo> top10List = new ArrayList<>();
		int index = 0;
		if (list != null && list.size() > 0) {
			if (list.size() > 10) {
				if (index < 10) {
					for (OtherInfo otherInfo : list) {
						index++;
						top10List.add(otherInfo);
					}
				}
			}else{
				for (OtherInfo otherInfo : list) {
					top10List.add(otherInfo);
				}
			}
		}
		model.addAttribute("otherInfoList", top10List);
		return "user/successMain";
	}

	@RequestMapping("/otherInfoDetail/{id}")
	public String findOtherInfoDetail(@PathVariable("id") int oId,Model model){
		System.out.println(oId);
		OtherInfo findOtherInfo = otherInfoService.findOtherInfo(oId);
		OtherInfoVo otherInfoVo = new OtherInfoVo();
		otherInfoVo.setId(findOtherInfo.getId());
		otherInfoVo.setCreateTime(findOtherInfo.getCreateTime());
		otherInfoVo.setSeecount(findOtherInfo.getSeecount());
		otherInfoVo.setTitle(findOtherInfo.getTitle());
		String strContent = new String(findOtherInfo.getContent()); 
		String stContent;
		try {
			byte[] b_utf8 = strContent.getBytes("utf-8");
			stContent = new String(b_utf8,"utf-8");
			otherInfoVo.setStrContent(stContent);
			otherInfoVo.setUsername(findOtherInfo.getUsername());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("otherInfoVo", otherInfoVo);
		return "otherInfoDetail";
	}
}
