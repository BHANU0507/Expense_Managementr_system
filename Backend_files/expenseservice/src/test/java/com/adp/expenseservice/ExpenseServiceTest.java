package com.adp.expenseservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

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
import com.adp.expenseservice.service.ExpenseServiceImpl;
import com.adp.expenseservice.utils.AddException;
import com.adp.expenseservice.utils.ChartException;
import com.adp.expenseservice.utils.EmployeeException;
import com.adp.expenseservice.utils.ExpenseException;

class ExpenseServiceTest {

	@InjectMocks
	private ExpenseServiceImpl expenseService;
	@Mock
	private ExpenseRepository expenseRepo;
	@Mock
	private EmployeeService employeeService;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testAddExpense() throws AddException {
		ApplyFormDto applyForm=new ApplyFormDto();
		applyForm.setEmpId("123");
		applyForm.setManagerId("456");
		applyForm.setCategory("Travel");
		applyForm.setAmount(500.0);
		applyForm.setRecipt("expense-recipt.jpg");
		applyForm.setUsercomment("Business trip");
		ExpenseEntity savedExpenseEntity=new ExpenseEntity();
		savedExpenseEntity.setManagerId(applyForm.getManagerId());
		savedExpenseEntity.setCategory(applyForm.getCategory());
		savedExpenseEntity.setAmount(applyForm.getAmount());
		savedExpenseEntity.setRecipt(applyForm.getRecipt());
		savedExpenseEntity.setUsercomment(applyForm.getUsercomment());
		savedExpenseEntity.setDate(LocalDate.now());
		savedExpenseEntity.setStatus("Pending");
		when(expenseRepo.saveAndFlush(any(ExpenseEntity.class))).thenReturn(savedExpenseEntity);
		String result=expenseService.addExpense(applyForm);
		verify(expenseRepo,times(1)).saveAndFlush(any(ExpenseEntity.class));
		assertTrue(result.contains("Added Successfully Id = " + savedExpenseEntity.getExpenseId()));
		
	}
	@Test
	public void testAllExpenses() throws ExpenseException {
		List<ExpenseEntity>sampleExpenseEntities=new ArrayList<>();
		ExpenseEntity expense1=new ExpenseEntity();
		expense1.setExpenseId(1);
		expense1.setEmpId("12");
		expense1.setManagerId("23");
		expense1.setCategory("Category 1");
		expense1.setUsercomment("user comment 1");
		expense1.setManagercomment("manager comment 1");
		expense1.setDate(LocalDate.now());
		expense1.setStatus("Pending");
		sampleExpenseEntities.add(expense1);
		when(expenseRepo.findAll()).thenReturn(sampleExpenseEntities);
		List<ExpenseDto>result=expenseService.allExpenses();
		assertEquals(sampleExpenseEntities.size(),result.size());
		ExpenseDto firstExpenseDto=result.get(0);
		assertEquals(expense1.getExpenseId(),firstExpenseDto.getExpenseId());
		assertEquals(expense1.getAmount(),firstExpenseDto.getAmount());
		assertEquals(expense1.getCategory(),firstExpenseDto.getCategory());
		assertEquals(expense1.getDate(),firstExpenseDto.getDate());
		assertEquals(expense1.getEmpId(),firstExpenseDto.getEmpId());
		assertEquals(expense1.getManagercomment(),firstExpenseDto.getManagercomment());
		assertEquals(expense1.getManagerId(),firstExpenseDto.getManagerId());
		assertEquals(expense1.getStatus(),firstExpenseDto.getStatus());
		}
	@Test
	public void testsetStatus() throws EmployeeException {
		StatusDto statusForm=new StatusDto();
		statusForm.setApplicationId(1);
		statusForm.setStatus("Approved");
		statusForm.setManagercomment("Manager comment");
		ExpenseEntity expectedExpense=new ExpenseEntity();
		expectedExpense.setStatus("Pending");
		when(expenseRepo.findById(statusForm.getApplicationId())).thenReturn(java.util.Optional.of(expectedExpense));
		String result=expenseService.setStatus(statusForm);
		assertEquals("Approved 1",result);
		assertEquals(statusForm.getStatus(),expectedExpense.getStatus());
		assertEquals(statusForm.getManagercomment(),expectedExpense.getManagercomment());
		verify(expenseRepo,times(1)).saveAndFlush(expectedExpense);
	}
	@Test
	public void testLineGraph() throws ChartException {
		String id="123";
		when(expenseRepo.linegraph(eq(id), eq(2023),eq(1))).thenReturn(100.0);
		List<LineGraphDto>result=expenseService.linegraph(id);
		assertEquals(12,result.size());
		List<String>expectedMonths=List.of("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		for(int i=0;i<12;i++) {
			LineGraphDto lineGraphDto=result.get(i);
			assertEquals(expectedMonths.get(i),lineGraphDto.getMonth());
			if(i==0) {
				assertEquals(100.0, lineGraphDto.getAmount());
			}else {
				assertEquals(0.0,lineGraphDto.getAmount());
			}
		}
	}
	@Test
	public void testEmployeeHistory() throws EmployeeException {
		String empId="123";
		List<EmployeeHistoryDto>expectedHistory=new ArrayList<>();
		when(expenseRepo.employeeHistory(eq(empId))).thenReturn(expectedHistory);
		List<EmployeeHistoryDto>result=expenseService.employeeHistory(empId);
		assertEquals(expectedHistory,result);
	}
	@Test
	public void testEmployeeExpenseDetails() throws ExpenseException {
		String empId="123";
		Integer expenseId=456;
		EmployeeDto sampleemployeeDto=new EmployeeDto();
		ResponseEntity<EmployeeDto>empRes=new ResponseEntity<>(sampleemployeeDto,HttpStatus.OK);
		when(employeeService.getEmpId(empId)).thenReturn(empRes);
		ExpenseEntity sampleExpenseEntity=new ExpenseEntity();
		sampleExpenseEntity.setAmount(100.0);
		sampleExpenseEntity.setCategory("category");
		sampleExpenseEntity.setDate(LocalDate.now());
		sampleExpenseEntity.setManagercomment("sample maanger ");
		sampleExpenseEntity.setRecipt("receipt");
		sampleExpenseEntity.setStatus("sample status");
		sampleExpenseEntity.setExpenseId(456);
		when(expenseRepo.findById(expenseId)).thenReturn(java.util.Optional.of(sampleExpenseEntity));
		ExpenseDetailsEmployeeDto result=expenseService.employeeexpensedetails(empId, expenseId);
		assertEquals(sampleExpenseEntity.getExpenseId(),expenseId);
		assertEquals(sampleExpenseEntity.getCategory(),result.getCategory());
		assertEquals(sampleExpenseEntity.getDate(),result.getDate());
	}
	@Test
	public void testEmployeesUnderManager() throws ExpenseException {
		String managerId="101";
		List<ExpenseEntity>mockExpenseEntities=new ArrayList<>();
		ExpenseEntity expense1=new ExpenseEntity();
		expense1.setExpenseId(1);
		mockExpenseEntities.add(expense1);
		when(expenseRepo.employeesUnderManger(managerId)).thenReturn(mockExpenseEntities);
		List<ExpenseDetailsEmployeeDto>result=expenseService.employeesUnderManger(managerId);
		assertEquals(1,result.size());
		ExpenseDetailsEmployeeDto expenseDto=result.get(0);
		assertEquals(1,expenseDto.getExpenseId());
		verify(expenseRepo).employeesUnderManger(managerId);
		
		
	}
	@Test
	public void testExpensesUnderManager() throws ExpenseException {
		String managerId="101";
		List<ExpenseEntity>mockExpenseEntities=new ArrayList<>();
		ExpenseEntity expense1=new ExpenseEntity();
		expense1.setExpenseId(1);
		mockExpenseEntities.add(expense1);
		when(expenseRepo.ExpensesUnderManger(managerId)).thenReturn(mockExpenseEntities);
		List<ExpenseDto>result=expenseService.ExpensesUnderManger(managerId);
		assertEquals(1,result.size());
		ExpenseDto expenseDto=result.get(0);
		assertEquals(1,expenseDto.getExpenseId());
		verify(expenseRepo).ExpensesUnderManger(managerId);
	}
	@Test
	public void testManagerPiechart() {
		String managerId="101";
		LocalDate currentdate=LocalDate.now();
		List<ManagerPieChartOutputDto>mockChartOutput=new ArrayList<>();
		ManagerPieChartOutputDto chartItem1=new ManagerPieChartOutputDto() {
		
			public String getStatusPie() {
				return "Approved";
			}
			@Override
			public Double getAmountPie() {
				return 100.0;
			}
		};
		mockChartOutput.add(chartItem1);
		when(expenseRepo.managerPieChart(managerId, currentdate)).thenReturn(mockChartOutput);
		List<ManagerPieChartOutputDto>result=expenseRepo.managerPieChart(managerId, currentdate);
		assertEquals(mockChartOutput.size(),result.size());
		ManagerPieChartOutputDto resultItem=result.get(0);
		assertEquals(chartItem1.getAmountPie(),resultItem.getAmountPie());
		assertEquals(chartItem1.getStatusPie(),resultItem.getStatusPie());
		verify(expenseRepo).managerPieChart(managerId, currentdate);
	}
	@Test
	public void testPieChart() {
		String empId="employee123";
		int j=42;
		List<PieChartDto>mockPieChartDtoList=new ArrayList<>();
		PieChartDto piechartDto1=new PieChartDto(){
			 public Double getAmount() {
				return 100.0;
			}
			@Override
			public String getCatogery() {
				return "category1";
			}
		};
		mockPieChartDtoList.add(piechartDto1);
		when(expenseRepo.piechart(empId, 1)).thenReturn(mockPieChartDtoList);
		List<PieChartDto>result=expenseService.piechart(empId,1, j);
		assertEquals(mockPieChartDtoList,result);
		verify(expenseRepo).piechart(empId, 1);	
	}
	@Test
	public void testCategory() {
		String empId="emp12";
		LocalDate currentDate=LocalDate.now();
		List<categoryDto>mockCategoryDtoList=new ArrayList<>();
		categoryDto categoryDto1=new categoryDto() {
			public String getCategory() {
				return "Category1";
			}
			@Override
			public Double getEmpCatogeryAmount() {
				return 100.0;
			}
		};
		when(expenseRepo.catogery(empId, currentDate)).thenReturn(mockCategoryDtoList);
		List<categoryDto>result=expenseService.catogery(empId, currentDate);
		assertEquals(mockCategoryDtoList,result);
		verify(expenseRepo).catogery(empId,currentDate);
	}
	@Test
	void testAddExpensefile() throws IOException {
		try {
			UploadfileDto applyForm=new UploadfileDto();

			applyForm.setEmpId("emp123");
			applyForm.setManagerId("manager123");
			applyForm.setCategory("Category1");
			applyForm.setAmount(100.0);
			applyForm.setUsercomment("Comment");
			MockMultipartFile mockFile=new MockMultipartFile("file","test1.txt","text/plain","Test Content".getBytes());
			applyForm.setRecipt(mockFile);
			String fileName="test1_"+System.currentTimeMillis() +".txt";
			String UPLOAD_DIR="src/main/resources/static";
			String filepath=Paths.get(UPLOAD_DIR,fileName).toString();
			System.out.println(filepath);
			while(Files.exists(Paths.get(filepath))) {
				fileName="test1_"+System.currentTimeMillis() +".txt";
				filepath=Paths.get(UPLOAD_DIR,fileName).toString();
			}
			Files.copy(applyForm.getRecipt().getInputStream(), Paths.get(filepath));
			when(expenseRepo.saveAndFlush(any(ExpenseEntity.class))).thenAnswer(invocation->{
				ExpenseEntity expenseEntity=invocation.getArgument(0);
				expenseEntity.setExpenseId(1);
				return expenseEntity;
			});
//			
			String result=expenseService.addExpensefile(applyForm);
			verify(expenseRepo).saveAndFlush(argThat(expenseEntity->
			"emp123".equals(expenseEntity.getEmpId())&&
			"manager123".equals(expenseEntity.getManagerId())&&
			"Category1".equals(expenseEntity.getCategory())&&
			"Comment".equals(expenseEntity.getUsercomment())
			//&& fileName.equals(expenseEntity.getRecipt())
			));
			 
		}catch(Exception e) {
			  assertNotNull(e.getMessage());
		        String expectedMessage = "upload failed change file name (File name alredy exists)  ";
		        String actualMessage = e.getMessage();

		        assertTrue(actualMessage.contains(expectedMessage));
		}
		
			//assertNotNull(result);
	}
}
