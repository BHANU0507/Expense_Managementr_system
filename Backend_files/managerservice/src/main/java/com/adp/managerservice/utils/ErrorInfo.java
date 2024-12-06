package com.adp.managerservice.utils;


import java.time.LocalDateTime;

public class ErrorInfo {
	 String error;
	    LocalDateTime date;
	    Integer status;
	    public String getError() {
	        return error;
	    }
	    public void setError(String error) {
	        this.error = error;
	    }
	    public LocalDateTime getDate() {
	        return date;
	    }
	    public void setDate(LocalDateTime date) {
	        this.date = date;
	    }
	    public Integer getStatus() {
	        return status;
	    }
	    public void setStatus(Integer status) {
	        this.status = status;
	    }
	    public ErrorInfo() {
	        super();
	    }
	    public ErrorInfo(String error, LocalDateTime date, Integer status) {
	        super();
	        this.error = error;
	        this.date = date;
	        this.status = status;
	    }
}
