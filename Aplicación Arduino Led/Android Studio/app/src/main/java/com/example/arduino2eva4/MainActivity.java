package com.example.arduino2eva4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLedControl(View view) {
        Intent intent = new Intent(this, com.example.arduino2eva4.ControlLedActivity.class);
        startActivity(intent);
    }

    public void goToSensorStatus(View view) {
        Intent intent = new Intent(this, com.example.arduino2eva4.SensorStatusActivity.class);
        startActivity(intent);
    }

}

