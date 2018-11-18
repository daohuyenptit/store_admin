package com.daohuyen.dell.storeadmin.model.response;


import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public class Item {
    private ProductViewModel product;
    private int number;

    public Item() {
    }

    public Item(ProductViewModel product, int number) {
        this.product = product;
        this.number = number;
    }

    public ProductViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductViewModel product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && ((Item) obj).getProduct().getId().equals(this.getProduct().getId());
    }

}
