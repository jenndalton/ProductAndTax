package com.trilogy.calculationmicroservice.feign;

import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.Tax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "TAX-REPOSITORY")
public interface TaxRepository {
    @RequestMapping(value= "/taxes", method = RequestMethod.GET)
    public List<Tax> getAllTaxes();

    @RequestMapping(value="/taxes/{id}", method = RequestMethod.GET)
    public Tax getTaxesById(@PathVariable int id);

}
