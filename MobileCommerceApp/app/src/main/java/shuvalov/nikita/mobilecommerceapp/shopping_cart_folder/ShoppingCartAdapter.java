package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;

/**
 * Created by NikitaShuvalov on 11/8/16.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder>{
    ArrayList<Product> mShoppingCartContent;

    public ShoppingCartAdapter(ArrayList<Product> shoppingCartContent){mShoppingCartContent = shoppingCartContent;}

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_shopping_cart,null);
        return new ShoppingCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, int position) {
        if(mShoppingCartContent.isEmpty()){//If cart is empty, display this single viewholder.
            holder.mImageView.setImageResource(R.drawable.ic_empty_cart);
            holder.mNameView.setText(R.string.empty_cart);
            holder.mCostView.setText("");
        }else{
            //ToDo: add swipe listener to allow user to swipe-remove items.
            //ToDo: Add an "x" to allow user to delete that way as well.
            Product product = mShoppingCartContent.get(holder.getAdapterPosition());
            holder.mNameView.setText(product.getName());
//            holder.mImageView.setImageResource(product.getImageRef());
            holder.mCostView.setText(String.valueOf(product.getPrice()));
            holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Remove item from shopping cart?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ShoppingCartContent.getInstance().removeItemByIndex(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).create().show();

                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() { //If the shopping cart is empty, return one holder to be populated.
        if (mShoppingCartContent.size()==0){
            return 1;
        }else {
            return mShoppingCartContent.size();
        }
    }
}


class ShoppingCartViewHolder extends RecyclerView.ViewHolder{
    ImageView mImageView;
    TextView mNameView, mCostView;
    RelativeLayout mLayout;


    public ShoppingCartViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        mNameView = (TextView)itemView.findViewById(R.id.item_name);
        mCostView = (TextView)itemView.findViewById(R.id.cost_text);
        mLayout = (RelativeLayout)itemView.findViewById(R.id.holder_layout);


    }
}