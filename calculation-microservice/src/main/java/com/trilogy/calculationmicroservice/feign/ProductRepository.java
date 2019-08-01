package com.trilogy.calculationmicroservice.feign;

import com.trilogy.calculationmicroservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "product-repository")
public interface ProductRepository {
    @RequestMapping(value= "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts();

    @RequestMapping(value="/product/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id);
}
