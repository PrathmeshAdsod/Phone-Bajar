package com.example.phonebajar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImg;
    Button insertButton;
    EditText detailNum, detailName, quantity;
    TextView phoneName, priceNum, detailDescription;


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImg = findViewById(R.id.detailImg);
        insertButton = findViewById(R.id.insertButton);
        detailNum = findViewById(R.id.detailNum);
        quantity = findViewById(R.id.quantity);
        detailName = findViewById(R.id.detailName);
        phoneName = findViewById(R.id.phoneName);
        priceNum = findViewById(R.id.priceNum);
        detailDescription = findViewById(R.id.detailDescription);

        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {


            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");

            detailImg.setImageResource(image);
            priceNum.setText(String.format("%d", price));
            phoneName.setText(name);
            detailDescription.setText(description);


            insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            detailName.getText().toString(),
                            detailNum.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(quantity.getText().toString())

                    );
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Ordered ", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(DetailActivity.this, "Some Error Occured", Toast.LENGTH_LONG).show();
                }

            });
        } else {


            final int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);


            detailImg.setImageResource(image);
            priceNum.setText(String.format("%d", cursor.getInt(3)));
            phoneName.setText(cursor.getString(6));
            detailDescription.setText(cursor.getString(5));
    //        quantity.setText(cursor.getInt(7));

            detailName.setText(cursor.getString(1));
//            detailNum.setText(cursor.getString(2));

            insertButton.setText("Update Now");

            insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // isUpdated created by us
                    boolean isUpdated = helper.updateOrder(
                            detailName.getText().toString(),
                            detailNum.getText().toString(),
                            Integer.parseInt(priceNum.getText().toString()),
                            image,
                            detailDescription.getText().toString(),
                            phoneName.getText().toString(),
                            2,
                            id
                    );
                    if (isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            });


        }


    }
}