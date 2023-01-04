package com.example.inclassdos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Gson gson;
    Button addbtn,savebtn;
    EditText title,author,pages;
    Switch available;
    ArrayList<Books> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupViews();
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books.add(new Books(title.getText().toString(),author.getText().toString(),pages.getText().toString(),
                        available.isChecked()));
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                gson = new Gson();
                String bookString = gson.toJson(books);
                System.out.println(bookString);
                editor.putString("123",bookString);
                editor.commit();
            }
        });


    }

    public void SetupViews(){
        addbtn = findViewById(R.id.btnAdd);
        savebtn = findViewById(R.id.btnSave);
        title = findViewById(R.id.bookTitle);
        author = findViewById(R.id.authName);
        pages = findViewById(R.id.pages);
        available = findViewById(R.id.availableSwitch);
    }
}