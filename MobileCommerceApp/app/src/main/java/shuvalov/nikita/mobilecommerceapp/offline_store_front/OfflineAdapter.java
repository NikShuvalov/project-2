package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import shuvalov.nikita.mobilecommerceapp.R;


/**
 * Created by NikitaShuvalov on 11/2/16.
 */

public class OfflineAdapter extends RecyclerView.Adapter<> {
}

class OfflineViewHolder extends RecyclerView.ViewHolder{
    TextView mNameView, mDescView, mPriceView;
    ImageView mImageView;


    public OfflineViewHolder(View itemView) {
        super(itemView);

        mNameView = (TextView) itemView.findViewById(R.id.item_name);
        mDescView = (TextView)

    }
}