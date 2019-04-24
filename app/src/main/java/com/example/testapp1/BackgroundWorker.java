package com.example.testapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Scanner;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... args) {
        String type = args[0];
        String login_url = String.format("{0}/roster_insert.php", "xx.xx.xxx.xxx"); // URL here
        if (type.equals("insert"))
        {
            String numberStr = args[1];
            String firstName = args[2];
            String lastName = args[3];
            //int number = Integer.getInteger(numberStr);
            String position = args[4];
            try
            {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("number", "UTF-8") + "=" +
                                   URLEncoder.encode(numberStr, "UTF-8") + "&" +
                                   URLEncoder.encode("first_name", "UTF-8") + "=" +
                                   URLEncoder.encode(firstName, "UTF-8") + "&" +
                                   URLEncoder.encode("last_name", "UTF-8") + "=" +
                                   URLEncoder.encode(lastName, "UTF-8") + "&" +
                                   URLEncoder.encode("position", "UTF-8") + "=" +
                                   URLEncoder.encode(position, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                alertDialog.setMessage(e.getMessage());
                alertDialog.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                alertDialog.setMessage(e.getMessage());
                alertDialog.show();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Insert Status");
    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
