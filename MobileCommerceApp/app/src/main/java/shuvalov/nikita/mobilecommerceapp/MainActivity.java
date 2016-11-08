package shuvalov.nikita.mobilecommerceapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineStoreActivity;


/*
Offline activity will include a FAB, ehhhh don't see much of a point of a FAB anymore.
ToDo: Use strings.xml and colors.xml.
ToDo: Make a functioning toolbar.
ToDo: Add an onSwipeListener for detailfragment so that user can scroll to next/previous item without having to go back to listview.
ToDo: Put shopping cart items into a cardView.

Stretch Goal ToDo-es
ToDo: Randomly generate items in the store based on a large database.
ToDo: Add a weight attribute to products which affects price of shipping upon checkout.
In Case I run out of item Ideas: https://en.wikipedia.org/wiki/List_of_mythological_objects
*/
public class MainActivity extends AppCompatActivity{
    public static final String ITEM_NAME_KEY = "item_name_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button debugAmazon = (Button) findViewById(R.id.amazondebug);
        Button offlineButton = (Button) findViewById(R.id.offline_store_button);

        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OfflineStoreActivity.class);
                startActivity(intent);
            }
        });

        debugAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will ultimately run the download task
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_LONG).show();
            }
        });


    }

}
