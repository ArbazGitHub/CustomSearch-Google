package com.example.arbaz.customsearch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    EditText input;
    Button buttonSearch;
    WebView webView;

    String search_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    try
    {
        String key = "AIzaSyD4d102uSGmzb9xAra7yMD74ufRnqoOBDM";
        String qry = "Mini Cooper";
        URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=008937629402112675073:m_9-8o6moiq&q=" + qry + "&alt=json");
//From hee use Okay HTTP
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setUseCaches(false);
        conn.setAllowUserInteraction(false);
        conn.setConnectTimeout(100000);
        conn.setReadTimeout(100000);
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
           /* String output;
            System.out.println("Output from Server .... \n");*/
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {

                if (output.contains("\"items\": \"")) {
                    String link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(), output.indexOf("\","));
                    System.out.println(link);       //Will print the google search links
                    Log.e("Links:->", link);
                }
            }
        }
        conn.disconnect();
    }
    catch (Exception e){
        e.printStackTrace();
    }
    }


}