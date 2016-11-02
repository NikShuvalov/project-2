package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import shuvalov.nikita.mobilecommerceapp.R;


/**
 * Created by NikitaShuvalov on 11/2/16.
 */

public class OfflineAdapter extends RecyclerView.Adapter<OfflineViewHolder> {

//    public OfflineAdapter(ArrayList<Product> inventory) {
//
//
//    }

    @Override
    public OfflineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(OfflineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class OfflineViewHolder extends RecyclerView.ViewHolder{
    TextView mNameView, mDescView, mPriceView;
    ImageView mImageView;


    public OfflineViewHolder(View itemView) {
        super(itemView);

        mNameView = (TextView) itemView.findViewById(R.id.item_name);
        mDescView = (TextView) itemView.findViewById(R.id.description_view);
        mPriceView = (TextView) itemView.findViewById(R.id.price_view);
        mImageView = (ImageView) itemView.findViewById(R.id.item_image);
    }
}