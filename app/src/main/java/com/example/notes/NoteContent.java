package com.example.notes;

import android.text.format.DateFormat;

import java.util.Date;

public class NoteContent {
    private String title,note,id;
    private String date;
    public NoteContent(String id,String title, String note) {
        this.note = note;
        this.title = title;
        this.id = id;
    }

    public NoteContent() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        Date d = new Date();
        CharSequence s  = DateFormat.format("M, d, yyyy ", d.getTime());
        date= (String) s;
        return date;
    }
}