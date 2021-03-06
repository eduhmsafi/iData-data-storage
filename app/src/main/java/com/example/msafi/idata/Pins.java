package com.example.msafi.idata;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pins extends Fragment {
Database_Pins database_pins;
Context context;
SimpleCursorAdapter adapter;

    public Pins() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pins, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        context = getContext();
        database_pins = new Database_Pins(context);
        if(view != null){
            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InsertPin.class);
                    startActivity(intent);
                }
            });
        }
       displayView();
    }
    public void displayView(){
        final Cursor cursor = database_pins.getData();
        final String[] columns = new String[]{
                Database_Pins.NAME,
                Database_Pins.VALUE,
                Database_Pins.UID,
        };
        int[] to = new int[]{
                R.id.finalName,
        };
        adapter = new SimpleCursorAdapter(context, R.layout.view_data, cursor, columns, to, 0);
        View view = getView();
        final ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor1 = (Cursor)listView.getItemAtPosition(i);
                String a1 = cursor1.getString(cursor.getColumnIndexOrThrow("name"));
                String a2 = cursor1.getString(cursor.getColumnIndexOrThrow("value"));
                String a3 = cursor1.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(context, View_pin.class);
                intent.putExtra("name", a1);
                intent.putExtra("value", a2);
                intent.putExtra("id", a3);
                startActivity(intent);
            }
        });
    }

}
