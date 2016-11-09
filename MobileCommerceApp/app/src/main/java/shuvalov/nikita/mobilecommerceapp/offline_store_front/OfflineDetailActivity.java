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

        ArrayList<Product> mInventory = OfflineStoreInventory.getInstance().getStoreFrontInventory();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { //Clicking on back button cause you to go back to storeFront.
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Pass the item name, which is the unique key. That key will instantiate a Product and use it's attributes to populate the views in the fragment.
//        OfflineDetailFragment offlineDetailFragment = OfflineDetailFragment.newInstance(getIntent().getStringExtra(MainActivity.ITEM_NAME_KEY));
//        getSupportFragmentManager().beginTransaction().add(R.id.detail_fragment_container, offlineDetailFragment).commit();

        CollectionPagerAdapter collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());

        ViewPager fragmentPager = (ViewPager)findViewById(R.id.detail_fragment_container);
        int currentIndex = 0;
        //Get item so we can keep track of what index we're on. Had to use a for loop because fragment holds a copy of Product object instead of the Object itself.
        for(Product product:mInventory) {
            if (getIntent().getStringExtra(MainActivity.ITEM_NAME_KEY).equals(product.getName())) {
                currentIndex = mInventory.indexOf(product);
                break;
            }
        }
        fragmentPager.setAdapter(collectionPagerAdapter);
        fragmentPager.setCurrentItem(currentIndex);

        //Temporarily? putting up buttons so that user can switch to next item without having to go back to storeFront page
//        nextButton = (ImageButton)findViewById(R.id.next_button);
//        previousButton = (ImageButton)findViewById(R.id.previous_button);


//            }
//        }
//        setButtonVisibilities();
//
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentIndex++;
//                OfflineDetailFragment updatedOfflineDetailFragment = OfflineDetailFragment.newInstance(mInventory.get(currentIndex).getName());
//                getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container,updatedOfflineDetailFragment).commit();
//                setButtonVisibilities();
//            }
//        });
//        previousButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentIndex--;
//                OfflineDetailFragment updatedOfflineDetailFragment = OfflineDetailFragment.newInstance(mInventory.get(currentIndex).getName());
//                getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container,updatedOfflineDetailFragment).commit();
//                setButtonVisibilities();
//            }
//        });
//
//    }
//
//
//    public void setButtonVisibilities(){
//        //If we are currently looking at the last item of the inventory, get rid of the nextbutton and viceversa for previous button.
//        if (currentIndex == 0){
//            previousButton.setVisibility(View.INVISIBLE);
//        }else if (currentIndex == mInventory.size()-1){
//            nextButton.setVisibility(View.INVISIBLE);
//        }else{
//            previousButton.setVisibility(View.VISIBLE);
//            nextButton.setVisibility(View.VISIBLE);
//        }

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


//    @Override
//    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        Point point = new Point();
//        Display display = getWindowManager().getDefaultDisplay();
//        display.getSize(point);
//        int screen_width = point.x;
//        int screen_height = point.y;
//        float horizontal_delta = Math.abs(motionEvent.getX() - motionEvent1.getX());
//        float vertical_delta = Math.abs(motionEvent.getY()-motionEvent1.getY());
//        if(motionEvent.getX()<motionEvent1.getX() && horizontal_delta>screen_width*0.4){
//            Toast.makeText(this, "Swipe towards Left", Toast.LENGTH_SHORT).show();
//        }else if (motionEvent.getX()>motionEvent1.getX() && horizontal_delta>screen_width*0.4){
//            Toast.makeText(this, "Swipe towards Right", Toast.LENGTH_SHORT).show();
//        }else if (motionEvent.getY()>motionEvent1.getY() && vertical_delta>screen_height*0.5){
//            Toast.makeText(this, "Swipe towards Top", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }


}
