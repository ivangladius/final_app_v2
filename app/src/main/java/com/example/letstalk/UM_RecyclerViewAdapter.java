package com.example.letstalk;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class UM_RecyclerViewAdapter
        extends RecyclerView.Adapter<UM_RecyclerViewAdapter.MyViewHolder> {

    static Context context;


    ArrayList<UserModel> userModels;
    static String primaryKey;

    // pass the userModels to the Constructor to later display them
    public UM_RecyclerViewAdapter(Context context, ArrayList<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;

        try {
            primaryKey = FileUtility.readFromFile("primary_key.txt", context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // this displays the RecyclerView
    @NonNull
    @Override
    public UM_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new UM_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UM_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // we created a list of All friends now this
        // command cycles trough all users in userModels
        // and sets the text according to their username
        holder.btnUsername.setText(userModels.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public static class MyViewHolder
            extends RecyclerView.ViewHolder {

        Button btnUsername = itemView.findViewById(R.id.btnUsername);

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUsername.setOnClickListener(view -> {

                // if button is clicked of one of the friends jump to the chat
                // and pass the username of the button you clicked on
                // so if you click on your friend peter
                // peter will be passed to ChatActivity
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatUserName", btnUsername.getText()); //Optional parameters
                intent.putExtra("key", primaryKey);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                context.startActivity(intent);
            });
        }

    }

}
