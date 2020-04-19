package com.example.gstbill.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.gstbill.model.ProductEntity;
import com.example.gstbill.service.ProductService;



@RestController
@RequestMapping("/api")
public class BillController {

		@Autowired
		private ProductService productService;

		
		@RequestMapping(value = "product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<ProductEntity> getAllProducts() {
			return productService.getAllProducts();
		}
		
	}
