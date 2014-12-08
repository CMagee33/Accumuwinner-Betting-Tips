package accumuwinner.network;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GcmRegistration {

    public static void registerWithServer(String regId) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NetworkGlobals.SERVER_URL)
                .build();

        IGcmRegistration registrationService = restAdapter.create(IGcmRegistration.class);
        registrationService.registerWithServer(regId, "orgID", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d("accumuwinner", response.getReason());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("accumuwinner", error.getMessage());
            }
        });
    }
}
