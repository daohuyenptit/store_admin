package com.daohuyen.dell.store_admin.model.view;


import com.daohuyen.dell.store_admin.model.response.Category;

public class CategoryViewModel {
    private String id;
    private String title;
    private String logo;
    private String idPL;
    private String namePL;

    public CategoryViewModel() {
    }

    public CategoryViewModel(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.logo = category.getLogo();
        this.idPL = category.getProductLine().getId();
        this.namePL = category.getProductLine().getName();
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIdPL() {
        return idPL;
    }

    public void setIdPL(String idPL) {
        this.idPL = idPL;
    }

    public String getNamePL() {
        return namePL;
    }

    public void setNamePL(String namePL) {
        this.namePL = namePL;
    }
}
