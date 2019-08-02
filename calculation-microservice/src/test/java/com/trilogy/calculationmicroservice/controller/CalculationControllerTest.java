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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
public class CalculationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();



    @Before
    public void setUp(){
    }

    @Test
    public void getRsvpThatDoesNotExistReturns404() throws Exception {
        ProductView productView = new ProductView();
        productView.setProductId("100");



        when(serviceLayer.getTotalProductPrice(productView, false)).thenReturn(null);
        this.mockMvc.perform(get("/api/price/product/" + productView.getProductId())).andDo(print()).
                andExpect(status().isNotFound())
                .andExpect(content().string(containsString("There is no Product View with id " + productView.getProductId())));
    }


    @Test // for a get by id
    public void getRsvpByIdShouldReturnRsvpWithIdJson() throws Exception {
        //Arrange/assemble the expected output
        ProductView productView = new ProductView();
        productView.setProductId("100");
        productView.setDescription("Flat screen TV 55");
        productView.setQuantity(1);
        productView.setProductId("539.93");
        productView.setTaxPercent(8.25);
        productView.setTotal(584.49);

        //Get the expected JSON output
        String outputJson = mapper.writeValueAsString(productView);

        //Set up your mock response!
        when(serviceLayer.getTotalProductPrice(productView, false )).thenReturn(productView);

        this.mockMvc.perform(
                get
                        ("/api/price/product/{productId}?quantity={1}&exemptTax=false"))
                .andDo(print()).andExpect(status()
                .isOk())
                .andExpect(content().json(outputJson));

    }

}