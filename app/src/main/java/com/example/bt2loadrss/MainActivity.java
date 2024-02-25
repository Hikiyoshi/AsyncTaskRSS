package com.example.bt2loadrss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rcv = (RecyclerView) findViewById(R.id.rcv);

        Button btnLoad = (Button) findViewById(R.id.button);
        EditText edlink = (EditText) findViewById(R.id.edRssLink);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkrss = edlink.getText().toString().trim();

                MyAsyncTask asyncTask = new MyAsyncTask(MainActivity.this,linkrss,rcv);
                asyncTask.execute();
            }
        });
    }
}