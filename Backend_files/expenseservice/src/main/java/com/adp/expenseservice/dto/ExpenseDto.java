package com.adp.expenseservice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseDto {
    
    
    Integer expenseId;
    
    String empId;
    String managerId;
    String category;
    Double amount;
    String recipt;
    String status;
    String usercomment;
    String managercomment;
    LocalDate date;
	public Integer getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public String getManagercomment() {
		return managercomment;
	}
	public void setManagercomment(String managercomment) {
		this.managercomment = managercomment;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    
        
    
}