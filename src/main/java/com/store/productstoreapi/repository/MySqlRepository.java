package com.store.productstoreapi.repository;

import com.store.productstoreapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRepository extends JpaRepository<Product,Integer> {

}
