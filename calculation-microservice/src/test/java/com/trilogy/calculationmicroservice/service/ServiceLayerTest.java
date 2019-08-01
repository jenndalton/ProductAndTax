package com.trilogy.calculationmicroservice.service;

import com.trilogy.calculationmicroservice.model.ProductView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class ServiceLayerTest {

//    @Mock
//    TaxService taxService;
    // we will mock the tax service

    @InjectMocks
    ServiceLayer productService;

    @Before
    public void setUp(){

    }

    @Test
    public void calculateTotalTax(){
        /*
            private String productId;
    private String description;
    private Integer quantity;
    private Double pricePerUnit;
    private Double taxPercent;
    private Double totalTax;
    private Double total;
         */
        boolean isTaxExempt = false;
        ProductView productView = new ProductView();
        productView.setDescription("Flat screen TV 55");
        productView.setProductId("1020");
        productView.setQuantity(1);

//        productView.setPricePerUnit(539.95);
//        productView.setTaxPercent(.0675);
//        productView.setTotalTax(.1234);
//        productView.setTotalTax(12.32);


        ProductView productViewExpected = new ProductView();

        productViewExpected.setDescription("Flat screen TV 55");
        productViewExpected.setProductId("1020");
        productViewExpected.setQuantity(1);
        productViewExpected.setPricePerUnit(539.95);
        productViewExpected.setTaxPercent(8.25);
        productViewExpected.setTotalTax(44.55);
        productViewExpected.setTotal(584.49);

        ProductView productViewFromService = productService.getTotalProductPrice(productView);
        assertEquals(productViewExpected, productViewFromService);

    }

    @Test
    public void calculateTax(){
        ProductView productView = new ProductView();

        productView.setQuantity(1);
        productView.setPricePerUnit(539.95);
        productView.setTaxPercent(8.25);


        Double fromService = productService.calculateTax(productView);
        Double expectedTax = (double)Math.round(44.55);
        // (double)Math.round(totalTax);;
        assertEquals(expectedTax, fromService);
    }

    @Test
    public void calculateTotalCost(){
        ProductView productView = new ProductView();
        productView.setPricePerUnit(539.95);
        productView.setQuantity(1);
        productView.setTotalTax(44.55);
        Double totalCostFromService = productService.calculateTotalPrice(productView);

        Double expectedTotalCost = 584.5;
        assertEquals(expectedTotalCost, totalCostFromService);


    }

}

