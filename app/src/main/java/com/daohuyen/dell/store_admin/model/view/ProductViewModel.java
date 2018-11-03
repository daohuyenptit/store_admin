package com.daohuyen.dell.store_admin.model.view;


import com.daohuyen.dell.store_admin.model.Product;

import java.io.Serializable;

public class ProductViewModel implements Serializable {
    private String id;
    private String name;
    private int price;
    private String logoUrl;
    private String des;
    private String categoryID;


    public ProductViewModel() {
    }

    public ProductViewModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.logoUrl = product.getLogoUrl();
        this.des=product.getDescription();
        this.categoryID=product.getCategory().getId();
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
