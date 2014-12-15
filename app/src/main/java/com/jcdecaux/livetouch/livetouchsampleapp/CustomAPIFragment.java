package com.jcdecaux.livetouch.livetouchsampleapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcdecaux.jcdroid.fwk.api.JCDroidService;
import com.jcdecaux.jcdroid.fwk.api.settings.InternalSettingsManager;
import com.jcdecaux.jcdroid.system.JCDroidSystem;

public class CustomAPIFragment extends MainActivity.PlaceholderFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        InternalSettingsManager ism = (InternalSettingsManager) getActivity().getSystemService(JCDroidService.INTERNAL_SETTINGS_SERVICE);

        ((TextView) getView().findViewById(R.id.device_id)).setText(ism.getDeviceName());
        ((TextView) getView().findViewById(R.id.furniture_type)).setText(ism.getDeviceType());
        ((TextView) getView().findViewById(R.id.platform_env)).setText(ism.getPlatformEnvironment().toString());


        JCDroidSystem jcdroidSystem = JCDroidSystem.getInstance(getActivity());
        ((TextView) getView().findViewById(R.id.os_version)).setText(jcdroidSystem.getProperty("ro.build.id"));
        ((TextView) getView().findViewById(R.id.hardware_name)).setText(jcdroidSystem.getProperty("ro.product.device"));

    }

}
