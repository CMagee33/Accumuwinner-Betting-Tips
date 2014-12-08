package accumuwinner.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mmckillion on 09/10/2014.
 */
public class SharedPreferencesHandler {

    /**
     * Reads the String value stored in shared preferences for a given key.
     * @param ctx
     * @param prefKey
     * @return The value for the key else null.
     */
    public static String readStringPreferenceForKey(Context ctx, String prefKey) {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_IDENTIFIER,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefKey, null);
    }
    /**
     * Reads the int value stored in shared preferences for a given key.
     * @param ctx
     * @param prefKey
     * @return The value for the key else null.
     */
    public static int readIntPreferenceForKey(Context ctx, String prefKey) {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_IDENTIFIER,
                Context.MODE_PRIVATE);

        //TODO should default to a non possible number
        return sharedPreferences.getInt(prefKey, -1);
    }

    /**
     * Writes the int value into shared preferences for a given key.
     */
    public static void writeIntPreferenceForKey(Context ctx, int value, String prefKey) {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_IDENTIFIER,
                Context.MODE_PRIVATE);

        sharedPreferences.edit().putInt(prefKey, value).apply();
    }

    /**
     * Writes the String value into shared preferences for a given key.
     */
    public static void writeStringPreferenceForKey(Context ctx, String value, String prefKey) {

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFS_IDENTIFIER,
                Context.MODE_PRIVATE);

        sharedPreferences.edit().putString(prefKey, value).apply();
    }

    private static final String SHARED_PREFS_IDENTIFIER = "accumuwinner";
}
