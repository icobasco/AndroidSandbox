package com.icobasco.sandbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.icobasco.sandbox.Fields;
import com.icobasco.sandbox.R;

public class DisplayActivity extends AppCompatActivity {

    private final static String TAG = "DisplayActivity";
    private TextView tvMessageFromStarter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tvMessageFromStarter = findViewById(R.id.tvMessageFromStarter);
        Intent i = getIntent();
        Fields fields = (Fields)i.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        if (fields != null) {
            tvMessageFromStarter.setText("Ricevuto: " + fields.toString());
        }
        else {
            Log.e(TAG, "Error: null object received!");
        }

    }
}
