package com.vsdata.cordova.plugin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.atomic.AtomicBoolean;

public class CilicoScanner extends CordovaPlugin {
    private static final String TAG = "CilicoScanner";
    public static final int REQUEST_CODE = 1;

    private CallbackContext callbackContext;
    private String m_Broadcastname;

    private static AtomicBoolean registered = new AtomicBoolean(false);

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        configScanner();
        Log.d(TAG, "Initializing CilicoScanner");
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if ("register".equals(action)) {
            if (registered.compareAndSet(false, true)) {
                // 注册监听广播
                initHookEvent();
            }
            return true;
        }
        return false;
    }

    private void configScanner() {
        Context context = getApplicationContext();

        int scanmode = ScanHelper.getBarcodeReceiveModel(context);
        boolean bleft = ScanHelper.getScanSwitchLeft(context);
        boolean bright = ScanHelper.getScanSwitchRight(context);
        boolean bsound = ScanHelper.getScanSound(context);
        
        if (!bsound) {
            ScanHelper.setScanSound(context, true);
        }
        if (!bleft) {
            ScanHelper.setScanSwitchLeft(context, true);
        }
        if (!bright) {
            ScanHelper.setScanSwitchRight(context, true);
        }
        if (scanmode != 2) {
            ScanHelper.setBarcodeReceiveModel(context, 2);
        }
    }

    /**
     * Use to get the Application Context
     * 
     * @return
     */
    private Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Use to get the current Cordova Activity
     *
     * @return your Cordova activity
     */
    private Activity getActivity() {
        return this.cordova.getActivity();
    }

    /**
     * Use to get the Web View Context
     *
     * @return
     */
    private Context getWebViewContext() {
        return super.webView.getContext();
    }

    /**
     * Initializing Hook Event
     * You ABSOLUTELY need to precise getActivity().getApplicationContext()
     * before registerReceiver() otherwise it won't get the good context.
     */
    public void initHookEvent() {
        IntentFilter filter_hook = new IntentFilter();
        m_Broadcastname = "com.barcode.sendBroadcast";
        filter_hook.addAction(m_Broadcastname);
        getActivity().getApplicationContext().registerReceiver(broadcastReceiver_hook, filter_hook);
    }

    /**
     * Is natively created by extending CordovaPlugin
     */
    public BroadcastReceiver broadcastReceiver_hook = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(m_Broadcastname)) {
                String str = intent.getStringExtra("BARCODE");
                if (!"".equals(str)) {
                    final Intent i = new Intent("CilicoScanner");
                    Bundle b = new Bundle();
                    b.putString("code", str);
                    i.putExtras(b);
                    LocalBroadcastManager.getInstance(getWebViewContext()).sendBroadcastSync(i);
                }
            }
        }
    };
}
