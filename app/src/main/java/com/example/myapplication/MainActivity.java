package com.example.myapplication;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            TabHost tab_host = (TabHost) findViewById(R.id.tabhost);
            tab_host.setup();

            TabHost.TabSpec ts1 = tab_host.newTabSpec("tab1");
            ts1.setIndicator("즐겨찾기");
            ts1.setContent(R.id.tab1);
            tab_host.addTab(ts1);

            TabHost.TabSpec ts2 = tab_host.newTabSpec("tab2");
            ts2.setIndicator("노선");
            ts2.setContent(R.id.tab2);
            tab_host.addTab(ts2);

        TabHost.TabSpec ts3 = tab_host.newTabSpec("tab3");
        ts3.setIndicator("정류장");
        ts3.setContent(R.id.tab3);
        tab_host.addTab(ts3);


        tab_host.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
}
