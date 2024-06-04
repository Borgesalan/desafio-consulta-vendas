package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.projections.SaleSellerMinProjection;

public class SaleSellerMinDTO {

    private String name;
    private Double amount;

    public SaleSellerMinDTO() {
    }

    public SaleSellerMinDTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SaleSellerMinDTO(Sale entity) {
        this.amount = entity.getAmount();
        this.name = entity.getSeller().getName();
    }

    public SaleSellerMinDTO(SaleSellerMinProjection projection) {
        this.amount = projection.getTotal();
        this.name = projection.getName();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SaleSellerMinDTO{ -> " +
                "amount: " + amount +
                ", name: " + name + '\'' +
                '}';
    }
}
