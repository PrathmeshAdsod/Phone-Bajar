package com.example.phonebajar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebajar.Adapters.my_adapters;
import com.example.phonebajar.Models.my_models;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<my_models> list = new ArrayList<>();




        list.add(new my_models(R.drawable.oppo,"Opp","20000","The best Sel Camera Phone"));
        list.add(new my_models(R.drawable.oppof,"Opp","25000","The best Se Camera Phone"));
        list.add(new my_models(R.drawable.iphone,"Opp","30000","The best Self Camera Phone"));
        list.add(new my_models(R.drawable.lava,"Opp","9000","The best Self Camera Phone"));
        list.add(new my_models(R.drawable.oppof,"Opp","85000","The best Sel Camera Phone"));




        my_adapters adapters = new my_adapters(list,this);
        recyclerView.setAdapter(adapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders :
                Intent intent = new Intent(MainActivity.this,OrdersActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}