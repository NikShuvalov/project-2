package shuvalov.nikita.mobilecommerceapp.shopping_cart_folder;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public void onBindViewHolder(ShoppingCartViewHolder holder, int position) {
        if(mShoppingCartContent.isEmpty()){//If cart is empty, display this single viewholder.
            holder.mImageView.setImageResource(R.drawable.ic_empty_cart);
            holder.mNameView.setText(R.string.empty_cart);
            holder.mCostView.setText("");
        }else{
            Product product = mShoppingCartContent.get(holder.getAdapterPosition());
            holder.mNameView.setText(product.getName());
//            holder.mImageView.setImageResource(product.getImageRef());
            holder.mCostView.setText(String.valueOf(product.getPrice()));
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


    public ShoppingCartViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        mNameView = (TextView)itemView.findViewById(R.id.item_name);
        mCostView = (TextView)itemView.findViewById(R.id.cost_text);


    }
}