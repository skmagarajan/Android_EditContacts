package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    String Fname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText name = (EditText)findViewById(R.id.name);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {           //Listener for edittext
                String name_trim = name.getText().toString().trim();
                Log.i("edit",name_trim);
                Intent Edit_in = new Intent();                              //Intent Created
                Bundle b = new Bundle();
                b.putString("Name",name_trim);
                Edit_in.putExtras(b);                                       //Passing name
                if(checkvalidation(name_trim)){                             //Checking validation
                    setResult(Edit.RESULT_OK,Edit_in);
                }
                else{
                    setResult(Edit.RESULT_CANCELED,Edit_in);
                }
                finish();                                                  // Closing this activity
                return true;
            }
        });

    }

    private boolean checkvalidation(String val) {
        if(val.matches("^[a-zA-Z ]*$")){                          // Whether name is alpha or not
            Log.i("edit","Inside check validation: "+val);
            String[] arr = val.split(" ");
            if(arr.length<2){                                           //Checking atleast first name and lastname provided
                return false;
            }
            Fname = val;
            return true;
        }
        return false;
    }


}
