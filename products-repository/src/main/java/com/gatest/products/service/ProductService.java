package com.gatest.products.service;


import com.gatest.products.models.ProductView;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${taxRepository}")
    private String taxRepositoryName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    private boolean taxExempt;



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
    /*
            List<ServiceInstance> instances = discoveryClient.getInstances(taxRepositoryName);

        String magicEightBallAnswerUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

        String eightBallAnswer = restTemplate.getForObject(magicEightBallAnswerUri, String.class);

        return eightBallAnswer;
     */
}
