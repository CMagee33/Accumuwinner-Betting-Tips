package accumuwinner.network;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by mmckillion on 09/10/2014.
 */
public interface IGcmRegistration {

    @Multipart
    @POST("/register")
    public void registerWithServer(@Part("registrationId") String regId,
                                   @Part("organizationId") String orgId,
                                   Callback<String> cb);
}
