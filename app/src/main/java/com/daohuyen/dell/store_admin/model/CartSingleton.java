package com.daohuyen.dell.store_admin.model;

import java.util.ArrayList;

public class CartSingleton {
    public static Cart cart;
    public static Cart getCart(){
        if(cart==null){
            cart=new Cart(new ArrayList<>(),0);
        }
        return  cart;

    }
}
