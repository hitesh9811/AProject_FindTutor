package com.dotvoid.drona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class login_with_facebook extends AppCompatActivity {

    WebView w;
    WebSettings set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_facebook);

        w=(WebView)findViewById(R.id.web_view);
        set=w.getSettings();
        set.setBuiltInZoomControls(true);
        w.setWebViewClient(new go_back());
       // w.loadUrl("file:///android_asset/index.html");
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
