package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.content.Intent;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartActivity;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartContent;

public class OfflineDetailActivity extends AppCompatActivity implements OfflineDetailFragment.OnFragmentClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_detail);

        //Adds toolbar and back navigation
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Product> relevantInventory = OfflineStoreInventory.getInstance().getRelevantInventory();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { //Clicking on back button cause you to go back to storeFront.
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Pass the item name, which is the unique key. That key will instantiate a Product and use it's attributes to populate the views in the fragment.
        CollectionPagerAdapter collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager(), relevantInventory);
        ViewPager fragmentPager = (ViewPager)findViewById(R.id.detail_fragment_container);
        int currentIndex = 0;

        //Get item so we can keep track of what index we're on. Had to use a for loop because fragment holds a copy of Product object instead of the Object itself.
        for(Product product:relevantInventory) {
            if (getIntent().getStringExtra(MainActivity.ITEM_NAME_KEY).equals(product.getName())) {
                currentIndex = relevantInventory.indexOf(product);
                break;
            }
        }

        fragmentPager.setAdapter(collectionPagerAdapter);
        fragmentPager.setCurrentItem(currentIndex);

    }



    @Override
    public void onFragmentInteraction(String itemName) {
        //Use offline database helper to make a copy of the product by the itemName and add that product to shopping cart singleton.
        Product product = OfflineSQLOpenHelper.getMyInstance(this.getApplicationContext()).getProductByName(itemName);
        ShoppingCartContent.getInstance().addToShoppingCart(product);
        Toast.makeText(this, String.format("%s added to cart",itemName), Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.offline_detail_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_cart:
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            default:
                Log.d("Err: ", "You clicked on something that doesn't exist.");
                return false;
        }
    }


}
