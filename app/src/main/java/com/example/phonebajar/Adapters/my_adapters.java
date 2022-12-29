package com.example.phonebajar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebajar.DetailActivity;
import com.example.phonebajar.Models.my_models;
import com.example.phonebajar.R;

import java.util.ArrayList;


public class my_adapters extends RecyclerView.Adapter<my_adapters.viewholder> {

    ArrayList<my_models> list;
    Context context;

    public my_adapters(ArrayList<my_models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.sample_smartphone,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final my_models models = list.get(position);
        holder.oppof.setImageResource(models.getImage());
        holder.mainName.setText(models.getName());
        holder.price.setText(models.getPrice());
        holder.description.setText(models.getDescription());

        // another method to apply onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image",models.getImage());
                intent.putExtra("price",models.getPrice());
                intent.putExtra("description",models.getDescription());
                intent.putExtra("name",models.getName());
                intent.putExtra("type",1);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        ImageView oppof;
        TextView mainName,price,description;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            oppof = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.myPrice);
            description = itemView.findViewById(R.id.description);

        }
    }
}
