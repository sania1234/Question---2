package com.example.notetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
        Toolbar toolbar;
        EditText subject, details;
        Calendar c;
        String todaysDate;
        String currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subject = findViewById(R.id.subject);
        details = findViewById(R.id.details);

        subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // get current date
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+ c.get(Calendar.MONTH)+"/"+ c.get(Calendar.DAY_OF_MONTH);
        todaysDate = pad(c.get(Calendar.HOUR))+":"+ pad(c.get(Calendar.MINUTE));
        Log.d("calendar", "Date and Time: "+ todaysDate+"and"+currentTime);
    }
    private String pad(int i){
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            Notes notes = new Notes(subject.getText().toString(),details.getText().toString(),todaysDate,currentTime);
            Database db = new  Database (this);
            db.addNote(notes);
            Toast.makeText(this, "Item is Saved.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Item is Deleted.", Toast.LENGTH_SHORT).show();
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}