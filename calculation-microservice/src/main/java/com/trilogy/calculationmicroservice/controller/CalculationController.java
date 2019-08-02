package com.trilogy.calculationmicroservice.controller;

import com.netflix.discovery.converters.Auto;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.model.Tax;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CalculationController {

    @Autowired
    ServiceLayer serviceLayer;


    @RequestMapping(value = "/api/price/product/{productId}", method = RequestMethod.GET)
    public ProductView queryForTotalPriceAndTax(@PathVariable String productId, @RequestParam Integer quantity, @RequestParam(required = false) Boolean taxExempt) {
        ProductView productView = new ProductView();
        productView.setProductId(productId);
        productView.setQuantity(quantity);
        return null;
//        return serviceLayer.getTotalProductPrice(productView, taxExempt);
    }

}
