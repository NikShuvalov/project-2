package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.ProfileActivity;
import shuvalov.nikita.mobilecommerceapp.R;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartActivity;

public class OfflineStoreActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Product> mProducts;
    OfflineAdapter mAdapter;
    Float mMaxPriceRange;
    int mGridColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        //This block of code figures out the size of the screen, particularly the width.
        Point size= new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int display_width = size.x;
        mGridColumns = display_width/720;


        debugProductList(); //If database is empty, populate it with data.
        mProducts = OfflineSQLOpenHelper.getMyInstance(this).getInventoryAsList();//Defines whole inventory.

        //If the singleton is empty, let's add products to it.
        OfflineStoreInventory offlineStoreInventory = OfflineStoreInventory.getInstance();
        if (offlineStoreInventory.inventoryIsEmpty()){
            offlineStoreInventory.replaceInventory(mProducts);
        }
        displayPriceRelevantProducts();
    }


    public void displayPriceRelevantProducts(){
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        mMaxPriceRange = sharedPreferences.getFloat("max_price_range", Float.MAX_VALUE);

        ArrayList<Product> priceRelevantProducts = OfflineSQLOpenHelper.getMyInstance(this).getProductsCheaperThan(mMaxPriceRange);//Defines relevant inventory.

        if(priceRelevantProducts.isEmpty()){
            priceRelevantProducts = mProducts;
            Toast.makeText(this, "No products match criteria", Toast.LENGTH_SHORT).show();
        }else{
            OfflineStoreInventory.getInstance().setPriceRelevantInventory(priceRelevantProducts);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.offline_store_recycler);

        //Using the displaywidth I determine how many columns will display, my screensize is 1440
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, mGridColumns);
        mAdapter = new OfflineAdapter(priceRelevantProducts);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        mMaxPriceRange = sharedPreferences.getFloat("max_price_range",Float.MAX_VALUE);
        displayPriceRelevantProducts();
        super.onResume();
    }

    public void debugProductList(){
        OfflineSQLOpenHelper dbHelper = OfflineSQLOpenHelper.getMyInstance(this);
        if(dbHelper.isEmpty()){
            dbHelper.addProductToInventory(new Product("Master Sword","Hero of times' sword said to be the sword that defeated Ganondorf", 0, 19.99));
            dbHelper.addProductToInventory(new Product("Phoenix Down", "Revives people", 1, 2.99));
            dbHelper.addProductToInventory(new Product("Ugly X-mas Sweater", "Perfect for staving off the frost and potential mates.", 2, 49.99));
            dbHelper.addProductToInventory(new Product("Holy Grail", "Holds 22 fl.oz of liquid", 3, 14.99));
            dbHelper.addProductToInventory(new Product("Bottled Lightning", "*Do not use while in Bathtub*", 4, 10.99));
            dbHelper.addProductToInventory(new Product("Monkey Paw", "Grants wishes and great for scratching hard to reach areas", 5, 87.99));
            dbHelper.addProductToInventory(new Product("Unconvincing Toupee","Thick is the head that wears this crown.", 5, 9.99));
            dbHelper.addProductToInventory(new Product("Primary color pantsuit", "A stylish pant suit designed by Marc Ecko. Popular amongst Korean Dictators, Yu-Gi-Oh villians, and female presidential candidates.",6,129.99));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_cart:
                Intent cartIntent = new Intent(this, ShoppingCartActivity.class);
                startActivity(cartIntent);
                return true;
            case R.id.remove_filters://This to refreshes data after search, but price max remains
                mAdapter.replaceData(mProducts);
                return true;
            case R.id.settings:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            case R.id.clear_settings:
                getSharedPreferences("mySharedPreferences",MODE_PRIVATE).edit().clear().apply();
                OfflineStoreInventory.getInstance().onResetPriceCeiling();
                displayPriceRelevantProducts();
                return true;
            default:
                Log.d("Err: ", "You clicked on something that doesn't exist.");
                return false;
        }
    }


}
