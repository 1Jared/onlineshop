package com.jaredfwaya.onlineshop.controller;

import javax.validation.Valid;

import com.jaredfwaya.onlineshop.model.Customer;
import com.jaredfwaya.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/register")
	public ModelAndView register(Customer customer){
		ModelAndView mv = new ModelAndView("customer/register");
		mv.addObject("customer", customer); 
		return mv;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView addCustomer(@Valid Customer customer, BindingResult result, Model model){
		
		if(result.hasErrors()){
			return register(customer);
		}
		
		customerService.saveCustomer(customer);
		return new ModelAndView("/customer/registerSuccess");
	}
}
