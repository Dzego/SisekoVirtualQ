package com.example.sisekovirtualq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBheplerF extends SQLiteOpenHelper {
    public static final String DBNAME="company.db";
    //public DBHelper(@Nullable Context context) {
     //   super(context, "Login.db", null , 1);
    //}

    public DBheplerF(@Nullable Context context) {
        super(context, "company.db",null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table company(companycode TEXT primary key,companyname TEXT,Owner TEXT,companyAddress TEXT,Description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists company");
    }

    public boolean insertData(String companyCode, String companyName,String Owner,String address, String description){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("companycode",companyCode);
        contentValues.put("companyname",companyName);
        contentValues.put("Owner",Owner);
        contentValues.put("companyAddress",address);
        contentValues.put("Description",description);
        Long result=myDB.insert("company",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }

   /* public boolean updatepassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password);
        long result=myDB.update("Qusers",contentValues,"username = ?",new String[]{username});
        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }*/

    public boolean updateCompany(String companyCode, String companyName,String Owner,String address, String description){
        SQLiteDatabase myDB=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        if(!companyCode.equals("")){
            contentValues.put("companycode",companyCode);

        }

        if(!companyName.equals("")){
            contentValues.put("companyname",companyName);

        }
        if(!Owner.equals("")){
            contentValues.put("Owner",Owner);

        }
        if(!address.equals("")){
            contentValues.put("companyAddress",address);

        }
        if(!description.equals("")){
            contentValues.put("Description",description);
        }

        long result=myDB.update("company",contentValues,"companycode = ?",new String[]{companyCode});
        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }

  public ArrayList<String>getAllCompany(){
        ArrayList<String>list=new ArrayList<String>();
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from company", null);
       if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                int index= cursor.getColumnIndex("companyname");
                String companyName=cursor.getString(index);
                list.add(companyName);
            }

       }

        return list;
  }


    public ArrayList<String>getAllCompanyCode(){
        ArrayList<String>list=new ArrayList<String>();
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from company", null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                int index= cursor.getColumnIndex("companycode");
                String companyName=cursor.getString(index);
                list.add(companyName);
            }

        }

        return list;
    }


    public boolean checkcompanycode(String companyCode){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from company where companycode=?",new String[]{companyCode});
        if(cursor.getCount()>0){
            return true;

        }
        else{

            return false;
        }
    }

    public Boolean deletedata (String companyCode){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from company where companycode=?",new String[]{companyCode});// select the raws just like datatable
        if (cursor.getCount() > 0) {
            long result = myDB.delete("company","companycode=?", new String[]{companyCode});//delete where username match the username provide as the parameter
            if (result == -1) {//if it didn't happen
                return false;
            } else {
                return true;
            }

        } else {

            return false;
        }

    }

    public Cursor getdata (){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from company", null);// select the raws just like datatable
        return cursor;

    }

    public Cursor getuserdata (String companyCode){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from company where companycode=?",new String[]{companyCode});// select the raws just like datatable
        return cursor;

    }
    public Cursor getadressdata (String companyname){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from company where companyname=?",new String[]{companyname});// select the raws just like datatable
        return cursor;

    }


   /* public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from company where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;

        }
        else{

            return false;
        }
    }*/



}
