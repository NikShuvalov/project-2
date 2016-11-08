package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartActivity;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartContent;

public class OfflineDetailActivity extends AppCompatActivity implements OfflineDetailFragment.OnFragmentClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_detail);

        //Adds toolbar and back navigation
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { //Clicking on back button cause you to go back to storeFront.
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        OfflineDetailFragment offlineDetailFragment = OfflineDetailFragment.newInstance(getIntent().getStringExtra(MainActivity.ITEM_NAME_KEY));
        getSupportFragmentManager().beginTransaction().add(R.id.detail_fragment_container, offlineDetailFragment).commit();
    }

    @Override
    public void onFragmentInteraction(String itemName) {
        Intent intent = new Intent(OfflineDetailActivity.this, ShoppingCartActivity.class);

        //Use offline database helper to make a copy of the product by the itemName and add that product to shopping cart singleton.
        Product product = OfflineSQLOpenHelper.getMyInstance(this.getApplicationContext()).getProductByName(itemName);
        ShoppingCartContent.getInstance().addToShoppingCart(product);

        startActivity(intent);
    }
}
