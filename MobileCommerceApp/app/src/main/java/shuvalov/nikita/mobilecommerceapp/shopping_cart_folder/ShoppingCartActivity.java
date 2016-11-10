package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

public class ShoppingCartActivity extends AppCompatActivity {

    Button checkout, keepShopping;
    TextView mPriceSummary;
    ArrayList<Product> inCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        inCart = ShoppingCartContent.getInstance().getShoppingCartContents();

        checkout = (Button)findViewById(R.id.checkout_button);
        keepShopping=(Button)findViewById(R.id.back_to_store_button);
        mPriceSummary =(TextView)findViewById(R.id.price_summary);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.shopping_cart_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final ShoppingCartRecyclerAdapterSC shoppingCartRecyclerAdapter = new ShoppingCartRecyclerAdapterSC(inCart);

        ItemTouchHelper.Callback callback = new SCItemTouchHelperAdapterCallback(shoppingCartRecyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingCartRecyclerAdapter);

        //Removes checkout Button if user doesn't have items in shopping cart.
        updatePriceView();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inCart.isEmpty()){ //If user has already completed purchase and remains on screen, future present attempts will present toast.
                    Toast.makeText(ShoppingCartActivity.this, "Order has already been completed", Toast.LENGTH_LONG).show();
                }
                else {
                    ShoppingCartContent.getInstance().onCheckout();
                    shoppingCartRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(ShoppingCartActivity.this, R.string.checkout_completed, Toast.LENGTH_SHORT).show();
                    checkout.setText(R.string.checkout_completed);
                    mPriceSummary.setText("");
                }
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

    public void updatePriceView(){
        //Get total cost in order to set cost to button.
        double subTotal = 0;
        double totalCost= 0;
        for (Product product: inCart){
            subTotal +=product.getPrice();
            totalCost+=Product.applyTaxes(product.getPrice());
        }
        //Removes checkout Button if user doesn't have items in shopping cart.
        if (totalCost == 0){
            checkout.setVisibility(View.INVISIBLE);
        }else {
            double taxes = ((int)((totalCost-subTotal)*100))/100.00;//Truncates extra decimals.
            mPriceSummary.setText(String.format("SubTotal: $%s\nTaxes:$%s\nS&H:$7.99",String.valueOf(subTotal),String.valueOf(taxes)));
            totalCost+=7.99;
            String checkoutText = "Pay $"+String.valueOf(totalCost);
            checkout.setText(checkoutText); //FixMe: Sometimes extra decimals appear.
        }
    }

}
