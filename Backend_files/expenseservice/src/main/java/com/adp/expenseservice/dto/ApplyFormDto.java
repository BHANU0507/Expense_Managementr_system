package com.adp.expenseservice.dto;

import lombok.Data;

@Data
public class ApplyFormDto 
{
    
    private String empId;
    private String managerId;
    private String category;
    private Double amount;
    private String recipt;
    private String usercomment;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRecipt() {
		return recipt;
	}
	public void setRecipt(String recipt) {
		this.recipt = recipt;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
    
}
