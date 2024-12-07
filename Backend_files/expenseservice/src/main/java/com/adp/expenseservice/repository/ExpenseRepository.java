package com.adp.expenseservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adp.expenseservice.dto.EmployeeHistoryDto;
import com.adp.expenseservice.dto.LineGraphDto;
import com.adp.expenseservice.dto.ManagerPieChartOutputDto;
import com.adp.expenseservice.dto.PieChartDto;
import com.adp.expenseservice.dto.categoryDto;
import com.adp.expenseservice.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Integer>
{
	@Query(value = "SELECT SUM(amount) AS amountPie, status AS statusPie " +
            "FROM expense_entity " +
            "WHERE manager_id = :managerid " +
            "AND MONTH(date) = MONTH(:currentdate) " +
            "GROUP BY status", nativeQuery = true)
     public List<ManagerPieChartOutputDto> managerPieChart(@Param("managerid") String managerId,@Param("currentdate") LocalDate currentdate);
     
     //@Query(value = "select sum(\"amount\") from \"expense_entity\" where YEAR(\"date\")= :year AND MONTH(\"date\")= :month and \"emp_id\" = :empid ", nativeQuery=true)
	@Query(value = "select sum(amount) from expense_entity where YEAR(date)= :year AND MONTH(date)= :month and emp_id = :empid ", nativeQuery=true) 
	public Double linegraph(@Param("empid") String empId, @Param("year") Integer year, @Param("month") Integer month);

     
     //@Query(value = "select \"category\" as catogery, sum(\"amount\") as amount from \"expense_entity\" where \"emp_id\" = :empid and YEAR(\"date\")= :year group by \"category\" ", nativeQuery = true)
	@Query(value = "select category as catogery, sum(amount) as amount from expense_entity where emp_id = :empid and YEAR(date)= :year group by category ", nativeQuery = true)
	public List<PieChartDto> piechart(@Param("empid") String empId, @Param("year") Integer year);
     
     //@Query(value = "select * from \"expense_entity\" where \"emp_id\" = :empid",nativeQuery=true)
     @Query(value = "select * from expense_entity where emp_id = :empid",nativeQuery=true)
     public List<EmployeeHistoryDto> employeeHistory(@Param("empid") String empId);
     

     //@Query(value = "select sum(\"amount\") as empCatogeryamount,\"category\" as category from \"expense_entity\" where YEAR(\"date\") =  YEAR(:currentdate) and \"emp_id\" = :empid group by \"category\" ", nativeQuery=true)
     @Query(value = "select sum(amount) as empCatogeryamount,category as category from expense_entity where YEAR(date) =  YEAR(:currentdate) and emp_id = :empid group by category ", nativeQuery=true)
     public List<categoryDto> catogery(@Param("empid") String empId, @Param("currentdate") LocalDate currentdate);
     
     
     //@Query(value = "select  *  from \"expense_entity\" where \"manager_id\"= :mngid and \"status\"='Pending'  ", nativeQuery=true)
     @Query(value = "select  *  from expense_entity where manager_id= :mngid and status='Pending'  ", nativeQuery=true)
     public List<ExpenseEntity> employeesUnderManger(@Param("mngid") String mngId);
     
     //@Query(value = "select  *  from \"expense_entity\" where \"manager_id\"= :mngid ", nativeQuery=true)
     @Query(value = "SELECT * FROM expense_entity WHERE manager_id = :mngid", nativeQuery = true)
     public List<ExpenseEntity> ExpensesUnderManger(@Param("mngid") String mngId);
     
}
