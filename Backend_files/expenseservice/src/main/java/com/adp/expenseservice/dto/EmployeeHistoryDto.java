package com.adp.expenseservice.dto;

import java.time.LocalDate;




public interface EmployeeHistoryDto {
	
	Integer getExpense_id();
    String getCategory();
    Double getAmount();
    String getRecipt();
    String getStatus();
    String getUserComment();
    String getManagerComment();
    LocalDate getDate();
    
}
