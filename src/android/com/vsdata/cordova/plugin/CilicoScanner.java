package com.vsdata.cordova.plugin;

import android.content.Intent;
import android.util.Log;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class CilicoScanner extends CordovaPlugin {
    private static final String TAG = "CilicoScanner";
    public static final int REQUEST_CODE = 1;

    private String m_Broadcastname;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing CilicoScanner");
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        initHookEvent();
        return false;
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
                    LocalBroadcastManager.getInstance(this).sendBroadcastSync(i);
                }
            }
        }
    };
}
