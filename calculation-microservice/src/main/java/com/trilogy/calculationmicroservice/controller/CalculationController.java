package com.trilogy.calculationmicroservice.controller;

import com.netflix.discovery.converters.Auto;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculationController {
    ServiceLayer serviceLayer;

    ProductRepository productRepository;

    @RequestMapping(value = "/api/price/product/{productId}", method = RequestMethod.GET)
    public void queryForTotalPriceAndTax(@PathVariable int productId) {
        serviceLayer.getTotalProductPriceWithTax(productId);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product queryForProduct(@PathVariable int id) {
        return productRepository.getProductById(id);
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (NullPointerException e){
            return null;
        }
    }
}
