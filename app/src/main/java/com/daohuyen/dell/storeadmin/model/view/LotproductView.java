package com.daohuyen.dell.storeadmin.model.view;
import com.daohuyen.dell.storeadmin.model.response.LotProduct;

import java.io.Serializable;

public class LotproductView implements Serializable{
    private String id;
    private String name;
    private int price;
    private String logoUrl;

    public LotproductView() {
    }

    public LotproductView(LotProduct lotProduct) {
        this.id = lotProduct.getId();
        this.name = lotProduct.getProduct().getName();
        this.price = lotProduct.getProduct().getPrice();
        this.logoUrl = lotProduct.getProduct().getLogoUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
