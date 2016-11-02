package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import shuvalov.nikita.mobilecommerceapp.R;

public class OfflineStore extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);

        mRecyclerView = (RecyclerView) findViewById(R.id.offline_store_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);


    }
}
