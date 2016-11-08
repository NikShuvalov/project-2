package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

public class OfflineStoreActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    ArrayList<Product> products;
    OfflineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        products = OfflineSQLOpenHelper.getMyInstance(this).getInventoryAsList();
//        debugProductList();

        //This block of code figures out the size of the screen, particularly the width.
        Point size= new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int display_width = size.x;

        mRecyclerView = (RecyclerView) findViewById(R.id.offline_store_recycler);

        //Using the displaywidth I determine how many columns will display, my screensize is 1440
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, display_width/720);

        mAdapter = new OfflineAdapter(products, display_width);


        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void debugProductList(){
        products.add(new Product("Master Sword","Hero of times' sword said to be the sword that defeated Ganondorf", 0, 19.99));
        products.add(new Product("Pheonix Down", "Revives people", 1, 2.99));
        products.add(new Product("Ugly X-mas Sweater", "Perfect for keeping away potential mates and the frost", 2, 49.99));
        products.add(new Product("Holy Grail", "Holds 22 fl.oz of liquid", 3, 14.99));
        products.add(new Product("Bottled Lightning", "Do not use while in Bathtub", 4, 10.99));
        products.add(new Product("Monkey Paw", "Grants wishes and great for scratching hard to reach areas", 5, 87.99));
        products.add(new Product("Unconvincing Toupee","\"This is my real hair.\"",5, 9.99));
        products.add(new Product("Primary color pantsuit", "A stylish pant suit designed by Marc Ecko. Popular amongst Korean Dictators, Yu-Gi-Oh villians, and female presidential candidates.",6,129.99));

        OfflineSQLOpenHelper dbHelper = OfflineSQLOpenHelper.getMyInstance(this);
        if(dbHelper.isEmpty()){
            for(Product product:products){
                dbHelper.addProductToInventory(product);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.offline_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, OfflineStoreActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            ArrayList<Product> relevantProducts = OfflineSQLOpenHelper.getMyInstance(this).searchProducts(query);
            if (relevantProducts.isEmpty()){
                Toast.makeText(this, "No results matched search", Toast.LENGTH_LONG).show();
            }else{
                mAdapter.replaceData(relevantProducts);
            }

        }
    }
}
