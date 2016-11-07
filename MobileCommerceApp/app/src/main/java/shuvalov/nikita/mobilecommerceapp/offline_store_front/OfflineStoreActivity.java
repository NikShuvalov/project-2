package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

public class OfflineStoreActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    ArrayList<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);

        products = new ArrayList<>();
        debugProductList();

        //This block of code figures out the size of the screen, particularly the width.
        Point size= new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int display_width = size.x;

        mRecyclerView = (RecyclerView) findViewById(R.id.offline_store_recycler);

        //Using the displaywidth I determine how many columns will display, my screensize is 1440
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, display_width/720);


        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new OfflineAdapter(products, display_width));

    }

    public void debugProductList(){
        products.add(new Product("Master Sword","Hero of times' sword said to be the sword that defeated Ganondorf", 0, 19.99));
        products.add(new Product("Pheonix Down", "Revives people", 1, 2.99));
        products.add(new Product("Ugly X-mas Sweater", "Perfect for keeping away potential mates and the frost", 2, 49.99));
        products.add(new Product("Holy Grail", "Holds 22 fl.oz of liquid", 3, 14.99));
        products.add(new Product("Bottled Lightning", "Do not use while in Bathtub", 4, 10.99));
        products.add(new Product("Monkey Paw", "Grants wishes and great for scratching hard to reach areas", 5, 87.99));
        OfflineSQLOpenHelper dbHelper = OfflineSQLOpenHelper.getMyInstance(this);
        if(dbHelper.isEmpty()){
            for(Product product:products){
                dbHelper.addProductToInventory(product);
            }
        }

    }

}