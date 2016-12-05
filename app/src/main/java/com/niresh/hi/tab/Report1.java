package com.niresh.hi.tab;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PEGA on 12/5/2016.
 */

public class Report1 extends Activity {
    SQLiteDatabase db;
    TextView t1;
    ListView l;
    String tablename = "transaction";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
        String s = getIntent().getStringExtra("date");
        l = (ListView) findViewById(R.id.list);
        t1 = (TextView) findViewById(R.id.textView3);
        t1.setText(s);
        String date = s.trim();
        List<String> list = new ArrayList<String>();
        db  = this.openOrCreateDatabase("moneymanager", Context.MODE_PRIVATE, null);
        Cursor res3 = db.rawQuery("select * from '" +tablename+"' where date = '" + date +"'",null);
        String values = "";
        while(res3.moveToNext()) {

            for(int i = 3;i<res3.getColumnCount();i++)
            {
                values = values + "\n"+ res3.getColumnName(i)+"\t:\t"+res3.getString(i).toString();
            }

            list.add(values);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            ;
            // Assign adapter to ListView
            l.setAdapter(adapter);

        }

    }

}
