package com.internation.info.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internation.info.common.HttpUtils;
import com.internation.info.common.UrlUtils;



@Controller
public class ArticleController {

	@RequestMapping("article")
	public String index() {
		return "web/article/article";
	}

	@RequestMapping("article/query")
	@ResponseBody
	public ResponseBo queryArticle(String date) {
		String param = "";
		String data = "";
		try {
			if (null!=date&&!date.equals("")) {
				param = "dev=1&date=" + date;
				data = HttpUtils.sendSSLPost(UrlUtils.MRYW_DAY_URL, param);
			} else {
				param = "dev=1";
				data = HttpUtils.sendSSLPost(UrlUtils.MRYW_TODAY_URL, param);
			}
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取文章失败，请联系网站管理员！");
		}
	}
}
