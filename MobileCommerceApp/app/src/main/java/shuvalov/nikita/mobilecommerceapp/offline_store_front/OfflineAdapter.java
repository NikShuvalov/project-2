package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;


/**
 * Created by NikitaShuvalov on 11/2/16.
 */

public class OfflineAdapter extends RecyclerView.Adapter<OfflineViewHolder> {

    private ArrayList<Product> mInventory;


    public OfflineAdapter(ArrayList<Product> inventory) {
        mInventory = inventory;
    }

    @Override
    public OfflineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_offline,parent, false);
        return new OfflineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OfflineViewHolder holder, int position) {

        holder.mNameView.setText(mInventory.get(position).getName());
        holder.mPriceView.setText("$"+String.valueOf(mInventory.get(position).getPrice()));
        holder.mImageView.setImageResource(mInventory.get(position).getImageRef());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OfflineDetailActivity.class);
                intent.putExtra(MainActivity.ITEM_NAME_KEY,mInventory.get(holder.getAdapterPosition()).getName());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInventory.size();
    }


    public void replaceData(ArrayList<Product> newList){
        mInventory = newList;
        notifyDataSetChanged();
    }
}

class OfflineViewHolder extends RecyclerView.ViewHolder{
    TextView mNameView, mPriceView;
    ImageView mImageView;
    CardView mCardView;


    public OfflineViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.cardview_container);
        mNameView = (TextView) itemView.findViewById(R.id.item_name);
        mPriceView = (TextView) itemView.findViewById(R.id.price_view);
        mImageView = (ImageView) itemView.findViewById(R.id.item_image);
    }

}