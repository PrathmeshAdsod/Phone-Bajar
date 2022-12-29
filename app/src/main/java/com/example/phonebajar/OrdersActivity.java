package com.example.phonebajar;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebajar.Adapters.OrdersAdapters;
import com.example.phonebajar.Models.OrdersModel;


import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView orderRecyclerView;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);

      //  ArrayList<OrdersModel> list = new ArrayList<>();

        // Code for Executing in orders
        // For creating the orders
        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();


        OrdersAdapters adapters = new OrdersAdapters(list,this);
        orderRecyclerView.setAdapter(adapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        orderRecyclerView.setLayoutManager(layoutManager);




    }
}