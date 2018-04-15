package com.internation.info.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;
@Repository
public class FileUpload implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.fileName
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private String fileName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.fileUrl
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private String fileUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.fileDescription
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private String fileDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.createTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer uId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.seecount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer seecount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.downLoadCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private Integer downLoadCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fileupload.collectionCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private String collectionCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.id
     *
     * @return the value of fileupload.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.id
     *
     * @param id the value for fileupload.id
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.fileName
     *
     * @return the value of fileupload.fileName
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.fileName
     *
     * @param fileName the value for fileupload.fileName
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.fileUrl
     *
     * @return the value of fileupload.fileUrl
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.fileUrl
     *
     * @param fileUrl the value for fileupload.fileUrl
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.fileDescription
     *
     * @return the value of fileupload.fileDescription
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String getFileDescription() {
        return fileDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.fileDescription
     *
     * @param fileDescription the value for fileupload.fileDescription
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.createTime
     *
     * @return the value of fileupload.createTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.createTime
     *
     * @param createTime the value for fileupload.createTime
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.uId
     *
     * @return the value of fileupload.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.uId
     *
     * @param uId the value for fileupload.uId
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.seecount
     *
     * @return the value of fileupload.seecount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getSeecount() {
        return seecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.seecount
     *
     * @param seecount the value for fileupload.seecount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setSeecount(Integer seecount) {
        this.seecount = seecount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.downLoadCount
     *
     * @return the value of fileupload.downLoadCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public Integer getDownLoadCount() {
        return downLoadCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.downLoadCount
     *
     * @param downLoadCount the value for fileupload.downLoadCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setDownLoadCount(Integer downLoadCount) {
        this.downLoadCount = downLoadCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fileupload.collectionCount
     *
     * @return the value of fileupload.collectionCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String getCollectionCount() {
        return collectionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fileupload.collectionCount
     *
     * @param collectionCount the value for fileupload.collectionCount
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public void setCollectionCount(String collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
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
        sb.append(", fileName=").append(fileName);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", fileDescription=").append(fileDescription);
        sb.append(", createTime=").append(createTime);
        sb.append(", uId=").append(uId);
        sb.append(", seecount=").append(seecount);
        sb.append(", downLoadCount=").append(downLoadCount);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append("]");
        return sb.toString();
    }
}