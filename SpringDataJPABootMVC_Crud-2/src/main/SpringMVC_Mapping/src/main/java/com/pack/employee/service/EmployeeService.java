package com.pack.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.employee.model.Address;
import com.pack.employee.model.Employee;
import com.pack.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	
	@Autowired
	EmployeeRepository employeeRepository;
	
	 
	public void saveEmployee(Employee employee) {
		Address address1=new Address();
		address1.setStreet("OTR Road");
		address1.setCity("Chennai");
		address1.setState("Tamil Nadu");
		address1.setZipcode("60087");
		employee.setEmployeeAddress(address1); 
 	employeeRepository.save(employee);
	 
		
	}
	
	 public List<Employee> viewAll()
	 {
		 
		List<Employee> employees= employeeRepository.findAll();
		 
		
		return employees;
	 }
	 
		
		  public Optional<Employee> getEmployeeById(int id){
			  
		      return employeeRepository.findById(id); 
		  }
		  
		  public void deleteEmployee(Employee employee)
		  {
			  
			  employeeRepository.delete(employee);
		  }
		 
		  public List<Object[]> find()
		  {
			  
			 return employeeRepository.find();
		  }
		  
}
