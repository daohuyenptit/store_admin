package com.daohuyen.dell.store_admin.model.response;

public class Category {
    private String id;
    private String title;
    private String logo;
    
    private ProductLine productLine;
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductLine getProductLine() {
        return productLine;
    }



    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }
}
