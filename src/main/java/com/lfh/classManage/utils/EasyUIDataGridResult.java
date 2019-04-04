package com.lfh.classManage.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类   返回结果
 * 简述部分:
 * 
 * @author lfh
 * @version 2019年3月12日
 */
@SuppressWarnings("rawtypes")
public class EasyUIDataGridResult implements Serializable{

	private static final long serialVersionUID = 1L;
	private long total;
	
	private List rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
