package com.example.yusub_x.finalwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    CustomListViewAdapter adapter;
    database db =   db = new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView  = findViewById(R.id.idListVIew);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,registration.class);
                startActivityForResult(intent,1);
            }
        });
        GetCustomersInfo();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

         if(requestCode==1){
            if(resultCode==RESULT_OK){
                GetCustomersInfo();
            }
        }
       if(requestCode==2){
           if(resultCode==RESULT_OK){
               Toast.makeText(this, "müştəri siyahıdan silindi!", Toast.LENGTH_SHORT).show();
               GetCustomersInfo();
           }if(resultCode==RESULT_CANCELED){
               Toast.makeText(this, "müştərinin məlumatları yeniləndi!", Toast.LENGTH_SHORT).show();
               GetCustomersInfo();
           }
       }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void GetCustomersInfo(){
        List<model> list = new ArrayList<>();
        list = db.getAllCustomers();
        adapter = new CustomListViewAdapter(MainActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,see_more.class);
                model model = adapter.getItem(position);
                intent.putExtra("user",model.getUserID());
                startActivityForResult(intent,2);
            }
        });

    }
}
