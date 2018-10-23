package com.example.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> arrayList;
    ListView list_item;
    TextView text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list_item = findViewById(R.id.list_item);
        text = findViewById(R.id.text);
        arrayList=new ArrayList<>();
        Intent intent =getIntent();
        String id =intent.getStringExtra("id");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Note").child(id);
        text.setText(" "+myRef);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                String title = dataSnapshot.child("title").getValue(String.class);
                String note = dataSnapshot.child("note").getValue(String.class);
                String date = dataSnapshot.child("date").getValue(String.class);
                arrayList.add(title);
                arrayList.add(note);
                arrayList.add(date);
                ArrayAdapter<String> adapter =new ArrayAdapter<>(MainActivity2.this,android.R.layout.simple_list_item_1,arrayList);
                list_item.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
