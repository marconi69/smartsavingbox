package com.example.takotlin;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListHistoryHolder extends RecyclerView.ViewHolder {

    public TextView tvUser;
    public TextView tvTanggal;

    public ListHistoryHolder(View itemView) {
        super(itemView);
        tvUser = itemView.findViewById(R.id.tv_user);
        tvTanggal = itemView.findViewById(R.id.tv_tgl);
    }

    public void bindToHistory(AdapterListHistory adpListHistory){
        tvUser.setText(adpListHistory.username);
        tvTanggal.setText(adpListHistory.tanggaldanwaktu);
    }
}
