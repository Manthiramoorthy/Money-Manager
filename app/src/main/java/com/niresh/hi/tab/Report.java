package com.niresh.hi.tab;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Report extends Fragment implements AdapterView.OnItemClickListener{
    View view;
    SQLiteDatabase db;
    String tablename = "transaction";
    private int mYear,mMonth,mDay;
    ListView l;
    public Report() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_report, container, false);
        l =(ListView) view.findViewById(R.id.listview);
        try {
            db = getActivity().openOrCreateDatabase("moneymanager", Context.MODE_PRIVATE, null);
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date1 = sdf.format(c.getTime()).toString();
            String[] d = date1.split("/");
            String month = d[1];
            List<String> list = new ArrayList<String>();

            Cursor res3 = db.rawQuery("select date,income,expense from '" + tablename + "' where month = '" + month + "'", null);
            while (res3.moveToNext()) {
                String values = "\n" + res3.getString(0).toString() + "\n" + "INCOME:" + res3.getString(1).toString() + "\n" + "EXPENSE:" + res3.getString(2).toString() + "\n";
                list.add(values);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
                ;
                // Assign adapter to ListView
                l.setAdapter(adapter);
                l.setOnItemClickListener(this);

            }
        }catch(Exception e)
        {

        }
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value =(String) (l.getItemAtPosition(position));
        String[] d = value.split("INCOME");
        String date = d[0];
        Intent i =new Intent(getActivity(),Report1.class);
        i.putExtra("date",date);
        startActivity(i);
    }
}
