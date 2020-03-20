package com.webshopback.repository;

import com.webshopback.model.entity.ProductEntity;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by fmeregildo.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, CrudRepository<ProductEntity, Integer> {

	@Query("SELECT c from ProductEntity p where p.id=:id")
	public Optional<ProductEntity> findById(@Param("id") Integer id);
}
