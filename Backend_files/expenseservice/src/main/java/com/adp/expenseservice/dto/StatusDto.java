package com.adp.expenseservice.dto;

import lombok.Data;

@Data
public class StatusDto {
	private Integer applicationId;
    private String managerId;
    private String managercomment;
    private String status;
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagercomment() {
		return managercomment;
	}
	public void setManagercomment(String managercomment) {
		this.managercomment = managercomment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
