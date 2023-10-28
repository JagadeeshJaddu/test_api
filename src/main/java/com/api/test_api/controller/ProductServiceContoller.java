package com.api.test_api.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.test_api.model.Product;

@RestController
public class ProductServiceContoller {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Product2");
        productRepo.put(product1.getId(), product1);
        productRepo.put(product2.getId(), product2);
        Product product3 = new Product();
        product3.setId("3");
        product3.setName("Product3");
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is successfully created.", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        productRepo.remove(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is successfully updated.", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is successfully deleted.", HttpStatus.OK);
    }
}
