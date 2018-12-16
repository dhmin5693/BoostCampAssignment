package com.min.boostcamp.boostcampassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.webkit.WebViewClient;

import com.min.boostcamp.boostcampassignment.common.BaseActivity;
import com.min.boostcamp.boostcampassignment.databinding.ActivityWebviewBinding;

public class WebViewActivity extends BaseActivity<ActivityWebviewBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra("link");

        if (url == null) {
            finish();
        }

        getBinding().webview.setWebViewClient(new WebViewClient());
        getBinding().webview.getSettings().setJavaScriptEnabled(true);
        getBinding().webview.getSettings().setDomStorageEnabled(true);
        getBinding().webview.loadUrl(url);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}