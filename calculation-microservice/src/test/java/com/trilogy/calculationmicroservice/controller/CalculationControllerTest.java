package com.trilogy.calculationmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
public class CalculationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    TaxRepository taxRepository;

  //  ProductView productView;
  //  ProductView productViewExpected;

    @Before
    public void setUp(){
        serviceLayer = new ServiceLayer(productRepository,taxRepository);
       // mockMvc= new MockMvc(serviceLayer)
    }

    @Test
    public void getTotalPriceAndTax() throws Exception{
        ProductView productView = new ProductView();
        productView.setProductId("1020");
        productView.setQuantity(1);

        ProductView  productViewExpected = new ProductView();
        productViewExpected.setDescription("Flat screen TV 55");
        productViewExpected.setProductId("1020");
        productViewExpected.setQuantity(1);
        productViewExpected.setPricePerUnit(539.95);
        productViewExpected.setTaxPercent(8.25);
        productViewExpected.setTotalTax(44.55);
        productViewExpected.setTotal(584.50);

        when(serviceLayer.getTotalProductPrice(productView,false)).thenReturn(productViewExpected);
        this.mockMvc.perform(get("/api/price/product/1020?quantity=1&exemptTax=true"))
               // .param("quantity","1")
              //  .param("taxExempt","false")
              //  .content(asJsonString(productView))
              // .contentType(MediaType.APPLICATION_JSON))
              //  .andDo(print())

                .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(asJsonString(productViewExpected)))
                .andExpect(jsonPath("$.description", is("Flat screen TV 55")));
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
