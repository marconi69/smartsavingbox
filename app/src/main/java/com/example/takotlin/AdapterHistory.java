package com.example.takotlin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HistoryViewHolder> {
    List<History> datahistory;

    public AdapterHistory(List<History> datahistory) {
        this.datahistory = datahistory;
    }



    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_history, parent, false);




        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.username.setText(datahistory.get(position).getUsername());
        holder.waktu.setText(datahistory.get(position).getTanggaldanwaktu());
        holder.status.setText(datahistory.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return datahistory.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView waktu;
        public TextView status;

        public HistoryViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.txtusername);
            waktu = (TextView) view.findViewById(R.id.txtWaktu);
            status = (TextView) view.findViewById(R.id.txtStatus);
        }
    }
}
