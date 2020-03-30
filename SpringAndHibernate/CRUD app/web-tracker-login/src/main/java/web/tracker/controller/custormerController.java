package web.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.tracker.dao.CustomerDAOIF;
import web.tracker.entity.Customer;
import web.tracker.service.customerServiceIF;

@Controller
@RequestMapping("/customer")
public class custormerController {
	
	@Autowired
	private customerServiceIF customerService;
	

	@RequestMapping("/list")
	public String listCustomer(Model model) {
		
		List<Customer> customers=customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		
		return "list-customers";
	}
	
	@GetMapping("/showFormToAdd")
	public String addForm(Model model) {
		
		Customer theCustomer=new Customer();
		
		model.addAttribute("customers", theCustomer);
		
		return "showFormToAdd";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customers")Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	private String updateCustomer(@RequestParam("customerId") int theId, Model model) {
		
		Customer customer=customerService.getCustomer(theId);
		
		model.addAttribute("customers", customer);
		
		
		return "customer-update-form";
	}
	
	@GetMapping("/delete")
	private String delete(@RequestParam("customerId")int theId) {
		
		customerService.deleteCustomer(theId);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/searchCustomer")
	private String searchCustomer(@RequestParam("theSearchParam")String theParam,Model model) {
		
		List <Customer> customers=customerService.searchCustomer(theParam);
		
		model.addAttribute("customers", customers);
		
		
		return "list-customers";
	}
	
}
