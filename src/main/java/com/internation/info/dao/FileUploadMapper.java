package com.internation.info.dao;

import com.internation.info.model.FileUpload;
import com.internation.info.model.FileUploadExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface FileUploadMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @SelectProvider(type=FileUploadSqlProvider.class, method="countByExample")
    int countByExample(FileUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @DeleteProvider(type=FileUploadSqlProvider.class, method="deleteByExample")
    int deleteByExample(FileUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @Delete({
        "delete from fileupload",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @Insert({
        "insert into fileupload (id, fileName, ",
        "fileUrl, fileDescription, ",
        "createTime, uId, ",
        "seecount, downLoadCount, ",
        "collectionCount)",
        "values (#{id,jdbcType=INTEGER}, #{fileName,jdbcType=VARCHAR}, ",
        "#{fileUrl,jdbcType=VARCHAR}, #{fileDescription,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{uId,jdbcType=INTEGER}, ",
        "#{seecount,jdbcType=INTEGER}, #{downLoadCount,jdbcType=INTEGER}, ",
        "#{collectionCount,jdbcType=VARCHAR})"
    })
    int insert(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @InsertProvider(type=FileUploadSqlProvider.class, method="insertSelective")
    int insertSelective(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @SelectProvider(type=FileUploadSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fileName", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="fileUrl", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="fileDescription", property="fileDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="uId", property="uId", jdbcType=JdbcType.INTEGER),
        @Result(column="seecount", property="seecount", jdbcType=JdbcType.INTEGER),
        @Result(column="downLoadCount", property="downLoadCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collectionCount", property="collectionCount", jdbcType=JdbcType.VARCHAR)
    })
    List<FileUpload> selectByExample(FileUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @Select({
        "select",
        "id, fileName, fileUrl, fileDescription, createTime, uId, seecount, downLoadCount, ",
        "collectionCount",
        "from fileupload",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fileName", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="fileUrl", property="fileUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="fileDescription", property="fileDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="uId", property="uId", jdbcType=JdbcType.INTEGER),
        @Result(column="seecount", property="seecount", jdbcType=JdbcType.INTEGER),
        @Result(column="downLoadCount", property="downLoadCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collectionCount", property="collectionCount", jdbcType=JdbcType.VARCHAR)
    })
    FileUpload selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @UpdateProvider(type=FileUploadSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @UpdateProvider(type=FileUploadSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FileUpload record, @Param("example") FileUploadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @UpdateProvider(type=FileUploadSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    @Update({
        "update fileupload",
        "set fileName = #{fileName,jdbcType=VARCHAR},",
          "fileUrl = #{fileUrl,jdbcType=VARCHAR},",
          "fileDescription = #{fileDescription,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "uId = #{uId,jdbcType=INTEGER},",
          "seecount = #{seecount,jdbcType=INTEGER},",
          "downLoadCount = #{downLoadCount,jdbcType=INTEGER},",
          "collectionCount = #{collectionCount,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FileUpload record);
}