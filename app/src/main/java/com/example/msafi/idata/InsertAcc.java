package com.example.msafi.idata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertAcc extends AppCompatActivity {
EditText name, value;
AccDatabase accDatabase;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_acc);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        name = findViewById(R.id.insert_name);
        value = findViewById(R.id.insert_acc);
        submit = findViewById(R.id.submit);
        accDatabase = new AccDatabase(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1 = name.getText().toString();
                String a2 = value.getText().toString();
                if(a1.isEmpty()||a2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Data and try again!", Toast.LENGTH_SHORT).show();
                }
                else{
                    long id = accDatabase.insertData(a1, a2);
                    if(id<=0){
                        Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        value.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(),"Successfuly inserted!", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        value.setText("");
                    }
                }
            }
        });
    }
}
