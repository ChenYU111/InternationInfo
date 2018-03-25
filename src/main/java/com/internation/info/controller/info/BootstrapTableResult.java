package com.internation.info.controller.info;

import java.util.List;

public class BootstrapTableResult<T> {
	public int total;      //总记录数  
    public List<T> rows;   //数据列表  
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
}  

