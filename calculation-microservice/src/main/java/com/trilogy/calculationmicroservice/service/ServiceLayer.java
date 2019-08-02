package com.trilogy.calculationmicroservice.service;


import com.trilogy.calculationmicroservice.feign.ProductRepository;
import com.trilogy.calculationmicroservice.feign.TaxRepository;
import com.trilogy.calculationmicroservice.model.Product;
import com.trilogy.calculationmicroservice.model.ProductView;
import com.trilogy.calculationmicroservice.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {
    TaxRepository taxRepository;

    ProductRepository productRepository;

    public ProductView getTotalProductPriceWithTax(int productId){
        ProductView productView = new ProductView();

        Product product = productRepository.getProductById(productId);
        Tax tax = taxRepository.getTaxesByCategory(product.getCategory());

        return productView;
    }



    public ProductView getTotalProductPriceTaxExempt(ProductView productView){
        return productView;
    }


    boolean taxExempt;

    public Double calculateTotalPrice(ProductView productView){
        Double totalPrice = 0.00;
        if (!taxExempt){
            totalPrice = productView.getTotalTax() + (productView.getPricePerUnit() * productView.getQuantity());
        } else {
            totalPrice = (productView.getPricePerUnit() * productView.getQuantity());
        }

        return totalPrice;
    }


    public Double calculateTax(ProductView productView){
        Double totalTax = ((productView.getTaxPercent() / 100) * (productView.getPricePerUnit() * productView.getQuantity()));
//        Long price = (long) (double) Math.round(totalTax);
        Double d = (double)Math.round(totalTax);;
        return d;
    }

    public ProductView getTotalProductPrice(ProductView productView){
        //get the category from productId

        //get the tax rate based on category from client


        return null;
    }
}