package com.internation.info.common;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class TableData {
	    private long totalCount;  
	      
	    private JSONArray rows;  
	      
	    public TableData(){  
	        rows = new JSONArray();  
	    }  
	      
	    public void setTotalCount(long totalCount){  
	        this.totalCount= totalCount;  
	    }  
	      
	    public void addRows(List<?> list){  
	        rows.addAll(list);  
	    }  
	      
	    public JSONObject toJson(){  
	        JSONObject json = new JSONObject();  
	        json.put("total", totalCount);
	        json.put("rows", rows);
	        return json;
	    }  
	  
}
