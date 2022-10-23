package com.example.springdataredis.controller.product;

import com.example.springdataredis.entity.product.Product;
import com.example.springdataredis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return dao.save(product);
    }

    @GetMapping
    @Cacheable(value = "Product")
    public List<Product> findAll() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product")
    public Object findProduct(@PathVariable int id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public ResponseEntity<String> delete(@PathVariable int id) {
        dao.delete(id);
        return new ResponseEntity<>("Success delete", HttpStatus.OK);
    }
}
