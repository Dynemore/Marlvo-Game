package com.example.adin.test2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    Intent intentQuiz,intentTumblr;
    Button buttonQuiz,buttonTumblr;

    String[] sentences = new String[]{"What is design?", "Design", "Design is not just", "what it looks like", "and feels like.", "Design", "is how it works.", "- Steve Jobs", "Older people", "sit down and ask,", "'What is it?'", "but the boy asks,", "'What can I do with it?'.", "- Steve Jobs", "Swift", "Objective-C", "iPhone", "iPad", "Mac Mini", "MacBook Pro", "Mac Pro", "爱老婆", "老婆和女儿"};
    private int mCounter = 10;
    private TextSwitcher textSwitcher;
    private HTextView hTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonQuiz= (Button)findViewById(R.id.buttonQuiz);
        buttonTumblr = (Button)findViewById(R.id.buttonTumblr);

        actionTaken();
    }

    protected void actionTaken(){
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentQuiz = new Intent(MainActivity.this, Quiz_Page.class);
                startActivity(intentQuiz);
            }
        });

        buttonTumblr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTumblr = new Intent(MainActivity.this,TumblrArt_Page.class);
                startActivity(intentTumblr);
            }
        });

    }
}
