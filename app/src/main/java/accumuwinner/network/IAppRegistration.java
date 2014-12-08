package accumuwinner.network;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by mmckillion on 02/12/14.
 */
public interface IAppRegistration {

    @POST("/login")
    public void loginUser(@Part("registrationId") String regId,
                          @Part("organizationId") String orgId,
                          Callback<String> cb);
}
