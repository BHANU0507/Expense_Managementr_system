package com.adp.managerservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class ManagerDto {
	private String mgnId;
	private String mgnName;
	private List<String> empIds;
	
	public String getMgnId() {
		return mgnId;
	}
	public void setMgnId(String mgnId) {
		this.mgnId = mgnId;
	}
	public String getMgnName() {
		return mgnName;
	}
	public void setMgnName(String mgnName) {
		this.mgnName = mgnName;
	}
	public List<String> getEmpIds() {
		return empIds;
	}
	public void setEmpIds(List<String> empIds) {
		this.empIds = empIds;
	}
}