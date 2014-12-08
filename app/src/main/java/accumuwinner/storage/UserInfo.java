package accumuwinner.storage;

/**
 * Created by mmckillion on 02/12/14.
 */
public class UserInfo {

    private static UserInfo userInfo;

    private String username = "Test User";

    private String email = "test_user@email.com";

    private String organisation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public static UserInfo getInstance() {
        if(userInfo == null) {
            userInfo = new UserInfo();
        }

        return userInfo;
    }

    private UserInfo() {
    }
}
