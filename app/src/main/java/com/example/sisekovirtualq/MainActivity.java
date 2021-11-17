package com.example.sisekovirtualq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class


MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private String selectedItem;
    private String selectedItem2;
    private Spinner sp;
    private Spinner spCode;
    DBheplerF myDB;
    Button view,companyQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=(Button)findViewById(R.id.companyQ_btn);
        companyQ=(Button)findViewById(R.id.viewAddress_btn);
        myDB=new DBheplerF(this);
        Toolbar toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Virtual Q");

        DBheplerF datahelper=new DBheplerF(this);
        ArrayList<String>list2=datahelper.getAllCompanyCode();
        ArrayList<String>list=datahelper.getAllCompany();
        sp=(Spinner) findViewById(R.id.company_spinner);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.sipinner_layout,R.id.txt_spnner,list);
        sp.setAdapter(adapter);
        spCode=(Spinner) findViewById(R.id.company_codeSp);
        ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,R.layout.sipinner_layout,R.id.txt_spnner,list2);
        spCode.setAdapter(adapter2);
        selectedItem2=spCode.getSelectedItem().toString();
        selectedItem=sp.getSelectedItem().toString();
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "company"+selectedItem, Toast.LENGTH_SHORT).show();
                selectedItem=sp.getSelectedItem().toString();
                Cursor res=myDB.getadressdata(selectedItem);

                if(res.getCount()==0){
                    Toast.makeText(getApplicationContext(), "no Entry exist", Toast.LENGTH_SHORT).show();
                    return; //use to ensure that the computer won't execute the bottom code
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()) {//moveToFirst(): Moves the position to the first row. boolean Cursor. ... moveToNext(): Moves the cursor to the next row relative to the current position. boolean Cursor.
                    buffer.append("Company Code :"+res.getString(0)+"\n");
                    buffer.append("Company Name:"+res.getString(1)+"\n");
                    buffer.append("Company Owner:"+res.getString(2)+"\n");
                    buffer.append("Company Address:"+res.getString(3)+"\n");
                    buffer.append("Company Description:"+res.getString(4)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);////view box
                builder.setCancelable(true);
                builder.setTitle("Company Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }

    public void  openCompany(View view){
        Intent intent =new Intent(this,CompanyQ.class);
        selectedItem2=spCode.getSelectedItem().toString();
        intent.putExtra("companyCode",selectedItem2);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);

        return true;

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.person:
                Intent intent=new Intent(this,Company.class);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //selectedItem=(String)parent.getItemAtPosition(position);
        selectedItem=sp.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

