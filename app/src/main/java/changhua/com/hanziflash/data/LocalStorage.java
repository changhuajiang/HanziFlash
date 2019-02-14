package changhua.com.hanziflash.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class LocalStorage {

    private Context contex;
    private static LocalStorage instance;

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    private LocalStorage(Context c) {
        contex = c;
    }

    public static LocalStorage getInstance(Context c) {
        if (instance == null) {
            instance = new LocalStorage(c);
        }
        return instance;
    }

    // Save as Json

    public void saveForgetWords(HashSet<String> forgetWords) {
        SharedPreferences.Editor editor = contex.getSharedPreferences(MY_PREFS_NAME, contex.MODE_PRIVATE).edit();
        editor.putStringSet("forget", forgetWords);
        editor.apply();
    }

    public Set<String> loadForgetWords() {
        //
        SharedPreferences sp = contex.getSharedPreferences(MY_PREFS_NAME, contex.MODE_PRIVATE);
        Set<String> forgetWords = sp.getStringSet("forget", null);
        if (forgetWords == null) {
            forgetWords = new HashSet<String>();
        }

        return forgetWords;
    }

    public void saveAllLession( String jsonText ) {
        SharedPreferences.Editor editor = contex.getSharedPreferences(MY_PREFS_NAME, contex.MODE_PRIVATE).edit();
        editor.putString("hanzi", jsonText);
        editor.apply();

    }

    public String loadAllLession() {
        //
        SharedPreferences sp = contex.getSharedPreferences(MY_PREFS_NAME, contex.MODE_PRIVATE);
        String hanzi = sp.getString("hanzi", null);

        return hanzi;
    }
}
