package com.trilogy.calculationmicroservice.controller;

import com.netflix.discovery.converters.Auto;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.model.Tax;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CalculationController {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/api/price/product/{productId}", method = RequestMethod.GET)
    public ProductView queryForTotalPriceAndTax(@PathVariable String productId, @RequestParam(required = false) Integer quantity, @RequestParam(required = false) Boolean taxExempt) {
        ProductView productView = new ProductView();
        productView.setProductId(productId);
        try {
            productView.setQuantity(quantity);
        } catch (NullPointerException e){

        }

        return serviceLayer.getTotalProductPrice(productView, taxExempt);

//        return productRepository.getProductById(productId);

    }

}
