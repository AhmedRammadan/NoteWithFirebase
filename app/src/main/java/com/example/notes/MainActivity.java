package com.example.notes;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<NoteContent> notes;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes=new ArrayList<>();
        list=findViewById(R.id.list_notes);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Note");
    }
    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notes.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    NoteContent note = dataSnapshot1.getValue(NoteContent.class);
                    notes.add(note);
                }
                NoteAdapter adapter =new NoteAdapter(MainActivity.this,0,notes);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void add(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View dialogShow= getLayoutInflater().inflate(R.layout.add_note,null);
        builder.setView(dialogShow);
        builder.create();
        builder.show();
        final EditText et_title=dialogShow.findViewById(R.id.title);
        final EditText et_note=dialogShow.findViewById(R.id.note);
        Button add=dialogShow.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(et_note.getText().toString().equals("")||et_title.getText().toString().equals(""))){
                    String id =myRef.push().getKey();
                    NoteContent note = new NoteContent(id,et_title.getText().toString(),et_note.getText().toString());
                    myRef.child(id).setValue(note);
                    //myRef.child(id).child("notee").child("ahmed").setValue(note);
                }
                else if (et_note.getText().toString().equals("")&&et_title.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "pleas Enter Title and Your Note", Toast.LENGTH_SHORT).show();
                }
                else if (et_title.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "pleas Enter Title for Note", Toast.LENGTH_SHORT).show();
                }
                else if (et_note.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "pleas Enter Your Note", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}