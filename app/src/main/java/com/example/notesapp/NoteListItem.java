package com.example.notesapp;

public class NoteListItem {

    int imageResource;
    String heading,content;

    NoteListItem(int imageResource,String heading,String content)
    {
        this.imageResource = imageResource;
        this.heading = heading;
        this.content = content;
    }


    public int getImageResource()
    {
        return imageResource;
    }

    public String getHeading()
    {
        return heading;
    }

    public String getContent()
    {
        return content;
    }

}
