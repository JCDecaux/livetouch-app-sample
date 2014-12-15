package com.jcdecaux.livetouch.livetouchsampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jcdecaux.jcdroid.fwk.api.JCDroidService;
import com.jcdecaux.jcdroid.fwk.api.settings.InternalSettingsManager;
import com.jcdecaux.jcdroid.system.JCDroidSystem;
import com.jcdecaux.jcdroid.web.NativeAPIWrapper;

public class WebAPIFragment extends MainActivity.PlaceholderFragment {

    private static final String JCDROIDJSINTERFACE_NAME = "jcdroid";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webfragment_main, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        WebView webView = (WebView) getView().findViewById(R.id.webview);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        // Define settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(false);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(getActivity().getFilesDir().getPath());
        webSettings.setGeolocationDatabasePath(getActivity().getFilesDir().getPath());
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportMultipleWindows(false);

        NativeAPIWrapper itf = new NativeAPIWrapper(getActivity());
        webView.addJavascriptInterface(itf, JCDROIDJSINTERFACE_NAME);

        webView.loadUrl("file:///android_asset/websample.html");
    }

}
