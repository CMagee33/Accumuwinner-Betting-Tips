package accumuwinner.network;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by mmckillion on 02/12/14.
 */
public class AppRegistration {

    public static void loginUser(String username, String password, Callback<String> cb) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NetworkGlobals.SERVER_URL)
                .build();

        IAppRegistration loginService = restAdapter.create(IAppRegistration.class);
        loginService.loginUser(username, password, cb);
    }
}
