package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       readItems();
        itemsAdapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, items );
        lvItems =  findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        //Add mock data

       // items.add("First Items");
       // items.add("Second Items");
       // items.add("Third Items");

        setupListViewListener();
    }

    //for adding text

    public void onAddItem(View v){

        EditText edText =  findViewById(R.id.edText);
        String itemText = edText.getText().toString();
        itemsAdapter.add(itemText);
        edText.setText("");
        writeItems();
        Toast.makeText(getApplicationContext(), "Items add to list", Toast.LENGTH_SHORT).show();

    }

    private void setupListViewListener(){
        Log.i("MainActivity" , "Setting up listener on list view");
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity" , "Items removed from list" + position);
               items.remove(position);
               itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "todo.text");
    }

    private void readItems() {
        try {
            items = new ArrayList<>( FileUtils.readLines( getDataFile(), String.valueOf( Charset.defaultCharset() ) ) );
        } catch (IOException e) {
            Log.e( "MainActivity", "Error reading file", e );
            items = new ArrayList<>();
        }
    }

        private void writeItems(){
            try {
                FileUtils.writeLines(getDataFile(), items);
            } catch (IOException e) {
                Log.e( "MainActivity", "Error writing file", e);
            }
        }

}