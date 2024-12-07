package com.adp.expenseservice.repository;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adp.expenseservice.entity.ExpenseEntity;
import com.adp.expenseservice.repository.ExpenseRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExpenseRepositoryTest {
        
        private static final LocalDate localdate = null;

        @Autowired
        private ExpenseRepository expenseRepository;

        private ExpenseEntity expense;

       
        @Test
        @DisplayName("JUnit test for custom query for managerPieChart")
        public void givenManagerIdDate_managerPieChart() {

            //expenseRepository.save(expense);

            // When: Action or behavior that we are going to test
            List getExpense = expenseRepository.managerPieChart("GGGAV8HGSGVDCB31", localdate);

            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }

        @Test
        @DisplayName("JUnit test for custom query for linegraph ")
        public void givenEmployeIdYearMonth_Linegraph() {

            //expenseRepository.save(expense);

            // When: Action or behavior that we are going to test
            Double getExpense =   expenseRepository.linegraph("GGGAV8HGSGVDCB31",2023,9);
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }



        @Test
        @DisplayName("JUnit test for custom query for piechart  ")
        public void givenEmployeIdYearMonth_Piechart() {


            //expenseRepository.save(expense);

           
            List getExpense =   expenseRepository.piechart("GGGAV8HGSGVDCB31",2018);
           
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }

        
        
        @Test
        @DisplayName("JUnit test for custom query to get Employee History  ")
        public void employeeHistory() {


            //expenseRepository.save(expense);

           
            List getExpense =   expenseRepository.employeeHistory("E17NH5C0CBD8F2FG");
           
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }
        
        
        @Test
        @DisplayName("JUnit test for custom query to get catogery History  ")
        public void catogery() {


           // expenseRepository.save(expense);

           
            List getExpense =   expenseRepository.catogery("E17NH5C0CBD8F2FG", localdate);
           
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }
        
        
        
        @Test
        @DisplayName("JUnit test for custom query to get employeesUnderManger")
        public void employeesUnderManger() {

           // expenseRepository.save(expense);

           
            List getExpense =   expenseRepository.employeesUnderManger("E17NH5C0CBD8F2FG");
           
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }
        
        
        
        @Test
        @DisplayName("JUnit test for custom query to get ExpensesUnderManger")
        public void ExpensesUnderManger() {

            // expenseRepository.save(expense);

           
            List getExpense =   expenseRepository.ExpensesUnderManger("E1NS5WYJCKN5V0AK");
           
            
            // Then: Verify the output or expected result
            assertThat(getExpense).isNotNull();
        }
        
    }
