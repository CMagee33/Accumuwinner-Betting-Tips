package accumuwinner.notification;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import accumuwinner.network.GcmRegistration;
import accumuwinner.storage.SharedPreferencesHandler;

/**
 * Created by mmckillion on 09/10/2014.
 */
public class GcmProvider {

    private Context ctx;
    private GoogleCloudMessaging m_gcm;
    private final String m_googleApiKey = "767561250797";

    private String GCM_REG_KEY = "accumuwinnerGcmKey";
    private String GCM_REG_APP_VERSION = "accumuwinnerAppVerison";

    public GcmProvider(Context ctx) {
        this.ctx = ctx;
    }

    public static void checkGcmExpiry() {

    }

    public String registerGcmToken() {

        //Check if the device is already registered with a GCM token.
        String gcmRegKey = SharedPreferencesHandler.readStringPreferenceForKey(ctx, GCM_REG_KEY);

        if(null == gcmRegKey) {
            generateGcmKey();
        } else {

            /**
            * If the GCM reg key exists, perform an app version check as a new reg id must be
            * generated for each new app version.
            */
            int versionNum = SharedPreferencesHandler.readIntPreferenceForKey(ctx,
                    GCM_REG_APP_VERSION);

            if(versionNum != getAppVersionNum()) {
                generateGcmKey();
            } else {
                return gcmRegKey;
            }
        }

        return null;
    }

    private void generateGcmKey() {
        AsyncTask gcmRegistration = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                String msg = "";
                try {
                    if (m_gcm == null) {
                        m_gcm = GoogleCloudMessaging.getInstance(ctx);
                    }
                    String regid = m_gcm.register(m_googleApiKey);
                    GcmRegistration.registerWithServer(regid);

                    SharedPreferencesHandler.writeStringPreferenceForKey(ctx, regid, GCM_REG_KEY);
                } catch (IOException ex) {
                    Log.e("Accumuwinner", ex.getMessage());
                }

                return null;
            }
        };

        gcmRegistration.execute();
    }

    /**
     * Caries out a check to ensure the Google Services apk is installed
     * on the device.
     * @return Boolean indicating if Google Services are enabled.
     */
    public boolean checkForGoogleServices() {
        int isServiceEnabled = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctx);

        if(isServiceEnabled != ConnectionResult.SUCCESS) {
            if(GooglePlayServicesUtil.isUserRecoverableError(isServiceEnabled)) {
                //TODO: Handle recoverable error
                Log.e("Accumuwinner", "Recoverable error");
            }

            return false;
        }

        return true;
    }

    private int getAppVersionNum() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = ctx.getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("accumuwinner", e.getMessage());
        }

        return -1;
    }
}
