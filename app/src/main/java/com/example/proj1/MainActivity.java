package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button first = (Button) findViewById(R.id.first);      //First Button
        first.setOnClickListener(new View.OnClickListener() {        //Listener for First button
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this,Edit.class); //Intent to move for Second Activity
                startActivityForResult(in1,1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, final Intent data){
        Button second = (Button)findViewById(R.id.second);          //Second Button

        if(requestCode == 1){
            Bundle b = data.getExtras();                            //Bundle to get Extra data
            final String name = b.getString("Name");            //Getting name from the Bundle
            if(resultCode == MainActivity.RESULT_OK){               //Checking result is OK or not if OK
                second.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("edit","Inside Result_ok: "+name);

                        Intent contact = new Intent(ContactsContract.Intents.Insert.ACTION);  //Implicit intent
                        contact.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                        contact.putExtra(ContactsContract.Intents.Insert.NAME,name);        //Passing Name on Intent to contact app
                        startActivity(contact);
                    }
                });
            }
            if(resultCode == MainActivity.RESULT_CANCELED){                         //If result is cancel
                second.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("edit","Inside Result_Cancelled");
                        Context context = getApplicationContext();
                        CharSequence text = "Entered incorrect Name: "+name;        //Toast message
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);      //Toast
                        toast.show();
                    }
                });
            }
        }

    }
}
