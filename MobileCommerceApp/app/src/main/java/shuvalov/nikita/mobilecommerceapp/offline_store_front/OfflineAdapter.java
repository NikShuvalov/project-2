package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;


/**
 * Created by NikitaShuvalov on 11/2/16.
 */

public class OfflineAdapter extends RecyclerView.Adapter<OfflineViewHolder> {

    ArrayList<Product> mInventory;
    int mDisplay_width;

    public OfflineAdapter(ArrayList<Product> inventory,  int display_width) {
        mInventory = inventory;
        mDisplay_width = display_width;
    }

    @Override
    public OfflineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_offline,parent, false);
        return new OfflineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfflineViewHolder holder, int position) {

        holder.mNameView.setText(mInventory.get(position).getName());
        holder.mPriceView.setText(String.valueOf(mInventory.get(position).getPrice()));
        holder.mDescView.setText(mInventory.get(position).getDescription());



    }

    @Override
    public int getItemCount() {
        return mInventory.size();
    }

}

class OfflineViewHolder extends RecyclerView.ViewHolder{
    TextView mNameView, mDescView, mPriceView;
    ImageView mImageView;
    CardView mCardView;


    public OfflineViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.cardview_container);
        mNameView = (TextView) itemView.findViewById(R.id.item_name);
        mDescView = (TextView) itemView.findViewById(R.id.description_view);
        mPriceView = (TextView) itemView.findViewById(R.id.price_view);
        mImageView = (ImageView) itemView.findViewById(R.id.item_image);
    }
}