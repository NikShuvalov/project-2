package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by NikitaShuvalov on 11/9/16.
 */

public class SCItemTouchHelperAdapterCallback extends ItemTouchHelper.Callback{

//Enables swipes on items in the shopping cart to remove from list.
    private final SCItemTouchHelperAdapter mAdapter;

    public SCItemTouchHelperAdapterCallback(SCItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.END | ItemTouchHelper.START;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }

    @Override
    public boolean isItemViewSwipeEnabled() { return true; }
}

