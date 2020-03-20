package com.webshopback.repository;

import com.webshopback.model.entity.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by fmeregildo.
 */

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	@Query("SELECT p from ProductEntity p where p.id=:id")
	public ProductEntity findById1(@Param("id") Integer id);
}
