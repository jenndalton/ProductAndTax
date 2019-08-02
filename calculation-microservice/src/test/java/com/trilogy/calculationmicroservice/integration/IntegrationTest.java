package com.trilogy.calculationmicroservice.integration;

import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.Tax;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.MockServerConfigurer;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@SpringBootTest
public class IntegrationTest {

    @Mock
    ServiceLayer serviceLayer;

    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    TaxRepository taxRepository;


    @Before
    public void setUp(){
        restTemplate = new RestTemplate();
    }

    RestTemplate restTemplate;


    @Test
   public void getCategoryFromProductRepository(){
        Product product = new Product();
        product.setProductDescription("Dove soap");
        product.setPricePerUnit(3.45);
        product.setCategory("toiletry");
        product.setProductId("1031");
        Product productFromClient = restTemplate.getForObject("http://localhost:9000/products"+"/1031",Product.class);
        Assert.assertThat(productFromClient.getCategory(),is("toiletry"));
   }

   @Test
   public void getTaxPercentageFromTaxRepository(){
    Tax tax = new Tax();
    tax.setTaxPercent(8.25);
    tax.setCategory("toiletry");
    tax.setTaxExempt(false);
       Tax taxFromClient = restTemplate.getForObject("http://localhost:9001/taxes"+"/toiletry",Tax.class);
       Assert.assertThat(taxFromClient.getTaxPercent(),is(8.25));
    }
}
