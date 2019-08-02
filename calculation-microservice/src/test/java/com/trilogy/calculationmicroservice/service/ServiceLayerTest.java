package com.trilogy.calculationmicroservice.service;

import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.model.Tax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class ServiceLayerTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    TaxRepository taxRepository;

    @InjectMocks
    ServiceLayer productService;


    @Before
    public void setUp(){
        productService = new ServiceLayer(productRepository,taxRepository);
        Tax tax = new Tax();
        tax.setCategory("electonic");
        tax.setTaxPercent(8.25);
        tax.setTaxExempt(false);

        Product product = new Product();
        product.setProductId("1020");
        product.setCategory("electonic");
        product.setPricePerUnit(539.95);
        product.setProductDescription("Flat screen TV 55");

        when(productRepository.getProductById("1020")).thenReturn(product);
        when(taxRepository.getTaxesByCategory("electonic")).thenReturn(tax);

    }

    @Test
    public void calculateTotalTax(){
        boolean isTaxExempt = false;
        ProductView productView = new ProductView();
        productView.setProductId("1020");
        productView.setQuantity(1);


        ProductView productViewExpected = new ProductView();
        productViewExpected.setDescription("Flat screen TV 55");
        productViewExpected.setProductId("1020");
        productViewExpected.setQuantity(1);
        productViewExpected.setPricePerUnit(539.95);
        productViewExpected.setTaxPercent(8.25);
        productViewExpected.setTotalTax(44.55);
        productViewExpected.setTotal(584.50);
        productViewExpected.setCategory("electonic");

       ProductView productViewFromService = productService.getTotalProductPrice(productView,false);
       assertEquals(productViewExpected, productViewFromService);

    }

    @Test
    public void calculateTax(){
        ProductView productView = new ProductView();
        productView.setQuantity(1);
        productView.setPricePerUnit(539.95);
        productView.setTaxPercent(8.25);
        Double fromService = productService.calculateTax(productView);
        Double expectedTax = 44.55;
        assertEquals(expectedTax, fromService);
    }

    @Test
    public void calculateTotalCostWithTaxExempt(){
        ProductView productView = new ProductView();
        productView.setPricePerUnit(539.95);
        productView.setQuantity(1);
        productView.setTotalTax(44.55);
        Double totalCostFromService = productService.calculateTotalPrice(productView,true);
        Double expectedTotalCost = 539.95;
        assertEquals(expectedTotalCost, totalCostFromService);
    }

    @Test
    public void calculateTotalCostWithOutTaxExempt(){
        ProductView productView = new ProductView();
        productView.setPricePerUnit(539.95);
        productView.setQuantity(1);
        productView.setTotalTax(44.55);
        Double totalCostFromService = productService.calculateTotalPrice(productView,false);
        Double expectedTotalCost = 584.5;
        assertEquals(expectedTotalCost, totalCostFromService);
    }

}

