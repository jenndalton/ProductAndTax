package com.trilogy.calculationmicroservice.controller;


import com.trilogy.calculationmicroservice.exception.NotFoundException;


import com.trilogy.calculationmicroservice.model.ProductView;

import com.trilogy.calculationmicroservice.service.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class CalculationController {

    @Autowired
    ServiceLayer serviceLayer;

 /*   public CalculationController(ServiceLayer serviceLayer){
        this.serviceLayer=serviceLayer;
    }*/


    @RequestMapping(value = "/api/price/product/{productId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductView getTotalPriceAndTax(@PathVariable("productId") String productId,
                                           @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                                           @RequestParam(value="taxExempt", required = false) Boolean taxExempt) {
        ProductView productView = new ProductView();
    /*    if(productId==null ||productId.isEmpty() || productId.equals(" "))
        {
            throw new IllegalArgumentException("Product Id cannot cannot be empty or null");
        }
        if(quantity==null ||quantity.toString().isEmpty() || quantity==0)
        {
            throw new IllegalArgumentException("Quantity cannot be zero or null");
        }*/
        productView.setProductId(productId);
        productView.setQuantity(quantity);
        productView = serviceLayer.getTotalProductPrice(productView, taxExempt);
        if(productView == null){
            throw new NotFoundException("Product details could not be retrieved for "+ productId);
        }
        return productView;

    }

}
