package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         final Button button1 = (Button) findViewById(R.id.button);
            final Button buttonpost = (Button)findViewById(R.id.postbutton);
        final TextView textView = (TextView) findViewById(R.id.textview);
        // ...

    // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://reqres.in/api/users?page=2";
        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                textView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                });

                // Add a request (in this example, called stringRequest) to your RequestQueue.
                queue.add(stringRequest);




// Add the request to the RequestQueue.
                //queue.add(stringRequest);

            }
        });


        buttonpost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                JSONObject postData = new JSONObject();
                try {
                    postData.put("name", "Jonathan");
                    postData.put("job", "Software Engineer");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String postUrl = "https://2480114b95d6.ngrok.io";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                queue.add(jsonObjectRequest);

            }
        });


    }

}