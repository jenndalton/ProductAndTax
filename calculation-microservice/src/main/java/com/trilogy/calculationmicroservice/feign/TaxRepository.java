package com.trilogy.calculationmicroservice.feign;

import com.trilogy.calculationmicroservice.model.Tax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "tax-repository")
public interface TaxRepository {

    @RequestMapping(value="/taxes/{category}", method = RequestMethod.GET)
    public Tax getTaxesByCategory(@PathVariable String category);

}
