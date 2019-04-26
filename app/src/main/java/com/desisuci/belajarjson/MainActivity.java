package com.desisuci.belajarjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView txt_id, txt_name, txt_email;
    String id, name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference of textview
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_email = (TextView) findViewById(R.id.txt_email);


        try {
            //get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            //fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("users");
            for (int i = 0; i < userArray.length();i++) {
                //create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(1);
                id = userDetail.getString("id");
                name = userDetail.getString("name");
                email = userDetail.getString("email");

                //set textview
                txt_id.setText("ID          : " + id);
                txt_name.setText("Name      : " + name);
                txt_email.setText("Email    : " + email);
            }
            } catch(JSONException e){
                e.printStackTrace();
            }
        }

        public String loadJSONFromAsset () {
            String json = null;
            try {
                InputStream is = getAssets().open("users.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }

            return json;

        }

    }
