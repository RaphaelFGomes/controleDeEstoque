package com.raphael.springbootionic.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.Category;
import com.raphael.springbootionic.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Transactional(readOnly=true)	
	Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);

}
