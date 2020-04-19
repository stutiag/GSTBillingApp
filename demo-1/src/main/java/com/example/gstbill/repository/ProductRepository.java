package com.example.gstbill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.gstbill.model.ProductEntity;


@Repository
public interface ProductRepository extends CrudRepository<	ProductEntity, Long> {
ProductEntity findByproductName(String name);
	}


