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
import com.example.ptsgenap10rpl228.model.RecommendationsModel;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsAdapter extends RecyclerView.Adapter<RecommendationsAdapter.ListViewHolder> {
    private List<RecommendationsModel> dataList, dataListFull;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public RecommendationsAdapter(Context mContext, ArrayList<RecommendationsModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        dataListFull = new ArrayList<>(dataList);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_recommendations, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.img_restaurant.setImageResource(dataList.get(position).getImageID());
        holder.tv_restaurant.setText(dataList.get(position).getRestName());
        holder.tv_location.setText(dataList.get(position).getRestLocation());
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
            tv_restaurant = itemView.findViewById(R.id.tv_restaurant_rvRecommendations);
            tv_location = itemView.findViewById(R.id.tv_location_rvRecommendations);
            img_restaurant = itemView.findViewById(R.id.img_restaurant_rvRecommendations);
            rv_layout = itemView.findViewById(R.id.rv_layout_rvRecommendations);

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
