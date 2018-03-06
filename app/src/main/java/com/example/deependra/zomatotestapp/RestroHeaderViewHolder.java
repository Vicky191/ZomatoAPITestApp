package com.example.deependra.zomatotestapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class RestroHeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView tvHeader;

    RestroHeaderViewHolder(View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.cuisineHeader);
    }

    TextView getTvHeader() {
        return tvHeader;
    }
}
