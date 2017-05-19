package com.example.adin.test2;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Quiz_Page extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    Context context = this;
    String imageUrl="",characterName1="",characterName2="",characterName3="",characterName4="",characterDescription="",wikiUrl="";
    ImageView imageview;
    Button button1,button2,button3,button4;
    Integer quizCounter=0;



    /*  total data 1485
     *  offset itu "page" dari JSON data (kalo max 100 brarti ada 15 halaman)
     *  limit itu jumlah data yang ditampilkan per page, di marvel max 100
     *  quiznumber1,2,3,4 buat ngerandom nomor kuis (jawabannya terutama)
    */
    Integer offset=0,limit=100, quizNumber1=0,quizNumber2=0,quizNumber3=0,quizNumber4=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__page);
        imageview = (ImageView)findViewById(R.id.imageView1);
        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        button4 = (Button)findViewById(R.id.btn4);

        button1.setTypeface(EasyFonts.robotoMedium(this));
        button2.setTypeface(EasyFonts.robotoMedium(this));
        button3.setTypeface(EasyFonts.robotoMedium(this));
        button4.setTypeface(EasyFonts.robotoMedium(this));

        new GetImages().execute();

    }

    protected String getAnswers(){
        final Intent i = new Intent(this,Characters_Detail_Page.class);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText().toString().equalsIgnoreCase(characterName1)){
                    Bundle extras = new Bundle();
                    quizCounter++;
                    extras.putString("name",characterName1);
                    extras.putString("description",characterDescription);
                    extras.putString("url",wikiUrl);
                    extras.putInt("counter",quizCounter);
                    //Log.i("deskripsi:",characterDescription);
                    i.putExtras(extras);
                    startActivity(i);

                }
                else{

                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText().toString().equalsIgnoreCase(characterName1)){
                    Bundle extras = new Bundle();
                    quizCounter++;
                    extras.putString("name",characterName1);
                    extras.putString("description",characterDescription);
                    extras.putString("url",wikiUrl);
                    extras.putString("counter",String.valueOf(quizCounter));
                    //Log.i("deskripsi:",characterDescription);
                    i.putExtras(extras);
                    startActivity(i);
                }
                else{

                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button3.getText().toString().equalsIgnoreCase(characterName1)){
                    Bundle extras = new Bundle();
                    quizCounter++;
                    extras.putString("name",characterName1);
                    extras.putString("description",characterDescription);
                    extras.putString("url",wikiUrl);
                    extras.putString("counter",String.valueOf(quizCounter));
                    //Log.i("deskripsi:",characterDescription);
                    i.putExtras(extras);
                    startActivity(i);

                }
                else{

                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button4.getText().toString().equalsIgnoreCase(characterName1)){
                    Bundle extras = new Bundle();
                    quizCounter++;
                    extras.putString("name",characterName1);
                    extras.putString("description",characterDescription);
                    extras.putString("url",wikiUrl);
                    extras.putString("counter",String.valueOf(quizCounter));
                    //Log.i("deskripsi:",characterDescription);
                    i.putExtras(extras);
                    startActivity(i);
                }
                else{

                }
            }
        });

        return "";
    }

    private class GetImages extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();
            Random r4 = new Random();
            Random r5 = new Random();
            offset = r1.nextInt(14 - 0) + 0;
            quizNumber1 = r2.nextInt(100 - 0) + 0;
            quizNumber2 = r3.nextInt(100 - 0) + 0;
            quizNumber3 = r4.nextInt(100 - 0) + 0;
            quizNumber4 = r5.nextInt(100 - 0) + 0;

            Log.i("nomor offset",":"+offset);
            Log.i("nomor1",":"+quizNumber1);
            Log.i("nomor2",":"+quizNumber2);
            Log.i("nomor3",":"+quizNumber3);
            Log.i("nomor4",":"+quizNumber4);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making an URL with timestamp, private key and public key
            String ts = String.valueOf(System.currentTimeMillis());
            String pukey = "bbc0440a9c6d8b4e05523b53b4483476";
            String pvkey = "cd8f50ee0376370afc39e6f582574c22d2a0d578";
            String s = ts+pvkey+pukey ;
            MessageDigest m= null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(s.getBytes(),0,s.length());
            String hash = (new BigInteger(1,m.digest()).toString(16));
            String jsonStr = sh.makeServiceCall("http://gateway.marvel.com/v1/public/characters?ts="+ts+"&apikey="+pukey+
                                                "&hash="+hash+"&limit="+limit+"&offset="+offset);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(jsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject jsonData=null;
                try {
                    jsonData = jsonObj.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Getting JSON Array node
                JSONArray characters = null;
                try {
                    characters = jsonData.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Buat nge random data karakter marvelnya
                        JSONObject jsonChar = null,jsonChar2=null,jsonChar3=null,jsonChar4=null;
                        try {

                            do {

                                Random r5 = new Random();
                                quizNumber1 = r5.nextInt(100 - 0) + 0;
                                jsonChar = characters.getJSONObject(quizNumber1);
                                String charUri = jsonChar.getString("resourceURI");
                                //Log.i("uri",charUri);

                                //Bagian ini buat dapetin data dari gambar soal, termasuk nama, deskripsi
                                characterName1 = jsonChar.getString("name");
                                characterDescription = jsonChar.getString("description");
                                //Log.i("deskripsi:",":"+characterDescription);
                                JSONObject jsonImg = jsonChar.getJSONObject("thumbnail");
                                String image = jsonImg.getString("path");
                                String extension = jsonImg.getString("extension");
                                imageUrl = image + "." + extension;
                                Log.i("imageUrl"," : "+imageUrl);

                                //Bagian ini untuk mendapatkan URL wikinya
                                JSONArray jsonDetail = jsonChar.getJSONArray("urls");
                                JSONObject jsonDetailurl = jsonDetail.getJSONObject(1);
                                wikiUrl = jsonDetailurl.getString("url");
                                //Log.i("wikiUrl",":"+wikiUrl);
                            }while (imageUrl.matches(".*image_not_available.*"));

                            //Bagian ini buat dapetin nama2 buat tombol jawaban lainnya
                            jsonChar2 = characters.getJSONObject(quizNumber2);
                            characterName2=jsonChar2.getString("name");
                            jsonChar3 = characters.getJSONObject(quizNumber3);
                            characterName3=jsonChar3.getString("name");
                            jsonChar4 = characters.getJSONObject(quizNumber4);
                            characterName4=jsonChar4.getString("name");

                            //Log.i("gambar",imageUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
            } else {
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
                    .load(imageUrl)
                    .resize(1000, 1000)
                    .into(imageview);

            ArrayList ar =new ArrayList(4);

            ar.add(0,characterName1);
            ar.add(1,characterName2);
            ar.add(2,characterName3);
            ar.add(3,characterName4);
            Collections.shuffle(ar);
            button1.setText(ar.get(0).toString());
            button2.setText(ar.get(1).toString());
            button3.setText(ar.get(2).toString());
            button4.setText(ar.get(3).toString());


            getAnswers();

        }

    }


}
