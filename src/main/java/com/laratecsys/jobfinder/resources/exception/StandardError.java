package com.laratecsys.jobfinder.resources.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Long timestamp;
	
	
	public StandardError() {
		
	}

	public StandardError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	

}
