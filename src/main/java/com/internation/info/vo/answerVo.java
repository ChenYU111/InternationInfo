package com.internation.info.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.internation.info.model.Answer;

public class answerVo extends Answer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private Integer uId;
	private Date createTime;
	private List<questionRevertVo> questionRevertVoList;
	public Date getCreateTime() {
		return createTime;
	}

	public void AnswerTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public List<questionRevertVo> getQuestionRevertVoList() {
		return questionRevertVoList;
	}

	public void setQuestionRevertVoList(List<questionRevertVo> questionRevertVoList) {
		this.questionRevertVoList = questionRevertVoList;
	}
}
