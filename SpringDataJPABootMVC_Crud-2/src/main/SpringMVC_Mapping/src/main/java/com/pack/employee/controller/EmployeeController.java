package com.pack.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.employee.exception.EmployeeNotFoundException;
import com.pack.employee.model.Employee;
import com.pack.employee.service.EmployeeService;

@Controller
public class EmployeeController {
 
	@Autowired
    EmployeeService employeeService;
	
	
	
	  @RequestMapping("/home") 
	  public String home() {
	  
	  return "index"; }
	 
	
	 @RequestMapping("/addUserForm")  
	 public String add(Model m)  
	 {  
	     m.addAttribute("emp", new  Employee());  
	     return "userForm";  
	 }
		
	 

	  @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	  public  String addStudent(Employee employee) { 
		   employeeService.saveEmployee(employee);
		   return "redirect:/viewForm";
	 
	  
	  }
	  
	  @RequestMapping("/viewForm") 
	  public String viewemp(Model m){ 
		  List<Employee> list=employeeService.viewAll();
		  m.addAttribute("list",list);
		  return "view"; 
		  }
	  
	  
			
			  @RequestMapping("/editEmp") 
			  public String edit( @RequestParam("id") int  id,Model m,Employee emp)
			  {
			  
				  String page=null;
			 
			  try
			  {
			  if (employeeService.getEmployeeById(id).isPresent())
				 {
					  emp=employeeService.getEmployeeById(id).get();
					  m.addAttribute("editEmpForm",emp);
					  page="editAction";
					 
				 }
				 else if (employeeService.getEmployeeById(id).isEmpty())
				 {
					 
				  throw new EmployeeNotFoundException();
				   
					  }
					}
					  catch(EmployeeNotFoundException e)
					  {
						  m.addAttribute("exception",e);
					    	page="ExceptionPage";
						  
					  }

			return page;
			
			  
			 
			  
			  }
			 
			  
			  @RequestMapping("/editEmployee") 
			  public String modify(Employee employee) {
			   employeeService.saveEmployee(employee);
			   return "redirect:/viewForm";
			  
			  }
			  
			  
			  
			  @RequestMapping(value="/deleteEmp/{id}", method = RequestMethod.GET)
			  public String   delete(@PathVariable int id,Employee employee,Model m) {
				  
				  String page=null; 
				  try {
					  if (employeeService.getEmployeeById(id).isPresent())
						 { 
				  employee=employeeService.getEmployeeById(id).get();
				  employeeService.deleteEmployee(employee);
				  page="redirect:/viewForm"; 
				  } 
				else if   (employeeService.getEmployeeById(id).isEmpty()) {
				  System.out.println("emp "+employee);
				  throw new EmployeeNotFoundException();
				  
				  } 
					  }
				  catch(EmployeeNotFoundException e)
				  {
				  m.addAttribute("exception",e);
				  page="ExceptionPage";
				  
				  }
				  
				  return page;
			  }
			  
			  
			  @RequestMapping("/findQuery") 
			  public String find(Model m){
				  String res=null;
				List<Object[]> list= employeeService.find();
				 for (Object[] obj:list)
				 
					  res=obj[0]+"  "+obj[1]; 
				 
				m.addAttribute("res",res);
				return "queryForm";
				  }
}
