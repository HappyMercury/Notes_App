package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<NoteListItem> {

    public CustomAdapter(@NonNull Context context, ArrayList<NoteListItem> list) {
        super(context, 0,list);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        NoteListItem currentNote = getItem(position);

        ImageView image = listItem.findViewById(R.id.listItemImageView);
        image.setImageResource(currentNote.getImageResource());

        TextView name = listItem.findViewById(R.id.listItemHeadingTextView);
        name.setText(currentNote.getHeading());

        TextView release = listItem.findViewById(R.id.listItemContentTextView);
        release.setText(currentNote.getContent());

        return listItem;
    }
}
