package com.example.adin.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Characters_Detail_Page extends AppCompatActivity {

    Button btnNext;
    Bundle extras2;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters__detail__page);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("name");
        String description = extras.getString("description");
        String url = extras.getString("url");
        int counter = extras.getInt("counter");

        btnNext = (Button)findViewById(R.id.btnNext);

        TextView textViewDetail = (TextView)findViewById(R.id.textViewDetails);
        TextView textViewName = (TextView)findViewById(R.id.textViewName);
        textViewName.setText(name.toString());
        textViewDetail.setText("This is the following description = " + description +
                                " .More Clear about this character in this url : "+url);

        i = new Intent(this,Quiz_Page.class);
        extras2 = new Bundle();
        extras.putInt("counter",counter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtras(extras2);
                startActivity(i);
            }
        });

    }


}
