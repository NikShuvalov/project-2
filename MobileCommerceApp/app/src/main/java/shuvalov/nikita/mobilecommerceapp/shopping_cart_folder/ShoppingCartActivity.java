package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //ToDo: Create a singleton to store shopping cart content.
        //ToDo: Retrieve the shopping cart content list and display in recyclerView.
        //Temporary shoppingList to test shoppingCart
        ArrayList<Product> inCart = new ArrayList<>();

        inCart.add(new Product("Unconvincing Toupee","\"This is my real hair.\"",5, 9.99));
        inCart.add(new Product("Primary color pantsuit", "A stylish pant suit designed by Marc Ecko. Popular amongst Korean Dictators, Yu-Gi-Oh villians, and female presidential candidates.",6,129.99));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.shopping_cart_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(inCart);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingCartAdapter);
    }
}
