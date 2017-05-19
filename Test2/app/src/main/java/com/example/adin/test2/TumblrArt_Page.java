package com.example.adin.test2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TumblrArt_Page extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    Context context = this;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    TextView textView1,textView2,textView3,textView4,textView5;
    String imageUrl="", jsonStr="";
    String[] stringPhotoUrlArray={"","","","",""};
    String[] stringBlogNameArray={"","","","",""};
    String[] stringSummaryArray={"","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumblr_art__page);

        imageView1 = (ImageView)findViewById(R.id.imageTum1);
        imageView2 = (ImageView)findViewById(R.id.imageTum2);
        imageView3 = (ImageView)findViewById(R.id.imageTum3);
        imageView4 = (ImageView)findViewById(R.id.imageTum4);
        imageView5 = (ImageView)findViewById(R.id.imageTum5);

        textView1 = (TextView)findViewById(R.id.textViewTum1);
        textView2 = (TextView)findViewById(R.id.textViewTum2);
        textView3 = (TextView)findViewById(R.id.textViewTum3);
        textView4 = (TextView)findViewById(R.id.textViewTum4);
        textView5 = (TextView)findViewById(R.id.textViewTum5);
        new GetImages().execute();
    }

    private class GetImages extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            Random r1 = new Random();
//            Random r2 = new Random();
//            Random r3 = new Random();
//            Random r4 = new Random();
//            Random r5 = new Random();
//            offset = r1.nextInt(14 - 0) + 0;
//            quizNumber1 = r2.nextInt(100 - 0) + 0;
//            quizNumber2 = r3.nextInt(100 - 0) + 0;
//            quizNumber3 = r4.nextInt(100 - 0) + 0;
//            quizNumber4 = r5.nextInt(100 - 0) + 0;
//
//            Log.i("nomor offset",":"+offset);
//            Log.i("nomor1",":"+quizNumber1);
//            Log.i("nomor2",":"+quizNumber2);
//            Log.i("nomor3",":"+quizNumber3);
//            Log.i("nomor4",":"+quizNumber4);


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making an URL with timestamp, private key and public key
            String apiKey = "DTd3o60Of1pkWwrLapTNzZJ0qaU8jfKfo0yEGwmZb7Vfk910Se";

            ArrayList arrayList = new ArrayList(10);

            Random rmonth = new Random();
            Random ryear = new Random();
            Random rday = new Random();

            Integer month = 0;
            Integer day = 0;
            Integer year = 0;

            month = rmonth.nextInt(12 - 0)+ 0;
            day = rday.nextInt(27 - 0) + 0;
            year = ryear.nextInt(2016 - 2011) + 2011;


            String str_date=month+"-"+day+"-"+year;
            DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date date = null;
            try {
                date = formatter.parse(str_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long output=date.getTime()/1000L;
            String str=Long.toString(output);
            long timestamp = Long.parseLong(str) * 1000;

            Log.e("timestamp:","->"+timestamp);
            Log.e("tumblr:","https://api.tumblr.com/v2/tagged?api_key="+apiKey+"&tag=marvel&before="+timestamp);
            jsonStr = sh.makeServiceCall("https://api.tumblr.com/v2/tagged?api_key="+apiKey+"&tag=marvel&before="+timestamp);
            Log.i("testimageUrl  ","="+jsonStr);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    JSONObject jsonImgObj;
                    JSONArray jsonArrayPhoto;
                    JSONObject jsonPhotoObj;
                    JSONArray jsonArrayAltSizePhoto;
                    JSONObject jsonObjAltSize;
                    Integer objectCounter = 0;
                    Integer loopCounter = 0;
                    do {

                        jsonImgObj = jsonArray.getJSONObject(objectCounter);

                        if(jsonImgObj.getString("type").equalsIgnoreCase("photo")){
                            arrayList.add(loopCounter,objectCounter);
                            Log.i("Nomor index:"+objectCounter,"isi:"+objectCounter);
                            loopCounter++;
                        }
                        objectCounter++;

                    } while ((objectCounter != 20) && loopCounter<5);

                    Collections.shuffle(arrayList);

                    for (int i=0;i<5;i++){
                        if(arrayList.get(i)!=null){
                            jsonImgObj = jsonArray.getJSONObject(Integer.parseInt(arrayList.get(i).toString()));
                            stringBlogNameArray[i]=jsonImgObj.getString("blog_name");
                            stringSummaryArray[i]=jsonImgObj.getString("summary");
                            jsonArrayPhoto = jsonImgObj.getJSONArray("photos");
                            jsonPhotoObj = jsonArrayPhoto.getJSONObject(0);
                            jsonArrayAltSizePhoto = jsonPhotoObj.getJSONArray("alt_sizes");
                            jsonObjAltSize = jsonArrayAltSizePhoto.getJSONObject(0);
                            imageUrl = jsonObjAltSize.getString("url");
                            stringPhotoUrlArray[i]=imageUrl;
                            Log.e("enak sekali",arrayList.get(i).toString());
                        }
                    }

//                    for (Object o:arrayList
//                         ) {
//                        Log.e("ENak2",o.toString());
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Picasso.with(context)
                    .load(stringPhotoUrlArray[0])
                    .resize(1000, 1000)
                    .into(imageView1);

            Picasso.with(context)
                    .load(stringPhotoUrlArray[1])
                    .resize(1000, 1000)
                    .into(imageView2);

            Picasso.with(context)
                    .load(stringPhotoUrlArray[2])
                    .resize(1000, 1000)
                    .into(imageView3);

            Picasso.with(context)
                    .load(stringPhotoUrlArray[3])
                    .resize(1000, 1000)
                    .into(imageView4);

            Picasso.with(context)
                    .load(stringPhotoUrlArray[4])
                    .resize(2000, 2000)
                    .into(imageView5);

            textView1.setText(stringBlogNameArray[0]);
            textView2.setText(stringBlogNameArray[1]);
            textView3.setText(stringBlogNameArray[2]);
            textView4.setText(stringBlogNameArray[3]);
            textView5.setText(stringBlogNameArray[4]);
                //Log.i("testimageUrl  ","="+jsonStr);
        }

    }
}
