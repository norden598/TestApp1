package com.example.testapp1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameTextBox, numberTextBox, positionTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameTextBox = (EditText)findViewById(R.id.nameTextBox);
        numberTextBox = (EditText)findViewById(R.id.numberTextBox);
        positionTextBox = (EditText)findViewById(R.id.positionTextBox);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void OnInsert(View view) {
        String name = nameTextBox.getText().toString();
        String numberStr = numberTextBox.getText().toString();
        String position = positionTextBox.getText().toString();
        String type = "insert";
        String firstName = "";
        String lastName = "";
        String[] nameSplit = name.split(" ");
        int number = 0;

        firstName = nameSplit[0];
        lastName = nameSplit[1];

        // make sure name and number are valid before moving on

        BackgroundWorker bgw = new BackgroundWorker(this);
        bgw.execute(type, numberStr, firstName, lastName, position);
    }
}
