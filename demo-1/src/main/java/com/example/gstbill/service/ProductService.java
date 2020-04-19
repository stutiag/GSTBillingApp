package com.example.gstbill.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gstbill.Exception.RecordNotFoundException;
import com.example.gstbill.model.ProductEntity;
import com.example.gstbill.repository.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public List<ProductEntity> getAllProducts()
	{
		List<ProductEntity> result = (List<ProductEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ProductEntity>();
		}
	}
	
	public ProductEntity getProductById(Long id) throws RecordNotFoundException 
	{
		Optional<ProductEntity> product = repository.findById(id);
		
		if(product.isPresent()) {
			return product.get();
		} else {
			throw new RecordNotFoundException("No  record exist for given id");
		}
	}
	public ProductEntity getProductByName(String name) 
	{
	ProductEntity product = repository.findByproductName(name);
	return product;
		
	}
	
	public ProductEntity createOrUpdateProduct(ProductEntity entity) 
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<ProductEntity> product = repository.findById(entity.getId());
			
			if(product.isPresent()) 
			{
				ProductEntity newEntity = product.get();
				newEntity.setGst(entity.getGst());
				newEntity.setProductName(entity.getProductName());
				newEntity.setPrice(entity.getPrice());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteProductById(Long id) throws RecordNotFoundException 
	{
		Optional<ProductEntity> product = repository.findById(id);
		
		if(product.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	} 
	public Optional<ProductEntity> findOne(Long product_code) {
		return  repository.findById(product_code) ;
	}
}