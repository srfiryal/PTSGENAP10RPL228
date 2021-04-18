package com.example.ptsgenap10rpl228.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsgenap10rpl228.model.LandingModel;
import com.example.ptsgenap10rpl228.R;

import java.util.ArrayList;
import java.util.List;

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.ListViewHolder> {
    private List<LandingModel> dataList;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public LandingAdapter(Context mContext, ArrayList<LandingModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_landing, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.img_vector.setImageResource(dataList.get(position).getImageID());
        holder.tv_title.setText(dataList.get(position).getMainFeature());
        holder.tv_desc.setText(dataList.get(position).getFeatureDesc());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_desc;
        private ImageView img_vector;
        private RelativeLayout rv_layout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_rvLanding);
            tv_desc = itemView.findViewById(R.id.tv_desc_rvLanding);
            img_vector = itemView.findViewById(R.id.img_rvLanding);
            rv_layout = itemView.findViewById(R.id.rv_layout_rvLanding);

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
