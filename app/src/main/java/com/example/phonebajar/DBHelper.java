package com.example.phonebajar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.phonebajar.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

   final static String DBName = "mydatabase.db";
   final static int DBVersion = 2;


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null,DBVersion );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +                                               // Column_Name  data_type
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "phoneName text)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP table if EXISTS orders ");

        onCreate(sqLiteDatabase);

    }
    public boolean insertOrder(String name,String phone,int price,int image,String description,String phonename,int quantity) {

            SQLiteDatabase database = getReadableDatabase();
            ContentValues values = new ContentValues();


            values.put("name", name);    // index 1
            values.put("phone", phone);  // index 2
            values.put("price",price);   // index 3
            values.put("image", image);  // index 4
            values.put("description",description);   //index 5
            values.put("phonename",phonename);  // index 6
            values.put("quantity",quantity);  // index 7

           long id = database.insert("orders",null,values);

           if(id <= 0) {
               return false;
           } else {
               return true;

           }


    }

    // For Creating the Orders

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,phonename,image,price from orders",null);

        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
             OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();

        return orders;           // Here orders will return and not getOrders
    }

    public Cursor getOrderById(int id) {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = " + id,null);

        if(cursor != null )
            cursor.moveToFirst();


//      Dont close Cursor and Database here

        return cursor;           // Here orders will return and not getOrders
    }

    public boolean updateOrder(String name,String phone,int price,int image,String description,String phonename,int quantity,int id) {



        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put("name", name);    // index 1
        values.put("phone", phone);  // index 2
        values.put("price",price);   // index 3
        values.put("image", image);  // index 4
        values.put("description",description);   //index 5
        values.put("phonename",phonename);  // index 6
        values.put("quantity",quantity);  // index 7

        long row = database.update("orders",values,"id="+id,null);

        if(row <= 0) {
            return false;
        } else {
            return true;

        }

    }
    public int deleteOrder (String id ) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }


}
