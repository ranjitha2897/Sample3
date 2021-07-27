package com.pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.exception.EmployeeNotFoundException;
import com.pack.model.Employee;
import com.pack.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("viewForm")
	public String viewemp(Model m) {
		List<Employee> list = employeeService.viewAll();
		m.addAttribute("list", list);
		return "view";
	}

	@RequestMapping("/addUserForm")//register
	public String add(Model m) {
		m.addAttribute("emp", new Employee());
		return "userForm";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)//employee
	public String addStudent(@ModelAttribute("emp") Employee employee) {
		employeeService.saveEmployee(employee);
		return "adduser-success";

	}

//	@RequestMapping(value = "/getId", method = RequestMethod.GET)
//	public String getId() {
//
//		return "getIdForm";
//	}

//	@RequestMapping(value = "/del", method = RequestMethod.GET)
//	public String delete(@RequestParam("id") int id, Employee employee, Model m) {

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id, Employee employee, Model m) {
		String page = null;
		try {
			if (employeeService.getEmployeeById(id).isPresent()) {
				employee = employeeService.getEmployeeById(id).get();
				employeeService.deleteEmployee(employee);
				page = "redirect:/viewForm";
			} else if (employeeService.getEmployeeById(id).isEmpty()) {
				System.out.println("emp " + employee);
				throw new EmployeeNotFoundException();

			}
		} catch (EmployeeNotFoundException e) {
			m.addAttribute("exception", e);
			page = "ExceptionPage";

		}

		return page;

	}

	@RequestMapping(value = "/update")
	public String update(@RequestParam int id, Model m) {

		Employee employee = null;
		String page = null;
		try {
			if (employeeService.getEmployeeById(id).isPresent()) {
				employee = employeeService.getEmployeeById(id).get();
				m.addAttribute("empUpdateBean", employee);
//				System.out.println("Hi");
				page = "updateForm";
			} else if (employeeService.getEmployeeById(id).isEmpty()) {
				System.out.println("emp " + employee);
				throw new EmployeeNotFoundException();

			}
		} catch (EmployeeNotFoundException e) {
			m.addAttribute("exception", e);
			page = "ExceptionPage";

		}

		return page;

	}

}
