package changhua.com.hanziflash.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import changhua.com.hanziflash.data.LocalStorage;
import changhua.com.hanziflash.model.LessonData;


public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<String> title;
    public ObservableField<String> word;
    public ObservableField<String> pinyin;
    public ObservableField<String> percent;
    public ObservableField<Integer> wordCount;
    public ObservableField<Integer> wordIndex;
    public ObservableField<String> filterBtnText;
    public ObservableField<String> forgetBtnText;

    private String[] words;
    private String[] pinyins;


    private Set<String> forgetWords;

    //private Context context;
    private int index = 0;
    private String json = null;
    private boolean isAll = true;
    private boolean isShwoPinyin = false;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static final String TAG = "MainViewModel";


    public MainViewModel(Application c) {
        super(c);
        //context = c;
        forgetWords = new HashSet<String>();
        word = new ObservableField<>("");
        title = new ObservableField<>("");
        pinyin = new ObservableField<>("");
        wordCount = new ObservableField<>(0);
        wordIndex = new ObservableField<>(0);
        percent = new ObservableField<>("");
        filterBtnText = new ObservableField<>("Forgot(0)");
        forgetBtnText = new ObservableField<>("Forgot");
        Log.d("MainViewModel", "construction");
    }

    public void init(Context c) {
        Log.d("MainViewModel", "init");

        getAllHanzi();
        loadForgetWords();

        title.set(LessonData.getInstance().getLessonName());
        wordCount.set(words.length);
        word.set(words[index]);
        setPinyinText();
        String forgetText = String.format("Forgot:(%d)", forgetWords.size());
        filterBtnText.set(forgetText);

        //LessonData.getInstance().getCurrentName().observe(context, nameObserver);
    }

    public void onClickedBtNext() {
        if (index < words.length - 1) {
            index++;
        }
        word.set(words[index]);
        setPinyinText();
        Log.d("MainViewModel", "ClickNext");
        //getAllHanzi();
        wordIndex.set(index + 1);

        percent.set(" " + index + "/" + words.length);
    }

    public void onClickedBtPre() {
        if (index >= 1) {
            index--;
        }
        word.set(words[index]);
        setPinyinText();
        wordIndex.set(index + 1);
        percent.set(" " + index + "/" + words.length);
    }

    public void onClickedBtForget() {

        if (forgetWords != null) {

            if (isAll) {
                forgetWords.add(words[index]);

            } else {
                forgetWords.remove(words[index]);

            }
            saveForgetWords();
            String forgetText = String.format("Forgot:(%d)", forgetWords.size());
            filterBtnText.set(forgetText);

        } else {
            Log.d("MainViewModel", "forgetWords is null");
        }

    }

    private void getAllHanzi() {

        title.set(LessonData.getInstance().getLessonName());

        String[][] hanziWithPinyin = LessonData.getInstance().getHanziWithPinyin();

        words = hanziWithPinyin[0];
        pinyins = hanziWithPinyin[1];

        Random rd = new Random();

        for (int i = 1; i < words.length; i++) {
            int next = rd.nextInt(i + 1);
            swapInString(words, i, next);
            swapInString(pinyins, i, next);
        }

    }


    private void swapInString(String[] array, int pos, int newPos) {
        String tmp = array[pos];
        array[pos] = array[newPos];
        array[newPos] = tmp;
    }

    public void onClickedBtAll() {
        getAllHanzi();

        if (words.length == 0) {
            return;
        }
        index = 0;
        isAll = true;

        wordCount.set(words.length);
        word.set(words[index]);
        setPinyinText();
        wordIndex.set(index + 1);
        percent.set(" " + index + "/" + words.length);
        forgetBtnText.set("Forgot");

    }


    public void onClickedBtFilter() {
        if (forgetWords.size() == 0) {
            return;
        }

        isAll = false;
        index = 0;
        words = forgetWords.toArray(new String[forgetWords.size()]);
        pinyins = LessonData.getInstance().getPinyin(words);
        word.set(words[index]);
        setPinyinText();
        wordCount.set(words.length);
        wordIndex.set(index + 1);
        percent.set(" " + index + "/" + words.length);
        forgetBtnText.set("Memorized");
    }

    private void setPinyinText() {

        if (isShwoPinyin) {
            pinyin.set(pinyins[index]);
        } else {
            pinyin.set("Show pinyin");
        }
    }

    public void onClickedPinyin() {
        isShwoPinyin = true;
        setPinyinText();
        isShwoPinyin = !isShwoPinyin;
    }


    public void saveForgetWords() {

        Log.d("MainViewModel", "saveForgetWords!!");

        LocalStorage.getInstance(getApplication()).saveForgetWords(forgetWords);
    }

    public void loadForgetWords() {

        Log.d("MainViewModel", forgetWords.toString());

        forgetWords = LocalStorage.getInstance(getApplication()).loadForgetWords();

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


}
