package shuvalov.nikita.mobilecommerceapp;

/**
 * Created by NikitaShuvalov on 11/2/16.
 */

public class Product {
    private String mName, mDescription;
    private int mImageRef;
    private double mPrice;


    //Idea: Add a weight to products, higher weight items cost more to ship.


    public Product(String name, String description, int imageRef, double price) {
        mName = name;
        mDescription = description;
        mImageRef = imageRef;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImageRef() {
        return mImageRef;
    }

    public double getPrice() {
        return mPrice;
    }

    public static double applyTaxes(double cost){
        cost *= 1.08;
        int newNumber = (int)(cost*100);
        cost = (double) newNumber;
        return cost/100.00;
    }


}
