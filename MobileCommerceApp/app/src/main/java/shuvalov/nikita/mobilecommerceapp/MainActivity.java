package shuvalov.nikita.mobilecommerceapp;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineStoreActivity;


public class MainActivity extends AppCompatActivity{
    public static final String ITEM_NAME_KEY = "item_name_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button offlineButton = (Button) findViewById(R.id.offline_store_button);

        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OfflineStoreActivity.class);
                startActivity(intent);
            }
        });



    }

}
