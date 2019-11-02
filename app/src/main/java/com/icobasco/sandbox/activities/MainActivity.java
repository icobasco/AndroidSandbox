package com.icobasco.sandbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.icobasco.sandbox.R;

import com.icobasco.sandbox.Fields;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.icobasco.sandbox.Fields";

    public static final int REQUEST_READ_FIELDS = 1001;

    private Button btReadFields = null;
    private Button btStartActivity = null;
    private Button btStartActivityForResult = null;
    private TextView tvLog = null;
    private TextView tvNotes = null;
    private EditText etInput = null;
    private EditText etPassword = null;
    private ImageView imgLogo = null;


    private Fields fields = null;

    private View.OnClickListener btReadFieldsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "btReadFieldsListener onClick()");
            fields = readFields();
            String outputMex = "[" +  fields.name + "][" + fields.password + "]";
            Log.d(TAG, outputMex);
            tvLog.append("\n" + outputMex);
        }
    };

    private View.OnClickListener btStartActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btStartActivity: {
                    Log.d(TAG, "btStartActivity onClick()");
                    fields = readFields();
                    String outputMex = "Starting another activity...";
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, fields);
                    Log.d(TAG, outputMex);
                    tvLog.append("\n" + outputMex);
                    startActivity(intent);
                    break;
                }
                case R.id.btStartActivityForResult: {
                    Log.d(TAG, "btStartActivity onClick()");
                    fields = readFields();
                    String outputMex = "Starting another activity for result...";
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, fields);
                    Log.d(TAG, outputMex);
                    tvLog.append("\n" + outputMex);
                    startActivityForResult(intent, REQUEST_READ_FIELDS);
                    break;
                }
                default: {
                    tvLog.append("btStartActivityListener without case....");
                }
            }
        }
    };

    private Fields readFields() {
        String input = etInput.getText().toString();
        String password = etPassword.getText().toString();
        Fields fields = new Fields();
        fields.name = input;
        fields.password = password;
        return fields;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btReadFields = findViewById(R.id.btRead);
        btStartActivity = findViewById(R.id.btStartActivity);
        btStartActivityForResult = findViewById(R.id.btStartActivityForResult);
        tvLog = findViewById(R.id.tvLog);
        tvNotes = findViewById(R.id.tvNotes);
        etInput = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        imgLogo = findViewById(R.id.imgLogo);

        btReadFields.setOnClickListener(btReadFieldsListener);
        btStartActivity.setOnClickListener(btStartActivityListener);
        btStartActivityForResult.setOnClickListener(btStartActivityListener);

        printFeatures4Future();
    }


    private void printFeatures4Future() {
        tvNotes.append("\n-) QRCODE\n");
        tvNotes.append("-) DragNDrop\n");
        tvNotes.append("-) Server call + parsing json\n");
        tvNotes.append("-) Device Info\n");
        tvNotes.append("-) Open WebView\n");
        tvNotes.append("-) Drawer (Settings; About; Credits; ...)\n");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);
        // Check which request we're responding to
        if (requestCode == REQUEST_READ_FIELDS) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "Received something....");
            }
        }
    }
}
