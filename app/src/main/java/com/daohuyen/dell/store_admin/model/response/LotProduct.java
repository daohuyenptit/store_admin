package com.daohuyen.dell.store_admin.model.response;


import com.daohuyen.dell.store_admin.model.Product;

public class LotProduct {
    private String id;
    private Product product;
    private Bill bill;
    private int amount;

    public LotProduct() {
    }

    public LotProduct(int amount) {
        this.amount = amount;
    }

    public LotProduct(Product product, Bill bill, int amount) {
        this.product = product;
        this.bill = bill;
        this.amount = amount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
