package com.example.chatroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<Message> msgs;

    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<Message> msgs) {
        this.msgs = msgs;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String msg = msgs.get(position).message;
        String username = msgs.get(position).username;
        holder.username.setText(username);
        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }
}
