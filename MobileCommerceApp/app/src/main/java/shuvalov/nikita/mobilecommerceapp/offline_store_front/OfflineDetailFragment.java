package shuvalov.nikita.mobilecommerceapp.offline_store_front;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import shuvalov.nikita.mobilecommerceapp.MainActivity;
import shuvalov.nikita.mobilecommerceapp.Product;
import shuvalov.nikita.mobilecommerceapp.R;


public class OfflineDetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mItemName;

    private OnFragmentClickListener mListener;

    public OfflineDetailFragment() {
        // Required empty public constructor
    }

    public static OfflineDetailFragment newInstance(String itemName) {
        OfflineDetailFragment fragment = new OfflineDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, itemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offline_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("Rancor", "ON VIEW CREATED RAN");
        super.onViewCreated(view, savedInstanceState);

        TextView nameView = (TextView) view.findViewById(R.id.item_name);
        TextView detailsView = (TextView)view.findViewById(R.id.item_details_text);
        ImageView itemImage = (ImageView)view.findViewById(R.id.item_image);
        TextView priceView = (TextView)view.findViewById(R.id.cost_text);
        Button buyButton = (Button)view.findViewById(R.id.buy_button);

        Product selectedProduct = OfflineSQLOpenHelper.getMyInstance(view.getContext()).getProductByName(mItemName);
        nameView.setText(selectedProduct.getName());
        detailsView.setText(selectedProduct.getDescription());
        priceView.setText(String.valueOf(selectedProduct.getPrice()));
        buyButton.setOnClickListener(new View.OnClickListener() { //When button is clicked, should add item to shopping cart.
            @Override
            public void onClick(View view) {
                //For now going to send user to shopping cart activity instead of adding to shopping cart.
                //ToDo:Change functionality after debugging.
                onButtonPressed();
            }
        });
        //ToDo: Have image reference set to itemImageView
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction(mItemName);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentClickListener) {
            mListener = (OnFragmentClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBuyClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentClickListener {
        void onFragmentInteraction(String itemName);
    }
}
