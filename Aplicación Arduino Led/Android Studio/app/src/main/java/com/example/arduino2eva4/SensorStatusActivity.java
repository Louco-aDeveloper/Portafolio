package com.example.arduino2eva4;

import android.os.Bundle;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

public class SensorStatusActivity extends AppCompatActivity {

    private static final String URL_READ = "https://api.thingspeak.com/channels/2780450/fields/1.json?results=3";
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_status_activity);
        txt = findViewById(R.id.sensor_status_txt);
        readJSON();
    }

    public void readJSON() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_READ, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String(responseBody);
                    processJSON(response);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Handle failure
            }
        });
    }

    private void processJSON(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray feeds = root.getJSONArray("feeds");
            StringBuilder texto = new StringBuilder();

            for (int i = 0; i < feeds.length(); i++) {
                String valor = feeds.getJSONObject(i).getString("field1");
                texto.append(valor).append("\n");
            }
            txt.setText(texto.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}