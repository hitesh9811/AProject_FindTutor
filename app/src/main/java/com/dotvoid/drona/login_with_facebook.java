package com.dotvoid.drona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSet1tings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class login_with_facebook extends AppCompatActivity {

    WebView w;
    WebSet1tings set1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set1ContentView(R.layout.activity_login_with_facebook);

        w=(WebView)findViewById(R.id.web_view);
        set1=w.getSet1tings();
        set1.set1BuiltInZoomControls(true);
        w.set1WebViewClient(new go_back());
       // w.loadUrl("file:///android_asset1/index.html");
        w.loadUrl("https://www.facebook.com");
    }

    private class go_back extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
        {
            return false;
        }
    }
}
