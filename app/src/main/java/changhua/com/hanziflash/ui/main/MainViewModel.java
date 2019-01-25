package changhua.com.hanziflash.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import changhua.com.hanziflash.LessonListActivity;
import changhua.com.hanziflash.data.LessonData;
import changhua.com.hanziflash.model.Lesson;



public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public ObservableField <String> word;
    public ObservableField <String> percent;
    public ObservableField <Integer> wordCount;
    public ObservableField <Integer> wordIndex;
    public ObservableField <String> filterBtnText;
    public ObservableField <String> forgetBtnText;

    private String[] words;


    private Set<String> forgetWords ;
    private Context context;
    private int index = 0;
    private String json = null;
    private boolean isAll = true;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static final String TAG = "MainViewModel";


    public MainViewModel( Context c) {
        context = c;
        forgetWords  = new HashSet<String>();
        word = new ObservableField<>("");
        wordCount = new ObservableField<>(0);
        wordIndex = new ObservableField<>(0);
        percent = new ObservableField<>("");
        filterBtnText = new ObservableField<>("Forgot(0)");
        forgetBtnText = new ObservableField<>("Forgot");
        Log.d( "MainViewModel", "construction" );
    }

    public void init(  Context c) {
        Log.d( "MainViewModel", "init" );


        getAllHanzi();
        loadForgetWords();

        wordCount.set(words.length);
        word.set( words[index] );
        String forgetText = String.format("Forgot:(%d)", forgetWords.size());
        filterBtnText.set( forgetText);


        //LessonData.getInstance().getCurrentName().observe(context, nameObserver);
    }
    public void onClickedBtNext() {
        if ( index < words.length -1 ) {
            index ++;
        }
        word.set( words[index] );
        Log.d( "MainViewModel", "ClickNext" );

        //getAllHanzi();
        wordIndex.set(index+1);

        percent.set( " " +index+"/" + words.length );
    }

    public void onClickedBtPre() {
        if ( index >= 1 ) {
            index --;
        }
        word.set( words[index] );
        wordIndex.set(index+1);
        percent.set( " " +index+"/" + words.length );
    }

    public void onClickedBtForget() {

        if ( forgetWords != null ) {

            if ( isAll ) {
                forgetWords.add(words[index]);

            } else {
                forgetWords.remove(words[index]);

            }
            saveForgetWords();
            String forgetText = String.format("Forgot:(%d)", forgetWords.size());
            filterBtnText.set(forgetText);

        } else {
            Log.d( "MainViewModel", "forgetWords is null" );
        }

    }

    private void  getAllHanzi(){
        words =LessonData.getInstance().getHanzi();
    }


    public void onClickedBtAll() {
        getAllHanzi();

        if ( words.length == 0 ) {
            return;
        }
        index =0;
        isAll = true;

        wordCount.set(words.length);
        word.set( words[index] );
        wordIndex.set(index+1);
        percent.set( " " +index+"/" + words.length );
        forgetBtnText.set( "Forgot");

    }


    public void onClickedBtFilter() {
        if ( forgetWords.size() == 0 ) {
            return;
        }

        isAll = false;
        index =0;
        words = forgetWords.toArray(new String[forgetWords.size()]);

        word.set( words[index] );
        wordCount.set(words.length);
        wordIndex.set(index+1);
        percent.set( " " +index+"/" + words.length );
        forgetBtnText.set( "Memorized");
    }



    public void saveForgetWords( ) {
        //
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putStringSet("forget", forgetWords);
        editor.apply();
    }

    public void loadForgetWords( ) {
        //
        SharedPreferences sp = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        forgetWords = sp.getStringSet("forget",null );
        if ( forgetWords == null ) {
            forgetWords  = new HashSet<String>();
        }
    }




    public void handleActivityResult(int requestCode, int resultCode) {
        onClickedBtAll();
    }


    // Create the observer which updates the UI.
    final Observer<String> nameObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable final String newName) {
            // Update the UI, in this case, a TextView.

        }
    };

    // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.



}
