package com.adp.expenseservice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adp.expenseservice.configurations.EmployeeService;
import com.adp.expenseservice.dto.ApplyFormDto;
import com.adp.expenseservice.dto.EmployeeDto;
import com.adp.expenseservice.dto.EmployeeHistoryDto;
import com.adp.expenseservice.dto.ExpenseDetailsEmployeeDto;
import com.adp.expenseservice.dto.ExpenseDto;
import com.adp.expenseservice.dto.LineGraphDto;
import com.adp.expenseservice.dto.ManagerPieChartOutputDto;
import com.adp.expenseservice.dto.PieChartDto;
import com.adp.expenseservice.dto.StatusDto;
import com.adp.expenseservice.dto.UploadfileDto;
import com.adp.expenseservice.dto.categoryDto;
import com.adp.expenseservice.entity.ExpenseEntity;
import com.adp.expenseservice.repository.ExpenseRepository;
import com.adp.expenseservice.service.ExpenseService;
import com.adp.expenseservice.utils.AddException;
import com.adp.expenseservice.utils.ChartException;
import com.adp.expenseservice.utils.EmployeeException;
import com.adp.expenseservice.utils.ExpenseException;

@CrossOrigin(origins = {"http://localhost:3000/","https://ephemeral-choux-4b34d5.netlify.app/"})
@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	EmployeeService empservice;
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String id) throws Exception{
		return empservice.getEmpId(id);
	}
	@Autowired
	ExpenseService expenseService;

	
	@RequestMapping(value = "/addExpensewithfile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    //@PostMapping("/addExpensewithfile")
    public @ResponseBody ResponseEntity<String> addExpensefile(@ModelAttribute UploadfileDto file1) throws ExpenseException {
        System.out.println();
        String res = expenseService.addExpensefile(file1);
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

	@PostMapping("/addExpense")
	public ResponseEntity<String> addExpense(@RequestBody ApplyFormDto applyForm) throws AddException {
		String res = expenseService.addExpense(applyForm);
		return new ResponseEntity<String>(res, HttpStatus.CREATED);
	}

	@GetMapping("/expenses")
	public ResponseEntity<List<ExpenseDto>> allExpenses() throws ExpenseException {

		return new ResponseEntity<List<ExpenseDto>>(expenseService.allExpenses(), HttpStatus.OK);
	}

	@PostMapping("/status")
	public ResponseEntity<String> setStatus(@RequestBody StatusDto statusForm ) throws EmployeeException {

		return new ResponseEntity<String>(expenseService.setStatus(statusForm), HttpStatus.OK);
	}
	
	@GetMapping("/managerpiechart")
	public ResponseEntity<List<ManagerPieChartOutputDto>> managerpiechart(@RequestParam String managerId,@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate currentdate) {
		return new ResponseEntity<List<ManagerPieChartOutputDto>>(expenseService.managerPieChart(managerId,currentdate),HttpStatus.OK);
	}
	
	@GetMapping("/lineGraph")
	public ResponseEntity<List<LineGraphDto>> linegraph(@RequestParam String empId) throws ChartException {
		return new ResponseEntity<List<LineGraphDto>>(expenseService.linegraph(empId),HttpStatus.OK);
	}
	
	@GetMapping("/pieChart")
	public ResponseEntity<List<PieChartDto>> empcatogerypiechart(@RequestParam String empId) {
        return new ResponseEntity<List<PieChartDto>>(expenseService.piechart(empId,2024,10),HttpStatus.OK);
    }
	
	@GetMapping("/empHistory")
	public ResponseEntity<List<EmployeeHistoryDto>> employeeHistory(@RequestParam String empId) throws EmployeeException {
        return new ResponseEntity<List<EmployeeHistoryDto>>(expenseService.employeeHistory(empId),HttpStatus.OK);
    }
	
	@GetMapping("/detailsemployee")
	public ResponseEntity<ExpenseDetailsEmployeeDto> employeeexpensedetails(@RequestParam String empId,@RequestParam Integer expenseId) throws ExpenseException {
        return new ResponseEntity<ExpenseDetailsEmployeeDto>(expenseService.employeeexpensedetails(empId,expenseId),HttpStatus.OK);
    }
	
	@GetMapping("/empcatogerytable")
    public ResponseEntity<List<categoryDto>> empcatogerytable(@RequestParam String empId,@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate currentdate) {
        return new ResponseEntity<List<categoryDto>>(expenseService.catogery(empId,currentdate),HttpStatus.OK);
    }
    
	 @GetMapping("/employeesUnderManger")
	    public ResponseEntity<List<ExpenseDetailsEmployeeDto>> empcatogerytable(@RequestParam String mngId) throws ExpenseException {
	        return new ResponseEntity<List<ExpenseDetailsEmployeeDto>>(expenseService.employeesUnderManger(mngId),HttpStatus.OK);
	    }
    
    @GetMapping("/expensesUnderManger")
    public ResponseEntity<List<ExpenseDto>> ExpensesUnderManger(@RequestParam String mngId) throws ExpenseException {
        return new ResponseEntity<List<ExpenseDto>>(expenseService.ExpensesUnderManger(mngId),HttpStatus.OK);
    }
	
}
