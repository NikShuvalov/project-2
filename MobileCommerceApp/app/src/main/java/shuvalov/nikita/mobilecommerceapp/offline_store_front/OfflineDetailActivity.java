package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.R;

public class OfflineDetailActivity extends AppCompatActivity implements OfflineDetailFragment.OnFragmentClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_detail);


        OfflineDetailFragment offlineDetailFragment = OfflineDetailFragment.newInstance(getIntent().getStringExtra(MainActivity.ITEM_NAME_KEY));
        getSupportFragmentManager().beginTransaction().add(R.id.detail_fragment_container, offlineDetailFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
