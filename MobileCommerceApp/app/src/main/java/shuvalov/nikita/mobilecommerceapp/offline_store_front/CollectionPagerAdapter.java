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

    private ArrayList<Product> mFilteredInventory;

    public CollectionPagerAdapter(FragmentManager fm, ArrayList<Product> relevantInventory) {
        super(fm);
        mFilteredInventory = relevantInventory;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = OfflineDetailFragment.newInstance(mFilteredInventory.get(position).getName());
        return fragment;
    }

    @Override
    public int getCount() {
        return mFilteredInventory.size();
    }
}
