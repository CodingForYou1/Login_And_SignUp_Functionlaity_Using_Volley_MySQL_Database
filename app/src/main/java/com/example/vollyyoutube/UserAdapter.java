package com.example.vollyyoutube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vollyyoutube.databinding.CellUserLayoutBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.holder> {

    ArrayList<UserModel> userList;
    Context context;

    public UserAdapter(ArrayList<UserModel> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(context).inflate(R.layout.cell_user_layout,parent,false);
     return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.holder holder, int position) {

        UserModel userModel = userList.get(position);

        holder.binding.name.setText(userModel.getFirst_name() +" "+userModel.getLast_name());
        holder.binding.email.setText(userModel.getEmail());

        Glide.with(context)
                .load(userModel.getAvatar())
                .into(holder.binding.profileImage);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class holder extends RecyclerView.ViewHolder{

        CellUserLayoutBinding binding;

        public holder(@NonNull View itemView) {
            super(itemView);
            binding = CellUserLayoutBinding.bind(itemView);
        }
    }
}
