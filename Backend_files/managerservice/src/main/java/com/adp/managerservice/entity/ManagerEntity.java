package com.adp.managerservice.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class ManagerEntity {

	@Id
	private String mgnId;
	private String mgnName;
	private String empIds;
	
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
	public String getEmpIds() {
		return empIds;
	}
	public void setEmpIds(String empIds) {
		this.empIds = empIds;
	}

}
