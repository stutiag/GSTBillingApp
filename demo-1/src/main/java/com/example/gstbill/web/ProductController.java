package com.example.gstbill.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gstbill.Exception.RecordNotFoundException;
import com.example.gstbill.model.ProductEntity;
import com.example.gstbill.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {

		@Autowired
		ProductService service;
		
		@RequestMapping()
		public String home()
		{
			return "home";
		}

		@RequestMapping("products")
		public String getAllProducts(Model model) 
		{
			List<ProductEntity> list = service.getAllProducts();

			model.addAttribute("products", list);
			return "list-products";
		}

		@RequestMapping(path = {"/edit", "/edit/{id}"})
		public String editProductById(Model model, @PathVariable("id") Optional<Long> id) 
								throws RecordNotFoundException 
		{
			if (id.isPresent()) {
				ProductEntity entity = service.getProductById(id.get());
				model.addAttribute("product", entity);
			} else {
				model.addAttribute("product", new ProductEntity());
			}
			return "add-edit-product";
		}
		
		@RequestMapping(path = "/delete/{id}")
		public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
								throws RecordNotFoundException 
		{
			service.deleteProductById(id);
			return "redirect:/";
		}

		@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
		public String createOrUpdateEmployee(ProductEntity product) 
		{
			service.createOrUpdateProduct(product);
			return "redirect:/";
		}
		
		@RequestMapping("/createbill")
		public String createbill()
		{
			return "billing";

		}
		List<ProductEntity>list=new ArrayList<ProductEntity>();
		@RequestMapping("/searchProduct/code")
		public String getProductById(Model model, @RequestParam Optional<Long> search) throws RecordNotFoundException 
		{
			ProductEntity entity = service.getProductById(search.get());
			list.add(entity);
			model.addAttribute("search",list);
	        return "billing";
		}
		
		@RequestMapping("/searchProduct/name")
		public String getProductByName(Model model,@RequestParam Optional<String>search)
		{
			ProductEntity entity=service.getProductByName(search.get());
			list.add(entity);
			model.addAttribute("searches",list);
			return "billing";
			
		}
	
		}
		
