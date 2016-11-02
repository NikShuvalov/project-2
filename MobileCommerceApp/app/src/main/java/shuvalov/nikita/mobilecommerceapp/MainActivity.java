package shuvalov.nikita.mobilecommerceapp;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineAdapter;
import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineStoreActivity;


/*ToDo:Create Offline mode store. This will be based off of a preset database.
ToDo: Offline Activity will have cardviews in a recyclerView of 2 columns.
CardViews will contain an image of the product, a short description and a price.
Offline activity will include a FAB

*/
public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button debugAmazon = (Button) findViewById(R.id.amazondebug);

        debugAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OfflineStoreActivity.class);
                startActivity(intent);
            }
        });
    }

}
