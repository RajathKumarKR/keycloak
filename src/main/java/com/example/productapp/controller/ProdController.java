package com.example.productapp.controller;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.productapp.service.ProductService;

@Controller
public class ProdController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/products")
	public String getProducts(Model model){
		ResponseEntity<String[]> result= productService.getCustomerInfo();
		if(result==null) {
			model.addAttribute("products",Arrays.asList("Nothing to show. Your not authorized"));
			return "products";
		}
		else {
			model.addAttribute("products",result.getBody());
			return "products";
		}

	}

	@GetMapping(path = "/submodules")
	public String getModules(Model model, @RequestParam("customer") String customer){
		System.out.println("Customer name "+customer);
		ResponseEntity<String[]> result= productService.getItems();
		if(result==null) {
			model.addAttribute("items",Arrays.asList("Nothing to show. Your not authorized"));
			return "items";
		}
		else
		{
			model.addAttribute("items",result.getBody());
			return "items";	
		}

	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "/";
	}

}


