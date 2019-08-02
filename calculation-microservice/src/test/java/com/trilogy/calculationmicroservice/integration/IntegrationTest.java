package com.trilogy.calculationmicroservice.integration;

import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTest {
   // private MockClient mockClient;

    @Mock
    ServiceLayer serviceLayer;

    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    TaxRepository taxRepository;



    @Test
   public void getCategoryFromProductRepository(){

   }

   @Test
   public void getTaxPercentageFromTaxRepository(){

    }
}
