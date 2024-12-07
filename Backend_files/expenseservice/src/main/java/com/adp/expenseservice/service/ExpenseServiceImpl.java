package com.adp.expenseservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import com.adp.expenseservice.utils.AddException;
import com.adp.expenseservice.utils.ChartException;
import com.adp.expenseservice.utils.EmployeeException;
import com.adp.expenseservice.utils.ExpenseException;

import jakarta.validation.constraints.NotBlank;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseRepository expenseRepo;
	
	@Autowired
	EmployeeService empService;
	
	private static final String UPLOAD_DIR= "src/main/resources/static";

	public String addExpense(ApplyFormDto applyForm) throws AddException {

		ExpenseEntity expenseEntity = new ExpenseEntity();
		expenseEntity.setEmpId(applyForm.getEmpId());
		expenseEntity.setManagerId(applyForm.getManagerId());
		expenseEntity.setCategory(applyForm.getCategory());
		expenseEntity.setAmount(applyForm.getAmount());
		expenseEntity.setRecipt(applyForm.getRecipt());
		expenseEntity.setUsercomment(applyForm.getUsercomment());
		expenseEntity.setDate(LocalDate.now());
		expenseEntity.setStatus("Pending");
		if(expenseEntity.getEmpId()==null ||expenseEntity.getAmount()==null || expenseEntity.getCategory()==null || expenseEntity.getDate()==null || expenseEntity.getManagerId()==null || expenseEntity.getDate()==null)
        {
        	throw new AddException("Failed to add expense");
        }
		ExpenseEntity expenses = expenseRepo.saveAndFlush(expenseEntity);
		return "Added Successfully Id = " + expenses.getExpenseId();
	}

	public List<ExpenseDto> allExpenses() throws ExpenseException {

		Iterable<ExpenseEntity> allexpenses = expenseRepo.findAll();
		List<ExpenseDto> list = new ArrayList<>();

		allexpenses.forEach(expense -> {

			ExpenseDto expenseDto = new ExpenseDto();

			expenseDto.setExpenseId(expense.getExpenseId());
			expenseDto.setAmount(expense.getAmount());
			expenseDto.setCategory(expense.getCategory());
			expenseDto.setDate(expense.getDate());
			expenseDto.setEmpId(expense.getEmpId());
			expenseDto.setManagercomment(expense.getManagercomment());
			expenseDto.setStatus(expense.getStatus());
			expenseDto.setUsercomment(expense.getUsercomment());
			expenseDto.setManagerId(expense.getManagerId());

			list.add(expenseDto);

		});
		
		if(list.size()<0)
        {
        	throw new ExpenseException("Expense file could not get added");
        }

		return list;

	}

	public String setStatus(StatusDto statusForm) throws EmployeeException {

		ExpenseEntity expenseEntity = expenseRepo.findById(statusForm.getApplicationId()).orElseThrow();

		expenseEntity.setManagercomment(statusForm.getManagercomment());
		
		
		if (expenseEntity.getStatus().toLowerCase().equals("pending")) {
			expenseEntity.setStatus(statusForm.getStatus());
			ExpenseEntity expenses = expenseRepo.saveAndFlush(expenseEntity);

		}
		
		if(expenseEntity.getStatus()==null)
		{
			throw new EmployeeException("Status failed to get updated");
		}

		return statusForm.getStatus() + " " + statusForm.getApplicationId();
	}
	
	public List<LineGraphDto> linegraph(String id) throws ChartException {
		List<LineGraphDto> data = new ArrayList<>();
		List<String> months = Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		for(int i=1;i<=12;i++) {
			LineGraphDto linegraph = new LineGraphDto();
			
			if(expenseRepo.linegraph(id,2024,i) == null) {
				linegraph.setAmount(0.00);
			}
			else {
				linegraph.setAmount(expenseRepo.linegraph(id,2024,i));
			}
			
			
			linegraph.setMonth(months.get(i-1));
			
			data.add(linegraph);
		}
		
		 if(data.size()<0)
	        {
	        	throw new ChartException("LineGraph could not get displayed");
	        }
		 
		return data;
	}

	@Override
	public List<EmployeeHistoryDto> employeeHistory(String empId) throws EmployeeException {
		// TODO Auto-generated method stub		
		if(expenseRepo.employeeHistory(empId)==null)
		{
			throw new EmployeeException("Employee history could not get fetched");
		}
		return expenseRepo.employeeHistory(empId);
	}
	
	
	
	
	public String addExpensefile(UploadfileDto applyForm) throws ExpenseException {

        ExpenseEntity expenseEntity = new ExpenseEntity();
        
        expenseEntity.setEmpId(applyForm.getEmpId());
        
        expenseEntity.setManagerId(applyForm.getManagerId());
        
        expenseEntity.setCategory(applyForm.getCategory());
        
        expenseEntity.setAmount(applyForm.getAmount());
        
        Random random =new Random();
        try {
            
            String fileName = applyForm.getRecipt().getOriginalFilename();
            Path directoryPath =Paths.get(UPLOAD_DIR);
            if(!Files.exists(directoryPath)){
                Files.createDirectories(directoryPath);
            }
            
            String filePath= Paths.get(UPLOAD_DIR,fileName).toString();
            
           // String projectRootPath =  System.getProperty("user.dir");
            
           // String absoluteFilePath = Paths.get(projectRootPath,filePath).toString();
            
            
           // Files.copy(applyForm.getRecipt().getInputStream(),Paths.get(absoluteFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(applyForm.getRecipt().getInputStream(),Paths.get(filePath));
            Map<String,String> result  = new HashMap<>();
            result.put("fileName", fileName);
            result.put("filePath", filePath);
            expenseEntity.setRecipt(fileName);
        }
        catch(IOException e){
        	
        	
              throw new ExpenseException("upload failed change file name (File name alredy exists)  ");
              }
            
        expenseEntity.setUsercomment(applyForm.getUsercomment());
        expenseEntity.setDate(LocalDate.now());
        expenseEntity.setStatus("Pending");

        ExpenseEntity expenses = expenseRepo.saveAndFlush(expenseEntity);
        if(expenses==null || expenseEntity==null)
        {
        	throw new ExpenseException("Expense file could not get added");
        }
        return "Added Successfully Id = " + expenses.getExpenseId();
    }
	
	
	
	
	
	public ExpenseDetailsEmployeeDto employeeexpensedetails(String empId,Integer expenseid) throws ExpenseException{
		ResponseEntity<EmployeeDto> emp = empService.getEmpId(empId);
		
		ExpenseEntity expense = expenseRepo.findById(expenseid).orElseThrow();
		ExpenseDetailsEmployeeDto expenseempdto = new ExpenseDetailsEmployeeDto();
		
		expenseempdto.setAmount(expense.getAmount());
		expenseempdto.setCategory(expense.getCategory());
		expenseempdto.setDate(expense.getDate());
		expenseempdto.setEmpCountry(emp.getBody().getEmpCountry());
		expenseempdto.setEmpEmail(emp.getBody().getEmpEmail());
		expenseempdto.setEmpId(empId);
		expenseempdto.setEmpName(emp.getBody().getEmpName());
		expenseempdto.setEmpPosition(emp.getBody().getEmpPosition());
		expenseempdto.setExpenseId(expenseid);
		expenseempdto.setManagercomment(expense.getManagercomment());
		expenseempdto.setRecipt(expense.getRecipt());
		expenseempdto.setStatus(expense.getStatus());
		expenseempdto.setUsercomment(expense.getUsercomment());
		expenseempdto.setManagerId(expense.getManagerId());
		if(expenseempdto.getEmpId()==null)
        {
        	throw new ExpenseException("EmployeeExpense details not found");
        }
		return expenseempdto;
		
	}
	
	//manager want to see only pending expenses of employees under him
 public List<ExpenseDetailsEmployeeDto> employeesUnderManger(String mngId) throws ExpenseException {

		Iterable<ExpenseEntity> expensesUnderManager = expenseRepo.employeesUnderManger(mngId);

		List<ExpenseDetailsEmployeeDto> list = new ArrayList<>();

		expensesUnderManager.forEach(expense -> {

		ExpenseDetailsEmployeeDto expenseDetailsEmployeeDto = new ExpenseDetailsEmployeeDto();

		expenseDetailsEmployeeDto.setExpenseId(expense.getExpenseId());
		expenseDetailsEmployeeDto.setAmount(expense.getAmount());
		expenseDetailsEmployeeDto.setCategory(expense.getCategory());
		expenseDetailsEmployeeDto.setDate(expense.getDate());
		expenseDetailsEmployeeDto.setEmpId(expense.getEmpId());
		expenseDetailsEmployeeDto.setManagercomment(expense.getManagercomment());
		expenseDetailsEmployeeDto.setStatus(expense.getStatus());
		expenseDetailsEmployeeDto.setUsercomment(expense.getUsercomment());
		expenseDetailsEmployeeDto.setManagerId(expense.getManagerId());



		ResponseEntity<EmployeeDto> emp = empService.getEmpId(expense.getEmpId());

		expenseDetailsEmployeeDto.setEmpName(emp.getBody().getEmpName());
		expenseDetailsEmployeeDto.setEmpPosition(emp.getBody().getEmpPosition());
		expenseDetailsEmployeeDto.setEmpId(emp.getBody().getEmpId());

		list.add(expenseDetailsEmployeeDto);

		});
		
		 try {
			if(list.size()<0)
			    {
			    	throw new ExpenseException("EmployeeExpense could not get added");
			    }
		} catch (ExpenseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

		}
    
    //manager want to see all expenses employees under him
    public List<ExpenseDto> ExpensesUnderManger(String mngId) throws ExpenseException {

        Iterable<ExpenseEntity> expensesUnderManager = expenseRepo.ExpensesUnderManger(mngId);
        
        List<ExpenseDto> list = new ArrayList<>();

        expensesUnderManager.forEach(expense -> {

            ExpenseDto expenseDto = new ExpenseDto();

            expenseDto.setExpenseId(expense.getExpenseId());
            expenseDto.setAmount(expense.getAmount());
            expenseDto.setCategory(expense.getCategory());
            expenseDto.setDate(expense.getDate());
            expenseDto.setEmpId(expense.getEmpId());
            expenseDto.setManagercomment(expense.getManagercomment());
            expenseDto.setStatus(expense.getStatus());
            expenseDto.setUsercomment(expense.getUsercomment());
            expenseDto.setManagerId(expense.getManagerId());

            list.add(expenseDto);

        });
        
        if(list.size()<0)
        {
        	throw new ExpenseException("ManagerExpense could not get added");
        }

        return list;
        


    }
	@Override
	public List<ManagerPieChartOutputDto> managerPieChart(
			@NotBlank(message = "Manager ID is required") String managerId,
			@NotBlank(message = "Date is required") LocalDate currentdate) {
		return expenseRepo.managerPieChart(managerId, currentdate);
	}

	@Override
	public List<PieChartDto> piechart(@NotBlank(message = "Employee ID is required") String empId, int i, int j) {

		return expenseRepo.piechart(empId, i);
	}

	@Override
	public List<categoryDto> catogery(@NotBlank(message = "Employee ID is required") String empId,
			@NotBlank(message = "Date is required") LocalDate currentdate) {
		return expenseRepo.catogery(empId, currentdate);
	}

}
