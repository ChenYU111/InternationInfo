package com.internation.info.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;
@Repository
public class DownloadFileReview implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column downloadfilereview.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column downloadfilereview.reviewContent
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private String reviewContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column downloadfilereview.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer uId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column downloadfilereview.reviewTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Date reviewTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column downloadfilereview.flooer
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer flooer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table downloadfilereview
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column downloadfilereview.id
     *
     * @return the value of downloadfilereview.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column downloadfilereview.id
     *
     * @param id the value for downloadfilereview.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column downloadfilereview.reviewContent
     *
     * @return the value of downloadfilereview.reviewContent
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String getReviewContent() {
        return reviewContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column downloadfilereview.reviewContent
     *
     * @param reviewContent the value for downloadfilereview.reviewContent
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column downloadfilereview.uId
     *
     * @return the value of downloadfilereview.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column downloadfilereview.uId
     *
     * @param uId the value for downloadfilereview.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column downloadfilereview.reviewTime
     *
     * @return the value of downloadfilereview.reviewTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column downloadfilereview.reviewTime
     *
     * @param reviewTime the value for downloadfilereview.reviewTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column downloadfilereview.flooer
     *
     * @return the value of downloadfilereview.flooer
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getFlooer() {
        return flooer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column downloadfilereview.flooer
     *
     * @param flooer the value for downloadfilereview.flooer
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setFlooer(Integer flooer) {
        this.flooer = flooer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table downloadfilereview
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reviewContent=").append(reviewContent);
        sb.append(", uId=").append(uId);
        sb.append(", reviewTime=").append(reviewTime);
        sb.append(", flooer=").append(flooer);
        sb.append("]");
        return sb.toString();
    }
}