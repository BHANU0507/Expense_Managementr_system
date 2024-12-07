package com.adp.expenseservice.dto;

import lombok.Data;

@Data
public class EmployeeDto {
	private String empId;
	private String empName;
	private String empCountry;
	private String empPosition;
	private String empDOB;
	private String empEmail;
	private String mgnId;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCountry() {
		return empCountry;
	}
	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	public String getEmpDOB() {
		return empDOB;
	}
	public void setEmpDOB(String empDOB) {
		this.empDOB = empDOB;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getMgnId() {
		return mgnId;
	}
	public void setMgnId(String mgnId) {
		this.mgnId = mgnId;
	}
	
}
