package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;


import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;

/**
 * Created by NikitaShuvalov on 11/8/16.
 */
public class ShoppingCartContent {

    private ArrayList<Product> mShoppingCartContents;

    private static ShoppingCartContent ourInstance = new ShoppingCartContent();


    public static ShoppingCartContent getInstance() {
        return ourInstance;
    }

    private ShoppingCartContent() {
        mShoppingCartContents = new ArrayList<>();
    }

    public void addToShoppingCart(Product product){
        mShoppingCartContents.add(product);
    }
    public ArrayList<Product> getShoppingCartContents(){
        return mShoppingCartContents;
    }
    
}
