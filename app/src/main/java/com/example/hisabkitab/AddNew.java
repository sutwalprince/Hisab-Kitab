package com.example.hisabkitab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hisabkitab.data.MyDbHandler;
import com.example.hisabkitab.model.Payment;

public class AddNew extends AppCompatActivity {
    MainActivity mainActivity = new MainActivity();
    EditText item ;
    EditText cost;
    String color ;
    CheckBox incoming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        item = findViewById(R.id.item1);
        cost = findViewById(R.id.cost1);
        incoming = findViewById(R.id.checkBox);


    }
    public void submit(View view){

        MyDbHandler db = new MyDbHandler(AddNew.this);
        Payment price = new Payment();
        if (incoming.isChecked()) {
            color = "0";
        }if (!incoming.isChecked()){
            color = "1";
        }

        if (item.getText().toString().isEmpty()){
            item.setError("Cannot be empty");
            return;
        }
        if (cost.getText().toString().isEmpty()){
            cost.setError("Cannot be empty");
            return;
        }

        price.setCost(cost.getText().toString());
        price.setItem(item.getText().toString());
        price.setColor(color);
        db.addPayment(price);
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        mainActivity.display();
        finish();
    }

}