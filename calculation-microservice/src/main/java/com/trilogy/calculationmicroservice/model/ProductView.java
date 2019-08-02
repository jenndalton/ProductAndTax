package com.trilogy.calculationmicroservice.model;

import java.util.Objects;

public class ProductView {
    private String productId;
    private String description;
    private Integer quantity;
    private Double pricePerUnit;
    private Double taxPercent;
    private Double totalTax;
    private Double total;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductView that = (ProductView) o;
        return productId.equals(that.productId) &&
                description.equals(that.description) &&
                quantity.equals(that.quantity) &&
                pricePerUnit.equals(that.pricePerUnit) &&
                taxPercent.equals(that.taxPercent) &&
                totalTax.equals(that.totalTax) &&
                total.equals(that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, description, quantity, pricePerUnit, taxPercent, totalTax, total);
    }
}
