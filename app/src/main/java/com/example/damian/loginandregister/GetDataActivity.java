package com.example.damian.loginandregister;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.Console;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Damian on 2017-03-10.
 */

public class GetDataActivity extends AsyncTask<String,Integer,Drawable>{

    private final String url;
    private final String login;
    private Context context;
    UserSettings userSettings;
    public ImageView imageView;
    public Drawable drawable;
    public ProgressBar progressBar;

    GetDataActivity(String myUrl, String myLogin, Context myContext, ImageView mImageView, ProgressBar myProgressBar){
        url = myUrl;
        login = myLogin;
        context = myContext;
        imageView = mImageView;
        progressBar = myProgressBar;
    }


    @Override
    protected Drawable doInBackground(String... params) {

        publishProgress();

        userSettings = new UserSettings(context);
        try{

            URL urlData = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlData.openConnection();
            connection.setRequestMethod("POST");
            OutputStream oS = connection.getOutputStream();

            String writerParams = "name=" + login;
            oS.write(writerParams.getBytes());

            InputStream iS = connection.getInputStream();
            int tmp;
            String data = "";

            while ((tmp = iS.read()) != -1) {
                data += (char) tmp;
            }

            String[] dataArrayTemp = data.split("\"");
            String[] dataArray = new String[10];
            int l = 0;


            for(int num = 3; num<dataArrayTemp.length; num = num+4){
                dataArray[l] = dataArrayTemp[num];
                l++;
            }

            userSettings.writePicUrls(dataArray);

            iS.close();
            connection.disconnect();

        }
        catch (Exception e){
            String exeption = e.toString();
        }

        try {

            String myUrl = userSettings.getPicUrls(0);

            URL urll = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) urll.openConnection();
            InputStream input = conn.getInputStream();
            drawable = Drawable.createFromStream(input, "src");

            conn.disconnect();
            input.close();

        }catch (Exception e){
            String err = e.getMessage().toString();
            return null;
        }

        return drawable;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        progressBar.isShown();
    }


    @Override
    protected void onPostExecute(Drawable pic){

        progressBar.setVisibility(View.GONE);

        if (drawable != null)
            imageView.setImageDrawable(drawable);
        else imageView.setImageResource(R.drawable.ic_menu_gallery);

    }





}


