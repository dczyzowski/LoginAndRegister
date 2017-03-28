package com.example.damian.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Damian on 2017-03-15.
 */

public class GetGalleryList extends AsyncTask<String, Integer, ArrayList> {

    Context context;
    ArrayList<Drawable> drawables = new ArrayList<>();
    ProgressBar progressBar;
    GalleryActivity galleryActivity;

    public GetGalleryList(Context thisContext, ProgressBar myProgressBar){
        context = thisContext;
        progressBar = myProgressBar;

        galleryActivity = new GalleryActivity();
    }


    @Override
    protected ArrayList doInBackground(String... params) {


        UserSettings myUser = new UserSettings(context);
        String url = "http://testbazydanych123.netau.net/AndroidUploadImage/get_pic.php";
        String login = myUser.getLoggedInUser().myLogin;
        String[] dataArray = new String[10];
        ArrayList<Drawable> drawables = new ArrayList<>();

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
            int l = 0;


            for(int num = 3; num<dataArrayTemp.length; num = num+4){
                dataArray[l] = dataArrayTemp[num];
                l++;
            }

            iS.close();
            connection.disconnect();

        }
        catch (Exception e){
            String exeption = e.toString();
        }

        for( int i = 0 ; dataArray.length<=i ; i++) {
            try {

                URL urll = new URL(dataArray[i]);
                HttpURLConnection conn = (HttpURLConnection) urll.openConnection();
                InputStream input = conn.getInputStream();
                Drawable drawable_temp = Drawable.createFromStream(input, "src");

                drawables.add(drawable_temp);

                conn.disconnect();
                input.close();

            } catch (Exception e) {
                String err = e.getMessage().toString();
                return null;
            }
        }
        return drawables;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(ArrayList arrayList) {

        galleryActivity.getList();

        super.onPostExecute(arrayList);
    }

    public ArrayList drawableList(){
        return drawables;
    }

}
