package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;
import shuvalov.nikita.mobilecommerceapp.setup.DBAssetHelper;
import shuvalov.nikita.mobilecommerceapp.shopping_cart_folder.ShoppingCartActivity;

public class OfflineStoreActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Product> mProducts;
    OfflineAdapter mAdapter;
    int mGridColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_store);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.offline_store_recycler);


        DbUpdateImagesAsyncTask updateImgTask = new DbUpdateImagesAsyncTask(this);
        updateImgTask.execute();
    }


    public void updateDatabaseImageReferences(){
        OfflineSQLOpenHelper dbHelper = OfflineSQLOpenHelper.getMyInstance(this);
        ArrayList<Product> products = dbHelper.getInventoryAsList();

        for(Product product: products){
            int imageRef;
            switch(product.getName()){
                case "Ugly X-Mas Sweater":
                    imageRef = R.drawable.ugly_xmas_sweater;
                    break;
                case "Holy Grail":
                    imageRef = R.drawable.holy_grail;
                    break;
                case "Bottled Lightning":
                    imageRef = R.drawable.bottled_lightning;
                    break;
                case "Monkey Paw":
                    imageRef = R.drawable.monkey_paw;
                    break;
                case "Unconvincing Toupee":
                    imageRef = R.drawable.unconvincing_toupee;
                    break;
                case "Denim Chicken":
                    imageRef = R.drawable.denim_chicken;
                    break;
                case "Egg":
                    imageRef = R.drawable.egg;
                    break;
                case "Rum Ham":
                    imageRef = R.drawable.rumham;
                    break;
                case "Snake Oil":
                    imageRef = R.drawable.snake_oil;
                    break;
                default:
                    imageRef = 0;
                    break;
            }
            dbHelper.updateImageReference(product.getName(),imageRef);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.offline_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, OfflineStoreActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        DbSearchAsyncTask dbSearchAsyncTask = new DbSearchAsyncTask(this, intent);
        dbSearchAsyncTask.execute();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_cart:
                Intent cartIntent = new Intent(this, ShoppingCartActivity.class);
                startActivity(cartIntent);
                return true;
            case R.id.remove_filters://This to refreshes data after search
                mAdapter.replaceData(mProducts);
                OfflineStoreInventory.getInstance().setRelevantInventory(mProducts);
                return true;
            default:
                Log.d("Err: ", "You clicked on something that doesn't exist.");
                return false;
        }
    }

    private class DbUpdateImagesAsyncTask extends AsyncTask<Void, Void, Void>{
        private Context mContext;

        public DbUpdateImagesAsyncTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            DBAssetHelper dbSetup = new DBAssetHelper(OfflineStoreActivity.this);
            dbSetup.getReadableDatabase();

            //This block of code figures out the size of the screen, particularly the width.
            Point size= new Point();
            Display display = getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int display_width = size.x;
            mGridColumns = display_width/720;


            updateDatabaseImageReferences();

            mProducts = OfflineSQLOpenHelper.getMyInstance(mContext).getInventoryAsList();
            OfflineStoreInventory.getInstance().setRelevantInventory(mProducts);

            //If the singleton is empty, let's add products to it.
            OfflineStoreInventory offlineStoreInventory = OfflineStoreInventory.getInstance();

            if (offlineStoreInventory.inventoryIsEmpty()){
                offlineStoreInventory.replaceInventory(mProducts);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Using the displaywidth I determine how many columns will display, my screensize is 1440
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, mGridColumns);
            mAdapter = new OfflineAdapter(mProducts);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class DbSearchAsyncTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;
        private Intent mIntent;
        private ArrayList<Product> mRelevantProducts;

        public DbSearchAsyncTask(Context context, Intent intent) {
            mContext = context;
            mIntent = intent;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (Intent.ACTION_SEARCH.equals(mIntent.getAction())) {
                String query = mIntent.getStringExtra(SearchManager.QUERY);
                mRelevantProducts = OfflineSQLOpenHelper.getMyInstance(mContext).searchProducts(query);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            OfflineStoreInventory.getInstance().setRelevantInventory(mRelevantProducts);
            if (mRelevantProducts.isEmpty()) {
                Toast.makeText(mContext, "No results matched search", Toast.LENGTH_LONG).show();
            } else {
                mAdapter.replaceData(mRelevantProducts);
            }
        }
    }


}
