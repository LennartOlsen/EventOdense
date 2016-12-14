package com.example.nikolai.eventodense;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nikolai.eventodense.models.Event.Event;

public class TileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile);

        Intent intent = getIntent();
        Event event = intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(40);
        textView.setTextColor(Color.parseColor(event.getColor()));
        textView.setText(event.getName());

        TextView textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewDescription.setTextSize(20);
        textViewDescription.setText(event.getDescription());

    }

}

