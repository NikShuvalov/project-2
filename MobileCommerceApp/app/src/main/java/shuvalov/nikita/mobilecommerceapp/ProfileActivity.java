package shuvalov.nikita.mobilecommerceapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    Button exitButt, applyButt;
    EditText priceRangeEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        exitButt = (Button)findViewById(R.id.exit_button);
        applyButt = (Button)findViewById(R.id.apply_button);

        priceRangeEntry = (EditText)findViewById(R.id.price_max_entry);

        exitButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        applyButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =  getSharedPreferences("mySharedPreferences", MODE_APPEND);

                int toastMessage = 0;
                if(priceRangeEntry.getText() != null) {
                    sharedPreferences.edit().putFloat("max_price_range", Float.parseFloat(priceRangeEntry.getText().toString())).apply();
                    toastMessage+=1;
                }

                //Added extra cases in the event I add more variables to change in profile.
                switch (toastMessage){
                    case 0:
                        Toast.makeText(ProfileActivity.this, "No Changes Were Saved", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(ProfileActivity.this, "Max Price Preference Saved", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(ProfileActivity.this, "Extra Detail Change Saved", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(ProfileActivity.this, "All Changes Saved", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });



    }
}
