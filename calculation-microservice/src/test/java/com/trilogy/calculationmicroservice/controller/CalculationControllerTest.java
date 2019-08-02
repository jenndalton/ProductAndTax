package com.trilogy.calculationmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.model.Tax;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CalculationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    TaxRepository taxRepository;



    @Before
    public void setUp(){
    }



    @Test
    public void expectsUnprocessableEntityForNumberFormatException() throws Exception{
        this.mockMvc.perform(get("/api/price/product/102345?quantity=4e&taxExempt=true")).
                andExpect(status().isBadRequest());
    }

    @Test
    public void expectsIllegalStateExceptionWhenQuantityIsLeftOff() throws Exception{
        this.mockMvc.perform(get("/api/price/product/102345?")).
                andExpect(status().isUnprocessableEntity());
    }




    @Test
    public void getProductThatDoesNotExistReturns404() throws Exception {
        ProductView productView = new ProductView();
        productView.setProductId("102345");
        when(serviceLayer.getTotalProductPrice(productView, false)).thenReturn(null);
        this.mockMvc.perform(get("/api/price/product/102345?quantity=1&taxExempt=true")).
                andExpect(status().isNotFound());
    }

    @Test
    public void taxExemptInUri() throws Exception{
        ProductView productView = new ProductView();
        productView.setProductId("1031");
        productView.setQuantity(1);

        ProductView  productViewExpected = new ProductView();
        productViewExpected.setDescription("Dove soap");
        productViewExpected.setProductId("1031");
        productViewExpected.setQuantity(1);
        productViewExpected.setPricePerUnit(539.95);
        productViewExpected.setTaxPercent(8.25);
        productViewExpected.setTotalTax(44.55);
        productViewExpected.setTotal(584.50);

        Product product = new Product();
        product.setProductId("1031");
        product.setCategory("toiletry");
        product.setPricePerUnit(3.45);
        product.setProductDescription("Dove soap");

        Tax tax = new Tax();
        tax.setCategory("toiletry");
        tax.setTaxPercent(8.25);
        tax.setTaxExempt(false);

        when(serviceLayer.getTotalProductPrice(productView,true)).thenReturn(productViewExpected);
        //when(productRepository.getProductById("1031")).thenReturn(product);
        //when(taxRepository.getTaxesByCategory("toiletry")).thenReturn(tax);


        this.mockMvc.perform(get("/api/price/product/1031?quantity=1&taxExempt=true"))
                // .param("quantity","1")
                // .param("taxExempt","false")
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(asJsonString(productViewExpected)))
                .andExpect(jsonPath("$.description", is("Dove soap")));
    }

    @Test
    public void taxExemptNotInUri() throws Exception{
        ProductView productView = new ProductView();
        productView.setProductId("1031");
        productView.setQuantity(1);

        ProductView  productViewExpected = new ProductView();
        productViewExpected.setDescription("Dove soap");
        productViewExpected.setProductId("1031");
        productViewExpected.setQuantity(1);
        productViewExpected.setPricePerUnit(539.95);
        productViewExpected.setTaxPercent(8.25);
        productViewExpected.setTotalTax(44.55);
        productViewExpected.setTotal(584.50);

        Product product = new Product();
        product.setProductId("1031");
        product.setCategory("toiletry");
        product.setPricePerUnit(3.45);
        product.setProductDescription("Dove soap");

        Tax tax = new Tax();
        tax.setCategory("toiletry");
        tax.setTaxPercent(8.25);
        tax.setTaxExempt(false);

        when(serviceLayer.getTotalProductPrice(productView,false)).thenReturn(productViewExpected);

        this.mockMvc.perform(get("/api/price/product/1031?quantity=1"))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                 .andExpect(content().json(asJsonString(productViewExpected)))
                 .andExpect(jsonPath("$.description", is("Dove soap")));
    }

    public final static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
