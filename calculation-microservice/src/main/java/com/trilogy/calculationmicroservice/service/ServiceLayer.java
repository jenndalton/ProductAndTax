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

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    ProductRepository productRepository;

   /* public ProductView getTotalProductPriceWithTax(int productId){
        ProductView productView = new ProductView();

       // Product product = productRepository.getProductById(productId);
        Tax tax = taxRepository.getTaxesByCategory(product.getCategory());

        return productView;
    }



    public ProductView getTotalProductPriceTaxExempt(ProductView productView){
        return productView;
    }
*/
   public ServiceLayer(ProductRepository productRepository, TaxRepository taxRepository){
       this.productRepository = productRepository;
       this.taxRepository = taxRepository;
   }


    public Double calculateTotalPrice(ProductView productView, boolean taxExempt){
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
        Double d = (double)Math.round(totalTax);;
        return d;
    }

    public ProductView getTotalProductPrice(ProductView productView,boolean isTaxExempt){
        //get the category from productId
        Product product = getCategoryFromProductRepository(productView.getProductId());
        //get the tax rate based on category from client
        Tax tax = getTaxPercentageFromTaxRepository(product.getCategory());
        isTaxExempt = tax.getTaxExempt();
        ProductView productViewToSend = new ProductView();
        productViewToSend.setProductId(productView.getProductId());
        productViewToSend.setDescription(product.getProductDescription());
        productViewToSend.setQuantity(productView.getQuantity());
        productViewToSend.setTaxPercent(tax.getTaxPercent());
        productViewToSend.setTotalTax(calculateTax(productView));
        productViewToSend.setTotal(calculateTotalPrice(productView,isTaxExempt));
        return productViewToSend;
    }

    // to do Integration Testing
    public Product getCategoryFromProductRepository(String productId){
        return  productRepository.getProductById(productId);
    }

    public Tax getTaxPercentageFromTaxRepository(String Category){
        return  taxRepository.getTaxesByCategory(Category);
    }
}