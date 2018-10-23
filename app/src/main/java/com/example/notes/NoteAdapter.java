package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<NoteContent> {
    public NoteAdapter(Context context, int resource, ArrayList<NoteContent> notes) {
        super(context, 0, notes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteContent getnote=getItem(position);
        View listItemView=convertView;
        if (listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.item_note,parent,false);
        }

        TextView title=listItemView.findViewById(R.id.title);
        title.setText(getnote.getTitle());

        TextView note=listItemView.findViewById(R.id.note);
        note.setText(getnote.getNote());

        TextView date=listItemView.findViewById(R.id.date);
        date.setText(getnote.getDate());

        return listItemView;
    }
}