package com.example.databasedisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import adapter.ClassAdapter;
import data.DatabaseHandler;
import model.Classes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        databaseHandler.addClass(new Classes("John"));
        databaseHandler.addClass(new Classes("Mike"));
        databaseHandler.addClass(new Classes("James"));
        databaseHandler.addClass(new Classes("Mark"));
        databaseHandler.addClass(new Classes("Tony"));
        databaseHandler.addClass(new Classes("Anton"));
        databaseHandler.addClass(new Classes("Gor"));
        List<Classes> classesList = databaseHandler.getAllClasses();
        List<String> names = new ArrayList<String>();
        ClassAdapter classAdapter = new ClassAdapter((int) classesList.stream().count());
        RecyclerView animalsList = findViewById(R.id.class_list);
        animalsList.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        
        for (Classes classes : classesList) {
            Log.d("Classes", "Teacher of CLASS " + classes.getId() + " is: " + classes.getName());
            names.add(classes.getName());
        }

        classAdapter.setName(names);

        classAdapter.setName(names);

        animalsList.setAdapter(classAdapter);
    }
}