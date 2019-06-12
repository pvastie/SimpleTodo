package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.codepath.simpletodo.MainActivity.ITEM_POSITION;
import static com.codepath.simpletodo.MainActivity.ITEM_TEXT;

public class EditItemActivity extends AppCompatActivity {

    //Track edit text
    EditText etItemText;
    // position of edited item in list
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_item );

        //resolve edit text from layaout
        etItemText = findViewById( R.id.etItemText);
        //set edit text value form intent extra
        etItemText.setText(getIntent().getStringExtra(ITEM_TEXT ) );
        //update positiom from intent extra
        position =  getIntent().getIntExtra(ITEM_POSITION, 0);
        //update the title bar of the activity
        getSupportActionBar().setTitle("Edit Item");
    }
        // handler for save button
    public void onSaveItem(View v){
    //prepare view intent for result
        Intent i = new Intent();
        //pass updated item text as extra
        i.putExtra( ITEM_TEXT, etItemText.getText().toString() );
        //pass original position as extra
        i.putExtra( ITEM_POSITION, position );
        // set the intent as the result of the activity
        setResult( RESULT_OK, i );
        // close the activity and redirect to main
        finish();
    }
}
