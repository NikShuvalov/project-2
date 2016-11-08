package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;

/**
 * Created by NikitaShuvalov on 11/7/16.
 */

public class OfflineSQLOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "ALL_GOOD_THINGS.DB";
    public static final String OFFLINE_TABLE_NAME="OFFLINE_INVENTORY";

    public static final String COL_ID= "ID";
    public static final String COL_NAME="NAME";
    public static final String COL_DESCRIPTION="DESCRIPTION";
    public static final String COL_IMG_REF="IMAGE_REF";
    public static final String COL_PRICE = "PRICE";

    public static final String CREATE_OFFLINE_INVENTORY_TABLE = "CREATE TABLE " +OFFLINE_TABLE_NAME+
            "("+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME + " VARCHAR, "+
            COL_DESCRIPTION + " VARCHAR, "+
            COL_PRICE+ " REAL, "+
            COL_IMG_REF + " INTEGER)";



    //ToDo: Use a sharedpreferences to keep track of user's max price range. And use that number to filter products.


    private static OfflineSQLOpenHelper myInstance;


    private OfflineSQLOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OfflineSQLOpenHelper getMyInstance(Context context) {
        if(myInstance==null) {
            myInstance = new OfflineSQLOpenHelper(context.getApplicationContext());
        }
        return myInstance;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {sqLiteDatabase.execSQL(CREATE_OFFLINE_INVENTORY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean isEmpty(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ OFFLINE_TABLE_NAME,null);
        return !cursor.moveToFirst();
    }

    public void addProductToInventory(Product product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getName());
        values.put(COL_DESCRIPTION, product.getDescription());
        values.put(COL_PRICE, product.getPrice());
        values.put(COL_IMG_REF, product.getImageRef());
        db.insert(OFFLINE_TABLE_NAME,null, values);
        db.close();
    }

    public ArrayList<Product> getInventoryAsList(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery("SELECT * FROM "+ OFFLINE_TABLE_NAME, null);
        ArrayList<Product> products = new ArrayList<>();
        if (c.moveToFirst()){
            while(!c.isAfterLast()){//Uses the database files to create an object and add it to list.
                products.add(new Product(c.getString(c.getColumnIndex(COL_NAME)),
                        c.getString(c.getColumnIndex(COL_DESCRIPTION)),
                        c.getInt(c.getColumnIndex(COL_IMG_REF)),
                        c.getDouble(c.getColumnIndex(COL_PRICE))));

                c.moveToNext();
            }
        }
        c.close();
        return products; //Return list
    }

    public Product getProductByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {name};
        String selection = COL_NAME + " = ?";

        Cursor c = db.query(OFFLINE_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(c.moveToFirst()){
            Product retrievedProduct = new Product(name,
                    c.getString(c.getColumnIndex(COL_DESCRIPTION)),
                    c.getInt(c.getColumnIndex(COL_IMG_REF)),
                    c.getDouble(c.getColumnIndex(COL_PRICE)));
            db.close();
            return retrievedProduct;//Based on item name create a product with those attributes.
        }
        return null; //This shouldn't happen.
    }

    public ArrayList<Product> searchProducts(String query){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Product> matchingProducts = new ArrayList<>();

        //Search based on query parameters if name or description contains query return appropriate items.
        Cursor c = db.query(OFFLINE_TABLE_NAME,
                null,
                COL_NAME +" LIKE ? OR "+ COL_DESCRIPTION+" LIKE ?",
                new String[]{"%"+query+"%","%"+query+"%"},
                null,
                null,
                null);
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                matchingProducts.add(new Product(c.getString(c.getColumnIndex(COL_NAME)),
                        c.getString(c.getColumnIndex(COL_DESCRIPTION)),
                        c.getInt(c.getColumnIndex(COL_IMG_REF)),
                        c.getDouble(c.getColumnIndex(COL_PRICE))
                        ));
                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return matchingProducts;
    }

}
