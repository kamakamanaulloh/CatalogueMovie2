package com.kamak.cataloguemovie;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPrequest {
    public static String AksesInternet(String urlParam){
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        String jsonstr=null;


        try {
            URL url = new URL(urlParam);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            StringBuffer buffer=new StringBuffer();
            if(inputStream==null){
                jsonstr=null;
            }
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String Line;
            while ((Line=reader.readLine())!=null){
                buffer.append(Line+"\n");

            }
            if(buffer.length()==0){
                jsonstr=null;
            }
            jsonstr=buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("PlaceholderFragment","Error",e);
            jsonstr=null;
        }
        finally {
            if(urlConnection !=null){
                urlConnection.disconnect();
            }
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("PlaceholderFragment","Error closing stream",e);
                }
            }
        }


        return jsonstr;
    }

}
