package com.internation.info.model;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.id
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.title
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.original
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Integer original;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.content
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.uid
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.blog_type
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private String blog_type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.ispublish
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Integer ispublish;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.isprivate
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Integer isprivate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.createTime
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.id
     *
     * @return the value of article.id
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.id
     *
     * @param id the value for article.id
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.title
     *
     * @return the value of article.title
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.title
     *
     * @param title the value for article.title
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.original
     *
     * @return the value of article.original
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Integer getOriginal() {
        return original;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.original
     *
     * @param original the value for article.original
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setOriginal(Integer original) {
        this.original = original;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.content
     *
     * @return the value of article.content
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.content
     *
     * @param content the value for article.content
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.uid
     *
     * @return the value of article.uid
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.uid
     *
     * @param uid the value for article.uid
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.blog_type
     *
     * @return the value of article.blog_type
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public String getBlog_type() {
        return blog_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.blog_type
     *
     * @param blog_type the value for article.blog_type
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setBlog_type(String blog_type) {
        this.blog_type = blog_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.ispublish
     *
     * @return the value of article.ispublish
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Integer getIspublish() {
        return ispublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.ispublish
     *
     * @param ispublish the value for article.ispublish
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setIspublish(Integer ispublish) {
        this.ispublish = ispublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.isprivate
     *
     * @return the value of article.isprivate
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Integer getIsprivate() {
        return isprivate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.isprivate
     *
     * @param isprivate the value for article.isprivate
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setIsprivate(Integer isprivate) {
        this.isprivate = isprivate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.createTime
     *
     * @return the value of article.createTime
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.createTime
     *
     * @param createTime the value for article.createTime
     *
     * @mbggenerated Tue Mar 13 14:57:04 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}