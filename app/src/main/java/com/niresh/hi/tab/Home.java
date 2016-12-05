package com.niresh.hi.tab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Home extends Fragment {
    SQLiteDatabase db;
    View view;
    TextView t1,t2,t3;
    Dbhelper d;
    private int mYear,mMonth,mDay;
    String tablename = "transaction";
    private Cursor c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        t1 = (TextView) view.findViewById(R.id.income);
        t2 = (TextView)view.findViewById(R.id.expense);
        t3 = (TextView)view.findViewById(R.id.balance);
        try {
            t1.setText("income");
            t2.setText("expense");
            t3.setText("balance");
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            //String dateFormat = "dd/MM/yyyy";
            String expense = null, income = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date1 = sdf.format(c.getTime()).toString();
            db = getActivity().openOrCreateDatabase("moneymanager", Context.MODE_PRIVATE, null);
            String balance = null;
            Cursor res3 = db.rawQuery("select " + t1.getText().toString() + " from '" + tablename + "' where date = '" + date1 + "'", null);
            while (res3.moveToNext()) {

                income = res3.getString(0).toString();
                t1.setText(income);
            }
            Cursor res4 = db.rawQuery("select " + t2.getText().toString() + " from '" + tablename + "' where date = '" + date1 + "'", null);
            while (res4.moveToNext()) {
                expense = res4.getString(0).toString();
                t2.setText(expense);
            }
            Cursor res5 = db.rawQuery("select " + t3.getText().toString() + " from '" + tablename + "' where date = '" + date1 + "'", null);
            while (res5.moveToNext()) {
                balance =res5.getString(0).toString();
                t3.setText(balance);
            }

        }catch(Exception e){

        }



        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
