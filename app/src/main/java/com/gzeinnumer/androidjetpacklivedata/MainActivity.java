package com.gzeinnumer.androidjetpacklivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvNumber = findViewById(R.id.tv_number);
        Button btnFetch = findViewById(R.id.btnRandom);

        final MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        final LiveData<String> myNumber = model.getNumber();

        myNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvNumber.setText(s);
                Log.i(TAG, "onChanged: data set in UI");
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.createNumber();
            }
        });


        Log.i(TAG, "onCreate: Owner");
    }
}
