package com.example.arduino2eva4;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

public class ControlLedActivity extends AppCompatActivity {

    private static final String URL_WRITE = "https://api.thingspeak.com/update?api_key=2WEANBX84NMG0GJ5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_led_activity);
    }

    public void ledOn(View view) {
        RequestParams params = new RequestParams("field1", "1");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_WRITE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String(responseBody);
                    Toast.makeText(ControlLedActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Handle failure
            }
        });
    }

    public void ledOff(View view) {
        String url = "https://api.thingspeak.com/update?api_key=2WEANBX84NMG0GJ5&field1=0";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String(responseBody);
                    Toast.makeText(ControlLedActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Handle failure
            }
        });
    }
}
