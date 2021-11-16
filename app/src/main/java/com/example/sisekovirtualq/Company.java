package com.example.sisekovirtualq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Company extends AppCompatActivity {
    EditText companyCode ,companyname,companyaddress,companydescription,companyowner;
    Button delete,update,view,insert;
    DBheplerF myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        companyCode=(EditText) findViewById(R.id.company_Code);
        companyname=(EditText) findViewById(R.id.company_name);
        companyaddress=(EditText)findViewById(R.id.company_address);
        companydescription=(EditText)findViewById(R.id.company_Description);
        companyowner=(EditText)findViewById(R.id.company_owner);
        delete=findViewById(R.id.delete_btn);
        update=findViewById(R.id.update_btn);
        view=findViewById(R.id.view_btn);
        insert=findViewById(R.id.insert_btn);


        myDB=new DBheplerF(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = (String) companyCode.getText().toString();
                boolean checkdeletedata = myDB.deletedata(nameTXT);
                if(checkdeletedata == true) {

                    Toast.makeText(getApplicationContext(), "Entry deleted ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Entry not deleted ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Cursor res=myDB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(getApplicationContext(), "no Entry exist", Toast.LENGTH_SHORT).show();
                    return; //use to ensure that the computer won't execute the bottom code
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()) {//moveToFirst(): Moves the position to the first row. boolean Cursor. ... moveToNext(): Moves the cursor to the next row relative to the current position. boolean Cursor.
                    buffer.append("Company Code :"+res.getString(0)+"\n");
                    buffer.append("Company Name:"+res.getString(2)+"\n");
                    buffer.append("Company Owner:"+res.getString(1)+"\n");
                    buffer.append("Company Address:"+res.getString(1)+"\n");
                    buffer.append("Company Description:"+res.getString(1)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(Company.this);////view box
                builder.setCancelable(true);
                builder.setTitle("Company Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = (String) companyCode.getText().toString();
                String companynameTXT = (String) companyname.getText().toString();
                String fcompanyownerTXT = (String)companyowner.getText().toString();
                String companyaddressTXT = (String)companyaddress.getText().toString();
                String companydescriptionTXT = (String)companydescription.getText().toString();
                boolean checkupdatedata = myDB.updateCompany(nameTXT,companynameTXT, fcompanyownerTXT,companyaddressTXT,companydescriptionTXT);
                if(checkupdatedata == true) {

                    Toast.makeText(getApplicationContext(), "Entry Updated ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Entry not Updated ", Toast.LENGTH_SHORT).show();

                }
            }

        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTXT = (String) companyCode.getText().toString();
                String companynameTXT = (String) companyname.getText().toString();
                String companyownerTXT = (String)companyowner.getText().toString();
                String companyaddressTXT = (String)companyaddress.getText().toString();
                String companydescriptionTXT = (String)companydescription.getText().toString();


                if(nameTXT.equals("")||companynameTXT.equals("")||companyownerTXT.equals("")||companyaddressTXT.equals("")||companydescriptionTXT.equals("")){
                    Toast.makeText(getApplicationContext(), "fill all the textbox ", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkucode=myDB.checkcompanycode(nameTXT);
                    if(checkucode){
                        boolean insert=myDB.insertData(nameTXT,companynameTXT,companyownerTXT,companyaddressTXT,companydescriptionTXT);
                        if(insert==true){

                            Toast.makeText(getApplicationContext(), "company inserted successfully", Toast.LENGTH_SHORT).show();
                            //Intent intent =new Intent(this,Home.class);
                           // intent.putExtra("username",user);
                            //startActivity(intent);
                            companyCode.setText("");
                            companyname.setText("");
                            companyowner.setText("");
                            companyaddress.setText("");
                            companydescription.setText("");
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "this code already exist", Toast.LENGTH_SHORT).show();
                    }


                }


            }

        });


    }




}