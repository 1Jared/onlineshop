package com.jaredfwaya.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
}
