package com.internation.info.dao;

import com.internation.info.model.Answer;
import com.internation.info.model.AnswerExample;
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

public interface AnswerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @SelectProvider(type=AnswerSqlProvider.class, method="countByExample")
    int countByExample(AnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @DeleteProvider(type=AnswerSqlProvider.class, method="deleteByExample")
    int deleteByExample(AnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @Delete({
        "delete from answer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @Insert({
        "insert into answer (id, uId, ",
        "content, floor, ",
        "answerTime, isAdopt, ",
        "requestId)",
        "values (#{id,jdbcType=INTEGER}, #{uId,jdbcType=INTEGER}, ",
        "#{content,jdbcType=VARCHAR}, #{floor,jdbcType=INTEGER}, ",
        "#{answerTime,jdbcType=TIMESTAMP}, #{isAdopt,jdbcType=INTEGER}, ",
        "#{requestId,jdbcType=INTEGER})"
    })
    int insert(Answer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @InsertProvider(type=AnswerSqlProvider.class, method="insertSelective")
    int insertSelective(Answer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @SelectProvider(type=AnswerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uId", property="uId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor", property="floor", jdbcType=JdbcType.INTEGER),
        @Result(column="answerTime", property="answerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isAdopt", property="isAdopt", jdbcType=JdbcType.INTEGER),
        @Result(column="requestId", property="requestId", jdbcType=JdbcType.INTEGER)
    })
    List<Answer> selectByExample(AnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @Select({
        "select",
        "id, uId, content, floor, answerTime, isAdopt, requestId",
        "from answer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uId", property="uId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor", property="floor", jdbcType=JdbcType.INTEGER),
        @Result(column="answerTime", property="answerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isAdopt", property="isAdopt", jdbcType=JdbcType.INTEGER),
        @Result(column="requestId", property="requestId", jdbcType=JdbcType.INTEGER)
    })
    Answer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @UpdateProvider(type=AnswerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @UpdateProvider(type=AnswerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @UpdateProvider(type=AnswerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Answer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbggenerated Tue Mar 27 10:23:40 CST 2018
     */
    @Update({
        "update answer",
        "set uId = #{uId,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "floor = #{floor,jdbcType=INTEGER},",
          "answerTime = #{answerTime,jdbcType=TIMESTAMP},",
          "isAdopt = #{isAdopt,jdbcType=INTEGER},",
          "requestId = #{requestId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Answer record);
}