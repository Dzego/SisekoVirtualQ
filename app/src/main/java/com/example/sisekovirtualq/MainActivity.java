package com.example.sisekovirtualq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Virtual Q");

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);

        return true;

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.person:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.home_icon:
                //Intent intent=new Intent(this,MainActivity.class);
                //startActivity(intent);
                return true;
            case R.id.log_in:
                //Intent intent=new Intent(this,MainActivity.class);
                //startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }





}

