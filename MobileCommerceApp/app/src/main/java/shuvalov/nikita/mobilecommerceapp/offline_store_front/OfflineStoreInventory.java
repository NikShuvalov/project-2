package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;

/**
 * Created by NikitaShuvalov on 11/9/16.
 */
public class OfflineStoreInventory {
    private ArrayList<Product> mStoreFrontInventory;

    private static OfflineStoreInventory ourInstance = new OfflineStoreInventory();

    public static OfflineStoreInventory getInstance() {
        return ourInstance;
    }

    private OfflineStoreInventory() {
        mStoreFrontInventory = new ArrayList<>();
    }

    public void addItemToInventory(Product product){
        mStoreFrontInventory.add(product);
    }

    public ArrayList<Product> getStoreFrontInventory(){
        return mStoreFrontInventory;
    }

    public void replaceInventory(ArrayList<Product> newInventory){
        mStoreFrontInventory = newInventory;
    }
    public boolean inventoryIsEmpty(){
        return mStoreFrontInventory.isEmpty();
    }

}
