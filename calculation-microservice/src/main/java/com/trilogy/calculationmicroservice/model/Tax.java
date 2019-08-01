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
        return Objects.equals(categoryId, tax.categoryId) &&
                Objects.equals(category, tax.category) &&
                Objects.equals(taxPercent, tax.taxPercent) &&
                Objects.equals(taxExempt, tax.taxExempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category, taxPercent, taxExempt);
    }
}
