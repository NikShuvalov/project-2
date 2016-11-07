package shuvalov.nikita.mobilecommerceapp;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineAdapter;
import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineStoreActivity;


/*ToDo:Create Offline mode store. This will be based off of a preset database.
ToDo: Offline Activity will have cardviews in a recyclerView of 2 columns.
CardViews will contain an image of the product, a short description/probably not and a price.
Offline activity will include a FAB
ToDo: Use strings.xml and colors.xml.
ToDo:
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
