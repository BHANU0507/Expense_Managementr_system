package com.adp.expenseservice.dto;

public class CountDto 
{
      Double amountSpend;
      Double totalAmount;
      Double remainingAmount;
	public Double getAmountSpend() {
		return amountSpend;
	}
	public void setAmountSpend(Double amountSpend) {
		this.amountSpend = amountSpend;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
}
