package com.adp.expenseservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.adp.expenseservice.dto.ApplyFormDto;
import com.adp.expenseservice.dto.EmployeeHistoryDto;
import com.adp.expenseservice.dto.ExpenseDetailsEmployeeDto;
import com.adp.expenseservice.dto.ExpenseDto;
import com.adp.expenseservice.dto.LineGraphDto;
import com.adp.expenseservice.dto.ManagerPieChartOutputDto;
import com.adp.expenseservice.dto.PieChartDto;
import com.adp.expenseservice.dto.StatusDto;
import com.adp.expenseservice.dto.UploadfileDto;
import com.adp.expenseservice.dto.categoryDto;
import com.adp.expenseservice.utils.AddException;
import com.adp.expenseservice.utils.ChartException;
import com.adp.expenseservice.utils.EmployeeException;
import com.adp.expenseservice.utils.ExpenseException;

import jakarta.validation.constraints.NotBlank;

public interface ExpenseService {
	public String addExpense(ApplyFormDto applyForm) throws AddException;
	public List<ExpenseDto>allExpenses() throws ExpenseException;
	public String setStatus(StatusDto statusForm) throws EmployeeException;
	public List<LineGraphDto> linegraph(String id) throws ChartException;
	public List<EmployeeHistoryDto> employeeHistory(String empId) throws EmployeeException;
	public ExpenseDetailsEmployeeDto employeeexpensedetails(String empId,Integer expenseid) throws ExpenseException;
	public List<ExpenseDetailsEmployeeDto> employeesUnderManger(String mngId) throws ExpenseException;
    public List<ExpenseDto> ExpensesUnderManger(String mngId) throws ExpenseException;
    public String addExpensefile(UploadfileDto applyForm) throws ExpenseException;
    public List<ManagerPieChartOutputDto> managerPieChart(
			@NotBlank(message = "Manager ID is required") String managerId,
			@NotBlank(message = "Date is required") LocalDate currentdate);
	public List<PieChartDto> piechart(@NotBlank(message = "Employee ID is required") String empId, int i, int j);
	public List<categoryDto> catogery(@NotBlank(message = "Employee ID is required") String empId,
			@NotBlank(message = "Date is required") LocalDate currentdate);
}
