package com.daohuyen.dell.storeadmin.model.response;

public class ProductGroup {

    private String id;
    private String name;

    public ProductGroup() {
    }

    public ProductGroup(String id, String name) {
        this.id = id;
        this.name = name;
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
}
