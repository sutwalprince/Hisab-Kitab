package com.example.hisabkitab.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hisabkitab.MainActivity;
import com.example.hisabkitab.Update;
import com.example.hisabkitab.model.Payment;
import com.example.hisabkitab.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + Params.TABLE_NAME +"("+Params.KEY_ID +" INTEGER PRIMARY KEY,"+Params.KEY_ITEM
                + " TEXT, "+Params.KEY_COST +" TEXT,"+Params.KEY_COLOR +" TEXT)";
//        Log.d("prince","table being create " + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void addPayment(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_ITEM,payment.getItem());
        values.put(Params.KEY_COST,payment.getCost());
        values.put(Params.KEY_COLOR,payment.getColor());

        db.insert(Params.TABLE_NAME,null,values);
//        Log.d("prince","Successfully inserted " );
        db.close();



    }

    public List<Payment> getAllPayments(){
        List<Payment> paymentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;

        Cursor cursor = db.rawQuery(select,null);

        if (cursor.moveToFirst()){
            do {
                Payment payment = new Payment();
                payment.setId(Integer.parseInt(cursor.getString(0)));
                payment.setItem(cursor.getString(1));
                payment.setCost(cursor.getString(2));
                payment.setColor(cursor.getString(3));
                paymentList.add(payment);
            }while (cursor.moveToNext());
        }
        return paymentList;
    }

    public int updatePayment(Payment payment,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_ITEM,payment.getItem());
        values.put(Params.KEY_COST,payment.getCost());
        values.put(Params.KEY_COLOR,payment.getColor());

//        db.close();
//        Log.d("prince1","Successfully " +id+"  "+payment.getCost()+payment.getItem()+ payment.getId() );


        return db.update(Params.TABLE_NAME,values,Params.KEY_ID + "=?",
                new String[]{String.valueOf(id)});


    }

    public void deletePaymentById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID +"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deletePaymentByItem(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ITEM +"=?",new String[]{item});
        db.close();
    }
    public int getCount(){
        String query = "SELECT * FROM "+ Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }
    @SuppressLint("Range")
    public int getSum() {
//        String query = "SELECT SUM("+Params.KEY_COST+") FROM "+ Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
////        Cursor cursor = db.rawQuery(query,null);
//        Cursor sum = db.rawQuery(query,null);

        Cursor cursor = db.rawQuery("SELECT SUM(" + Params.KEY_COST +
                ") as Total FROM " + Params.TABLE_NAME, null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("Total"));

        }
        return total;
//        Log.d("sumd", "cc" + total);
//        return cursor.getCount();
    }


}
