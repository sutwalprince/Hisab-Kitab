package com.example.hisabkitab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hisabkitab.data.MyDbHandler;
import com.example.hisabkitab.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ID = "com.example.hisabkitab.ID";
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display();
//        Log.d("prince", "total = " + db.getCount());
    }

    public void addNew(View view) {
        Intent intent = new Intent(this,AddNew.class);

        startActivity(intent);


    }
    public void display(){
        MyDbHandler db = new MyDbHandler(MainActivity.this);
        List<Payment> allPayments = db.getAllPayments();
        int sum = db.getSum();

        textView = findViewById(R.id.textView);
        textView.setText("Total Money Wasted = "+sum);
        //display in listview
        ArrayList<String> payments = new ArrayList<>();
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String text = "item " + i +" " + ((TextView) view).getText().toString();
                Intent intent = new Intent(MainActivity.this, Edit.class);

                intent.putExtra(ID, allPayments.get(i).getId());
                startActivity(intent);
            }

        });


        for (Payment payment : allPayments) {
              int index = allPayments.indexOf(payment);

//
//            if (payment.getColor().equals(0)) {
//                listView.setBackgroundColor(Color.RED);  // it changes ALL items...
//
//            }
//            if (payment.getColor().equals(1)) {
//                listView.setBackgroundColor(Color.GREEN);  // it changes ALL items...
//
//            }

//            Log.d("prince", "\n" + "ID: " + payment.getId() + "\n" +
//                    "item: " + payment.getItem() + "\n" +
//                    "cost: " + payment.getCost());
            payments.add(index+1 + " )\t\t" + payment.getItem() + "\t\t\t (" + payment.getCost() + ")"+ payment.getColor() );
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, payments);

        listView.setAdapter(arrayAdapter);
    }
}