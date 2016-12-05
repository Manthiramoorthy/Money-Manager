package com.niresh.hi.tab;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab;
    String tablename = "transaction";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        SQLiteDatabase db ;
        db = this.openOrCreateDatabase("moneymanager", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS'"+tablename+"'(date  varchar PRIMARY KEY,month varchar,year varchar,income varchar default 0,expense varchar default 0,balance varchar default 0,Allowance varchar default 0,Salary varchar default 0,Pettycash varchar default 0,Bonus varchar default 0,Other varchar default 0," +
                "Food varchar default 0,Entertainment varchar default 0,Education varchar default 0,Maintanence varchar default 0,Clothing varchar default 0,Electronics varchar default 0,SocialLife varchar default 0," +
                "Health varchar default 0,Beauty varchar default 0,Transportation varchar default 0,Otherexpenses varchar default 0,totalincome varchar default 0,totalexpense varchar default 0)");

        Pager TabAdapter = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(TabAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        //in the block below write the onclick for floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Transaction.class);
                startActivity(i);
            }
        });
    }
}
