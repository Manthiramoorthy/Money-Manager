package com.niresh.hi.tab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by PEGA on 12/3/2016.
 */

public class Dbhelper extends SQLiteOpenHelper {
    public static final String dbname= "moneymanager";
    public static final String tablename = "transaction";
    private int mYear,mMonth,mDay;
    public Dbhelper(Context context) {
        super(context,dbname,null,1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS'"+tablename+"'(date  varchar PRIMARY KEY,month varchar,year varchar,income varchar default 0,expense varchar default 0,balance varchar default 0,Allowance varchar default 0,Salary varchar default 0,Pettycash varchar default 0,Bonus varchar default 0,Other varchar default 0," +
                "Food varchar default 0,Entertainment varchar default 0,Education varchar default 0,Maintanence varchar default 0,Clothing varchar default 0,Electronics varchar default 0,SocialLife varchar default 0," +
                "Health varchar default 0,Beauty varchar default 0,Transportation varchar default 0,Otherexpenses varchar default 0,totalincome varchar default 0,totalexpense varchar default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insert()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date","29/11/2011");
//        contentValues.put("income",11);
//        contentValues.put("expense",11);
//        contentValues.put("Allowance",11);
//        contentValues.put("Salary",11);
//        contentValues.put("Pettycash",11);
//        contentValues.put("Bonus",11);
//        contentValues.put("Other",11);
//        contentValues.put("Food",11);
//        contentValues.put("Entertainment",11);
//        contentValues.put("Education",11);
//        contentValues.put("Maintanence",11);
//        contentValues.put("Clothing",11);
//        contentValues.put("Electronics",11);
//        contentValues.put("SocialLife",11);
//        contentValues.put("Healthy",11);
//        contentValues.put("Beauty",11);
//        contentValues.put("Transportation",11);
//        contentValues.put("Otherexpenses",11);
//        contentValues.put("totalincome",11);
//        contentValues.put("totalexpense",11);

//        String query = "INSERT INTO transaction(date)VALUES('fhfhad')";
//        db.execSQL(query);

        long i = db.insert("'"+tablename+"'",null,contentValues);
        if (i==-1)
            return false;
        else
            return true;
    }
    public int insert1(String date,String category,String rupee)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select date from '" +tablename+"'",null);
        ContentValues contentValues = new ContentValues();
        res.moveToNext();
        int j = 0;
        while (res.moveToNext()) {

            if ((res.getString(0).toString()).equals(date)) {
                Cursor res1 = db.rawQuery("select " + category + " from '" + tablename + "' where date = '" + date +"'", null);
                res1.moveToNext();
                long existingvalue = Integer.parseInt((res1.getString(0).toString()));
                long currentvalue = Integer.parseInt(rupee);
                long updatedrupee1 = existingvalue + currentvalue;
                String updatedrupee = Long.toString(updatedrupee1);
                contentValues.put(category, updatedrupee);
                int i = db.update("'" + tablename + "'", contentValues, "date = ?", new String[]{date});
                int k = incomeupdate(date);
                j++;



                return k;
            }

        }
        int l;
        if (j == 0) {
            String[] d = date.split("/");
            contentValues.put("date", date);
            contentValues.put("month",d[1]);
            contentValues.put("year",d[2]);
            db.insert("'" + tablename + "'", null, contentValues);
            Cursor res1 = db.rawQuery("select " + category + " from '" + tablename + "' where date = '" + date + "'", null);
            StringBuffer buffer1 = new StringBuffer();
            res1.moveToNext();
            int existingvalue = Integer.parseInt(res1.getString(0).toString());
            int currentvalue = Integer.parseInt(rupee);
            int updatedrupee1 = existingvalue + currentvalue;
            String updatedrupee = Integer.toString(updatedrupee1);
            contentValues.put(category, updatedrupee);
            db.update("'" + tablename + "'", contentValues, "date = ?", new String[]{date});
            l = incomeupdate(date);

        }
        return 0;
    }
    public int incomeupdate(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery("select * from '" +tablename+"' where date = '" + date +"'",null);
        res2.moveToNext();
        int income = Integer.parseInt(res2.getString(1).toString());
        int expense = Integer.parseInt(res2.getString(2).toString());
        int allowance = Integer.parseInt(res2.getString(3).toString());
        int salary = Integer.parseInt(res2.getString(4).toString());
        int pettycash = Integer.parseInt(res2.getString(5).toString());
        int bonus = Integer.parseInt(res2.getString(6).toString());
        int other = Integer.parseInt(res2.getString(7).toString());
        int food = Integer.parseInt(res2.getString(8).toString());
        int entertainment = Integer.parseInt(res2.getString(9).toString());
        int education = Integer.parseInt(res2.getString(10).toString());
        int maintanence = Integer.parseInt(res2.getString(11).toString());
        int clothing = Integer.parseInt(res2.getString(12).toString());
        int electronics = Integer.parseInt(res2.getString(13).toString());
        int sociallife = Integer.parseInt(res2.getString(14).toString());
        int health = Integer.parseInt(res2.getString(15).toString());
        int beauty = Integer.parseInt(res2.getString(16).toString());
        int transportation = Integer.parseInt(res2.getString(17).toString());
        int otherexpenses= Integer.parseInt(res2.getString(18).toString());
        int updatedincome = allowance+salary+pettycash+bonus+other;
        int updatedexpense = food+entertainment+education+maintanence+clothing+electronics+sociallife+health+beauty+transportation+otherexpenses;
        ContentValues contentValues = new ContentValues();
        contentValues.put("income",updatedincome);
        contentValues.put("expense",updatedexpense);
        Cursor res3 = db.rawQuery("select income,expense from '" +tablename+"'",null);
        res3.moveToLast();
        long totalincome = Long.parseLong(res3.getString(0).toString());
        Long toatalExpanse =  Long.parseLong(res3.getString(1).toString());
        db.update("'" + tablename + "'", contentValues, "date = ?", new String[]{date});

        long updatedtotalincome = totalincome +updatedincome;
        Long updatedtotalexpense = toatalExpanse+updatedexpense;

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("totalincome",updatedtotalincome);
        contentValues1.put("totalexpense",updatedtotalexpense);
        db.update("'" + tablename + "'", contentValues1, "date = ?", new String[]{date});
        ContentValues contentValues2 = new ContentValues();
        int balance = updatedincome-updatedexpense;
        contentValues2.put("balance",Integer.toString(balance));
        db.update("'" + tablename + "'", contentValues2, "date = ?", new String[]{date});
        return updatedexpense;


    }

}