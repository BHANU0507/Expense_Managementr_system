

package com.adp.expenseservice.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class UploadfileDto 
{
        private String empId;
        private String managerId;
        private String category;
        private Double amount;
        private MultipartFile recipt;
        private String usercomment;
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getManagerId() {
			return managerId;
		}
		public void setManagerId(String managerId) {
			this.managerId = managerId;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public MultipartFile getRecipt() {
			return recipt;
		}
		public void setRecipt(MultipartFile recipt) {
			this.recipt = recipt;
		}
		public String getUsercomment() {
			return usercomment;
		}
		public void setUsercomment(String usercomment) {
			this.usercomment = usercomment;
		}
        
}
