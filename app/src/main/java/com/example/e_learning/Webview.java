package com.example.e_learning;

import android.content.Context;
import android.net.Uri;
import android.util.Log;


import androidx.browser.customtabs.CustomTabsIntent;
import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

public class Webview {
    Context ctx;
    String url_web;

    public Webview(Context context, String url_web) {
        this.ctx = context;
        this.url_web = url_web;
        loadwebview();
        Log.i("daat_url", "Webview: "+url_web);
    }
    String pdf = "https://sipaten.jambikota.go.id/file-print/";
    public void loadwebview() {
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .enableUrlBarHiding()
                .setToolbarColor(ctx.getResources().getColor(R.color.gray_btn_bg_color))
                .addDefaultShareMenuItem()
                .build();
        CustomTabsHelper.addKeepAliveExtra(ctx, customTabsIntent.intent);
        CustomTabsHelper.openCustomTab(ctx, customTabsIntent,

                Uri.parse("http://drive.google.com/viewerng/viewer?embedded=true&url=http://192.168.43.48/penjas/materi/"+url_web),
                new WebViewFallback());
    }

}
