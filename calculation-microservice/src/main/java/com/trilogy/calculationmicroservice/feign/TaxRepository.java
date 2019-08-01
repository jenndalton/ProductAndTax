package com.trilogy.calculationmicroservice.feign;

import com.trilogy.calculationmicroservice.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "tax-repository")
public interface TaxRepository {
    @RequestMapping(value= "/taxes", method = RequestMethod.GET)
    public List<Product> getAllProducts();

    @RequestMapping(value="/taxes/{id}", method = RequestMethod.GET)
    public Product getProductById();
}
