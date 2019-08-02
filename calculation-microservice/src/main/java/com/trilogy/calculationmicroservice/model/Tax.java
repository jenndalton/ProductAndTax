package com.trilogy.calculationmicroservice.model;

import java.util.Objects;

public class Tax {
    private Integer categoryId;
    private String category;
    private Double taxPercent;
    private Boolean taxExempt;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Boolean getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return categoryId.equals(tax.categoryId) &&
                category.equals(tax.category) &&
                taxPercent.equals(tax.taxPercent) &&
                taxExempt.equals(tax.taxExempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category, taxPercent, taxExempt);
    }
}
