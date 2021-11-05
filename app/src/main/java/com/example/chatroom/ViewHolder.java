package com.example.chatroom;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView message;

    public ViewHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.username);
        message = itemView.findViewById(R.id.message_text);
    }
}