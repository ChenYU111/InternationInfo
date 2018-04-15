package com.internation.info.dao;

import com.internation.info.model.FileUpload;
import com.internation.info.model.FileUploadExample.Criteria;
import com.internation.info.model.FileUploadExample.Criterion;
import com.internation.info.model.FileUploadExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FileUploadSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String countByExample(FileUploadExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("fileupload");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String deleteByExample(FileUploadExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("fileupload");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String insertSelective(FileUpload record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("fileupload");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getFileName() != null) {
            sql.VALUES("fileName", "#{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileUrl() != null) {
            sql.VALUES("fileUrl", "#{fileUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFileDescription() != null) {
            sql.VALUES("fileDescription", "#{fileDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("createTime", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getuId() != null) {
            sql.VALUES("uId", "#{uId,jdbcType=INTEGER}");
        }
        
        if (record.getSeecount() != null) {
            sql.VALUES("seecount", "#{seecount,jdbcType=INTEGER}");
        }
        
        if (record.getDownLoadCount() != null) {
            sql.VALUES("downLoadCount", "#{downLoadCount,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionCount() != null) {
            sql.VALUES("collectionCount", "#{collectionCount,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String selectByExample(FileUploadExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("fileName");
        sql.SELECT("fileUrl");
        sql.SELECT("fileDescription");
        sql.SELECT("createTime");
        sql.SELECT("uId");
        sql.SELECT("seecount");
        sql.SELECT("downLoadCount");
        sql.SELECT("collectionCount");
        sql.FROM("fileupload");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        FileUpload record = (FileUpload) parameter.get("record");
        FileUploadExample example = (FileUploadExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("fileupload");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getFileName() != null) {
            sql.SET("fileName = #{record.fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileUrl() != null) {
            sql.SET("fileUrl = #{record.fileUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFileDescription() != null) {
            sql.SET("fileDescription = #{record.fileDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getuId() != null) {
            sql.SET("uId = #{record.uId,jdbcType=INTEGER}");
        }
        
        if (record.getSeecount() != null) {
            sql.SET("seecount = #{record.seecount,jdbcType=INTEGER}");
        }
        
        if (record.getDownLoadCount() != null) {
            sql.SET("downLoadCount = #{record.downLoadCount,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionCount() != null) {
            sql.SET("collectionCount = #{record.collectionCount,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("fileupload");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("fileName = #{record.fileName,jdbcType=VARCHAR}");
        sql.SET("fileUrl = #{record.fileUrl,jdbcType=VARCHAR}");
        sql.SET("fileDescription = #{record.fileDescription,jdbcType=VARCHAR}");
        sql.SET("createTime = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("uId = #{record.uId,jdbcType=INTEGER}");
        sql.SET("seecount = #{record.seecount,jdbcType=INTEGER}");
        sql.SET("downLoadCount = #{record.downLoadCount,jdbcType=INTEGER}");
        sql.SET("collectionCount = #{record.collectionCount,jdbcType=VARCHAR}");
        
        FileUploadExample example = (FileUploadExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    public String updateByPrimaryKeySelective(FileUpload record) {
        SQL sql = new SQL();
        sql.UPDATE("fileupload");
        
        if (record.getFileName() != null) {
            sql.SET("fileName = #{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileUrl() != null) {
            sql.SET("fileUrl = #{fileUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFileDescription() != null) {
            sql.SET("fileDescription = #{fileDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("createTime = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getuId() != null) {
            sql.SET("uId = #{uId,jdbcType=INTEGER}");
        }
        
        if (record.getSeecount() != null) {
            sql.SET("seecount = #{seecount,jdbcType=INTEGER}");
        }
        
        if (record.getDownLoadCount() != null) {
            sql.SET("downLoadCount = #{downLoadCount,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionCount() != null) {
            sql.SET("collectionCount = #{collectionCount,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fileupload
     *
     * @mbggenerated Sat Apr 14 19:23:48 CST 2018
     */
    protected void applyWhere(SQL sql, FileUploadExample example, boolean includeExamplePhrase) {
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