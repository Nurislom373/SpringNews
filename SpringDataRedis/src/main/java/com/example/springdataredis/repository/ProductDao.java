package com.example.springdataredis.repository;

import com.example.springdataredis.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    private final HashOperations hashOperations;
    public static final String HASH_KEY = "Product";

    @Autowired
    public ProductDao(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Product save(Product product) {
        hashOperations.put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return hashOperations.values(HASH_KEY);
    }

    public Object findById(int id) {
        System.out.println("call database findById() from DB");
        return hashOperations.get(HASH_KEY, id);
    }

    public void delete(int id) {
        hashOperations.delete(HASH_KEY, id);
    }

}
