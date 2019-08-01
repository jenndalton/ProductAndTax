package com.trilogy.calculationmicroservice.controller;

import com.netflix.discovery.converters.Auto;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CalculationController {
    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/api/price/product/{productId}", method = RequestMethod.GET)
    public void queryForTotalPriceAndTax(@PathVariable int productId){
        serviceLayer.getTotalProductPriceWithTax(productId);
    }
}
