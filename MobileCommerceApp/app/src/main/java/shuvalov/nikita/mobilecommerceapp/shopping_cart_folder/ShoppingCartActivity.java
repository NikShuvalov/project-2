package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

public class ShoppingCartActivity extends AppCompatActivity {

    Button checkout, keepShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        final ArrayList<Product> inCart = ShoppingCartContent.getInstance().getShoppingCartContents();

        checkout = (Button)findViewById(R.id.checkout_button);
        keepShopping=(Button)findViewById(R.id.back_to_store_button);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.shopping_cart_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final ShoppingCartRecyclerAdapterSC shoppingCartRecyclerAdapter = new ShoppingCartRecyclerAdapterSC(inCart);

        ItemTouchHelper.Callback callback = new SCItemTouchHelperAdapterCallback(shoppingCartRecyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingCartRecyclerAdapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              ShoppingCartContent.getInstance().onCheckout();
                shoppingCartRecyclerAdapter.notifyDataSetChanged();
                Toast.makeText(ShoppingCartActivity.this, "Checkout complete", Toast.LENGTH_SHORT).show();
            }
        });

        keepShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Finish the activity and go back to previous page you were in.
                //ToDo: (Optional) If user hits back, tell them they can get a one-time discount if they complete checkout now.
                //Will require some data persistence in order to make sure user doesn't swindle me.
                finish();
            }
        });
    }
}
