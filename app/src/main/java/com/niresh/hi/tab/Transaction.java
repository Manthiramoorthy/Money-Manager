package com.niresh.hi.tab;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Transaction extends Activity implements View.OnClickListener{
    Dbhelper dh;
    Spinner spinner;
    Button b1;
    List<String> list;
    RadioGroup r1;
    EditText e1,e2,e3;
    static final int DATE_DIALOG_ID = 0;
    private int mYear,mMonth,mDay;
    SQLiteDatabase db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);
        dh = new Dbhelper(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        e1 = (EditText) findViewById(R.id.editText2);
        e2 = (EditText)findViewById(R.id.editText3);
        e3 = (EditText)findViewById(R.id.editText4);
        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        //String dateFormat = "dd/MM/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        e3.setText( sdf.format(c.getTime()));
        e3.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);

            }
        });
        r1 = (RadioGroup)findViewById(R.id.trans);
        b1 = (Button)findViewById(R.id.button2);
        list = new ArrayList<String>();
        list.add("Select categary");
        call();
        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radioButton4:
                        // TODO Something
                        list.clear();
                        spinner.setAdapter(null);
                        list.add("Select categary");
                        list.add("Food");
                        list.add("Entertainment");
                        list.add("Education");
                        list.add("Maintanence");
                        list.add("Clothing");
                        list.add("Electronics");
                        list.add("SocialLife");
                        list.add("Health");
                        list.add("Beauty");
                        list.add("Transportation");
                        list.add("Otherexpenses");
                        call();
                        break;
                    case R.id.radioButton3:
                        spinner.setAdapter(null);
                        // TODO Something
                        list.clear();
                        list.add("Select categary");
                        list.add("Allowance");
                        list.add("Salary");
                        list.add("Pettycash");
                        list.add("Bonus");
                        list.add("Other");
                        call();
                        break;

                }

            }
        });


        b1.setOnClickListener(this);


    }
    void call(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);

        }

        return null;

    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            e3.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));

        }

    };
    @Override
    public void onClick(View view) {
        try {
            String rupee = e1.getText().toString();
            int selectedRadioButtonID = r1.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
            String type = selectedRadioButton.getText().toString();
            String notes = e2.getText().toString();
            String date = e3.getText().toString();
            String category = spinner.getSelectedItem().toString();
            int i = dh.insert1(date,category,rupee);

            if (i>0)
            {
                Toast.makeText(Transaction.this,Integer.toString(i),Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(Transaction.this,"not"+i,Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {
            Toast.makeText(this,"hii"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}


