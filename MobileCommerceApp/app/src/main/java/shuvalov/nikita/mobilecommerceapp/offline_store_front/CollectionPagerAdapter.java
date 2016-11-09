package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;

/**
 * Created by NikitaShuvalov on 11/9/16.
 */

public class CollectionPagerAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Product> mStoreInventory;

    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
        mStoreInventory = OfflineStoreInventory.getInstance().getStoreFrontInventory();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = OfflineDetailFragment.newInstance(mStoreInventory.get(position).getName());
        return fragment;
    }

    @Override
    public int getCount() {
        return mStoreInventory.size();
    }
}
