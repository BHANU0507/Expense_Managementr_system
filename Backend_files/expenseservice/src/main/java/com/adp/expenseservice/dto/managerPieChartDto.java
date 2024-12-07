package com.adp.expenseservice.dto;

import java.time.LocalDate;

public class managerPieChartDto
{
     String managerId;
     LocalDate date;
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
     
     
     
}
