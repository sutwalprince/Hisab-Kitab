package com.example.hisabkitab;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hisabkitab.data.MyDbHandler;

public class Edit extends AppCompatActivity {
    MainActivity mainActivity = new MainActivity();

    Button delete  ;
    AlertDialog.Builder builder;
    public static final String AGAIN_ID= "com.example.hisabkitab.AGAIN_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.ID,0);

        delete = findViewById(R.id.delete);
        builder = new AlertDialog.Builder(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Alert")
                        .setMessage("Do you want to delete ")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MyDbHandler db = new MyDbHandler(Edit.this);

                                db.deletePaymentById(id);
                                Toast.makeText(Edit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();

                                mainActivity.display();
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });







    }

    public void openUpdate(View view){
        Intent intent1 = getIntent();

        int id = intent1.getIntExtra(MainActivity.ID,0);

        Intent intent = new Intent(Edit.this ,Update.class);
        intent.putExtra(AGAIN_ID,id);

        startActivity(intent);
    }



}