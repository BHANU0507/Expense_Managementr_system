package com.adp.expenseservice.dto;

import lombok.Data;

@Data
public class LineGraphDto 
{
	 String month;
     Double amount;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
     

}
