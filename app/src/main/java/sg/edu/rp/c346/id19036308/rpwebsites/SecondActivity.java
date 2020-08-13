package sg.edu.rp.c346.id19036308.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SecondActivity extends AppCompatActivity {

    WebView wvRPsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        wvRPsite = findViewById(R.id.webViewRPsite);
        //Extract the URL from the Intent
        Intent intentReceive = getIntent();
        String url = intentReceive.getStringExtra("url");

        //Initialized the Client Window
        wvRPsite.setWebViewClient(new WebViewClient());

        //Configure the Webview
        wvRPsite.getSettings().setBuiltInZoomControls(true);
        wvRPsite.getSettings().setJavaScriptEnabled(true);

        wvRPsite.loadUrl(url);


    }
}
