package com.example.ptsgenap10rpl228.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsgenap10rpl228.R;
import com.example.ptsgenap10rpl228.model.OurPicksModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OurPicksAdapter extends RecyclerView.Adapter<OurPicksAdapter.ListViewHolder> {
    private List<OurPicksModel> dataList, dataListFull;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public OurPicksAdapter(Context mContext, ArrayList<OurPicksModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        dataListFull = new ArrayList<>(dataList);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_our_picks, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tv_restaurant.setText(dataList.get(position).getRestaurantName());
        holder.tv_location.setText(dataList.get(position).getRestaurantLocation());
        Picasso.get()
                .load(dataList.get(position).getImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.img_restaurant);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_restaurant, tv_location;
        private ImageView img_restaurant;
        private RelativeLayout rv_layout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv_restaurant = itemView.findViewById(R.id.tv_restaurant_rvOurPicks);
            tv_location = itemView.findViewById(R.id.tv_location_rvOurPicks);
            img_restaurant = itemView.findViewById(R.id.img_restaurant_rvOurPicks);
            rv_layout = itemView.findViewById(R.id.rv_layout_rvOurPicks);

            rv_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
