package com.internation.info.dao;

import com.internation.info.model.Question;
import com.internation.info.model.QuestionExample.Criteria;
import com.internation.info.model.QuestionExample.Criterion;
import com.internation.info.model.QuestionExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class QuestionSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String countByExample(QuestionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("question");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String deleteByExample(QuestionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("question");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String insertSelective(Question record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("question");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestioner() != null) {
            sql.VALUES("questioner", "#{questioner,jdbcType=INTEGER}");
        }
        
        if (record.getQuestionTime() != null) {
            sql.VALUES("questionTime", "#{questionTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsresolve() != null) {
            sql.VALUES("isresolve", "#{isresolve,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorId() != null) {
            sql.VALUES("professorId", "#{professorId,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorName() != null) {
            sql.VALUES("professorName", "#{professorName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdoptTheContent() != null) {
            sql.VALUES("adoptTheContent", "#{adoptTheContent,jdbcType=VARCHAR}");
        }
        
        if (record.getSeeCount() != null) {
            sql.VALUES("seeCount", "#{seeCount,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String selectByExample(QuestionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("title");
        sql.SELECT("content");
        sql.SELECT("questioner");
        sql.SELECT("questionTime");
        sql.SELECT("isresolve");
        sql.SELECT("professorId");
        sql.SELECT("professorName");
        sql.SELECT("adoptTheContent");
        sql.SELECT("seeCount");
        sql.FROM("question");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        Question record = (Question) parameter.get("record");
        QuestionExample example = (QuestionExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("question");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestioner() != null) {
            sql.SET("questioner = #{record.questioner,jdbcType=INTEGER}");
        }
        
        if (record.getQuestionTime() != null) {
            sql.SET("questionTime = #{record.questionTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsresolve() != null) {
            sql.SET("isresolve = #{record.isresolve,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorId() != null) {
            sql.SET("professorId = #{record.professorId,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorName() != null) {
            sql.SET("professorName = #{record.professorName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdoptTheContent() != null) {
            sql.SET("adoptTheContent = #{record.adoptTheContent,jdbcType=VARCHAR}");
        }
        
        if (record.getSeeCount() != null) {
            sql.SET("seeCount = #{record.seeCount,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("question");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        sql.SET("questioner = #{record.questioner,jdbcType=INTEGER}");
        sql.SET("questionTime = #{record.questionTime,jdbcType=TIMESTAMP}");
        sql.SET("isresolve = #{record.isresolve,jdbcType=INTEGER}");
        sql.SET("professorId = #{record.professorId,jdbcType=INTEGER}");
        sql.SET("professorName = #{record.professorName,jdbcType=VARCHAR}");
        sql.SET("adoptTheContent = #{record.adoptTheContent,jdbcType=VARCHAR}");
        sql.SET("seeCount = #{record.seeCount,jdbcType=INTEGER}");
        
        QuestionExample example = (QuestionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    public String updateByPrimaryKeySelective(Question record) {
        SQL sql = new SQL();
        sql.UPDATE("question");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getQuestioner() != null) {
            sql.SET("questioner = #{questioner,jdbcType=INTEGER}");
        }
        
        if (record.getQuestionTime() != null) {
            sql.SET("questionTime = #{questionTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsresolve() != null) {
            sql.SET("isresolve = #{isresolve,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorId() != null) {
            sql.SET("professorId = #{professorId,jdbcType=INTEGER}");
        }
        
        if (record.getProfessorName() != null) {
            sql.SET("professorName = #{professorName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdoptTheContent() != null) {
            sql.SET("adoptTheContent = #{adoptTheContent,jdbcType=VARCHAR}");
        }
        
        if (record.getSeeCount() != null) {
            sql.SET("seeCount = #{seeCount,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated Sun Apr 01 13:13:18 CST 2018
     */
    protected void applyWhere(SQL sql, QuestionExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}