package com.example.hisabkitab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hisabkitab.data.MyDbHandler;
import com.example.hisabkitab.model.Payment;

public class Update extends AppCompatActivity {

    EditText item1;
    EditText cost1;
    Button update1;
    CheckBox incoming;
    String color;
    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        cost1 = findViewById(R.id.cost1);
        item1 = findViewById(R.id.item1);
        incoming = findViewById(R.id.checkBox);

        update1 = findViewById(R.id.update1);

        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra(Edit.AGAIN_ID,0);
                MyDbHandler db = new MyDbHandler(Update.this);
                Payment up_price = new Payment();
                if (cost1.getText().toString().isEmpty()){
                    cost1.setError("Cannot be empty");
                }if (item1.getText().toString().isEmpty()){
                    item1.setError("Cannot be empty");
                }
                if (incoming.isChecked()) {
                    color = "0";
                }if (!incoming.isChecked()){
                    color = "1";
                }
                up_price.setCost(cost1.getText().toString());
                up_price.setItem(item1.getText().toString());
                up_price.setColor(color);
                db.updatePayment(up_price,id);
                Toast.makeText(Update.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                mainActivity.display();
            }
        });

    }
}