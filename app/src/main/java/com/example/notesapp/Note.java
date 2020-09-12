package com.example.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Note extends AppCompatActivity {

    EditText headingEditText,contentEditText;
    private String heading,content;
    FloatingActionButton fab2;
    String h = "";

    DBHelper dbHelper;

    public static final String SQLITE_CREATE_ENTRIES = "CREATE TABLE "+ DatabaseSchema.DatabaseEntry.TABLE_NAME
            + " (" + DatabaseSchema.DatabaseEntry._ID+"INTEGER PRIMARY KEY,"+DatabaseSchema.DatabaseEntry.COLUMN_NAME_HEADING+" TEXT,"+ DatabaseSchema.DatabaseEntry.COLUMN_NAME_CONTENT
            +" TEXT)";

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you don't want to save?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        setResult(RESULT_CANCELED);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
        .setCancelable(false)
        .setIcon(R.drawable.ic_cancel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        headingEditText = findViewById(R.id.headingEditText);
        contentEditText = findViewById(R.id.contentEditText);

        Intent intent = getIntent();

        h = intent.getStringExtra("heading");

        headingEditText.setText(dbHelper.getNoteHeading(h));
        contentEditText.setText(dbHelper.getNoteContent(h));


        fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading = headingEditText.getText().toString();
                content = contentEditText.getText().toString();

                Toast.makeText(Note.this, "Note Saved", Toast.LENGTH_SHORT).show();

                dbHelper = new DBHelper(getApplicationContext());

                dbHelper.insertNote(heading,content);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                setResult(RESULT_OK);
                startActivity(intent);
                finish();

            }
        });



    }

}