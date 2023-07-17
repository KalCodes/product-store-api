package com.store.productstoreapi.controller;

import com.store.productstoreapi.model.Product;
import com.store.productstoreapi.repository.MySqlRepository;
import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    MySqlRepository mySqlRepository;

    @GetMapping("/api/v1/products")
    public List<Product> getAllProducts(){

        return mySqlRepository.findAll();
    }

    @GetMapping("/api/v1/products/{identity}")
    public Product getSingleProducts(@PathVariable("identity") Integer id){

        return mySqlRepository.findById(id).get();
    }

    @PostMapping("/api/v1/products")
    public Product createProduct(@RequestBody Map<String, String> body){

        String name = body.get("name");
        String model = body.get("model");
        Integer price = Integer.parseInt(body.get("price"));

        Product newProduct = new Product(name,model,price);

        return mySqlRepository.save(newProduct);
    }

    @PutMapping("/api/v1/products/{id}")
    public Product updateProduct(@PathVariable("id") Integer id,
                                 @RequestBody Map<String, String> body){
        Product current = mySqlRepository.findById(id).get();
        current.setName(body.get("name"));
        current.setModel(body.get("model"));
        current.setPrice(Integer.parseInt(body.get("price")));
        mySqlRepository.save(current);
        return current;
    }

    @PatchMapping("/api/v1/products/{id}")
    public Product updateProductByFields(@PathVariable int id,@RequestBody Map<String, Object> fields) {
        Optional<Product> existingProduct = mySqlRepository.findById(id);

        if (existingProduct.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return mySqlRepository.save(existingProduct.get());
        }
        return null;
    }

    @DeleteMapping("/api/v1/products/{id}")
    public boolean deleteRow(@PathVariable("id") Integer id){
        if (!mySqlRepository.findById(id).equals(Optional.empty())){
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
