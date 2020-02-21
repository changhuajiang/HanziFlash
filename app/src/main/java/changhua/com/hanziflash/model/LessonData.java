package changhua.com.hanziflash.model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import changhua.com.hanziflash.data.HanziCollection;
import changhua.com.hanziflash.data.LessonBase;
import changhua.com.hanziflash.data.LocalStorage;


/****
 * record all the lesseons data and the currect select one for main UI
 *
 */
public class LessonData {

    private List<LessonBase>  lessons  = new ArrayList<>();

    private HanziCollection allLessonData;


    private int current = 0;

    private static LessonData instance;

    private  String[] allPinyins = null;


    public static LessonData getInstance(){
        if( instance == null ) {
            instance = new LessonData();
        }
        return instance;
    }
    private  LessonData() {

    }

    public void initLessonData( Context c) {

        String jsonText = LocalStorage.getInstance(c).loadAllLession( );
        loadPinyinFromAsset(c);

        if ( jsonText == null || jsonText.isEmpty()) {
            loadLessonFromAsset(c);
            saveHanziToLocal(c);
        } else {
            getAllLesson(jsonText );
        }

    }

    public List<LessonBase> getLessionList( ) {

        return  lessons;
    }
    private void loadLessonFromAsset( Context c ) {

        try {
            InputStream is = c.getAssets().open("hanzi.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            getAllLesson( json );

        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    private void loadPinyinFromAsset( Context c ) {

        try {
            InputStream is = c.getAssets().open("pinyin.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String pinyinAll= new String(buffer, "UTF-8");

            allPinyins = pinyinAll.split(",");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void getAllLesson(String json ) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        allLessonData = gson.fromJson( json,  HanziCollection.class);

        lessons = allLessonData.collection.lessons;

        for (int i = 0; i < lessons.size(); i++) {
            lessons.get(i).setLessonID(i);
            lessons.get(i).makeShowDemo();
        }
    }

    private void getAllLesson( ) {

    }
    public String[] getHanzi( ){
        return getHanzi(current);
    }


    public String[] getPinyin(String[] hanzi ){
        int wordCount = hanzi.length;
        String[] pinyin = new String[wordCount];

        for ( int i = 0; i < wordCount; i ++  ) {
            char word = hanzi[i].charAt(0);
            pinyin[i] = allPinyins[word-19968];
        }

        return pinyin;
    }

    public String[][] getHanziWithPinyin( ){
        return getHanziWithPinyin(current);
    }

    public String getLessonName( ) {
        return getLessonName(current);
    }


    public void  setCurrenID( int currentID){
        current = currentID;
    }

    /****
     *
     * @param index
     * @return String[0] :  Hanzi belongs to the lesson index
     *         String[1] :  Pinyin match with each hanzi in Sring[0]
     */
    private String[][] getHanziWithPinyin( int index ){
        int wordCount = lessons.get(index).getHanzi().length();
        String[][] hanziWithPinyin = new String[2][wordCount];
        hanziWithPinyin[0] = new String[wordCount];
        hanziWithPinyin[1] = new String[wordCount];

        for ( int i = 0; i < wordCount; i ++  ) {
            char word = lessons.get(index).getHanzi().charAt(i);
            hanziWithPinyin[0][i] = new String( word + "");
            hanziWithPinyin[1][i] = allPinyins[word-19968];
        }

        return hanziWithPinyin;
    }

    private String[] getHanzi( int index ){
        int wordCount = lessons.get(index).getHanzi().length();
        String[]words = new String[wordCount];

        for ( int i = 0; i < wordCount; i ++  ) {
            char word = lessons.get(index).getHanzi().charAt(i);
            words[i] = new String( word + "");
        }

        return words;
    }

    public String getHanziAsString( int index ){

        String allWords = new String(lessons.get(index).getHanzi());
        return allWords;
    }

    public String getLessonName( int index ){

        return( new String(lessons.get(index).getTitle() ));
    }
    public void saveHanziAsString( int index , String lessonName , String newWord){

        lessons.set(index,new LessonBase( lessonName, newWord ));

        // Save
    }

    private MutableLiveData <String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

    public void appendNewLesson( Context c, String lessonName, String allWord) {

        lessons.add( new LessonBase(lessonName,allWord ));

        saveHanziToLocal(c);
    }

    public void removeLesson( Context c, int id) {
        allLessonData.collection.lessons.remove( id);

        saveHanziToLocal(c);

        initLessonData(c);

    }

    public void setLesson( Context c, int id, String lessonName, String allWord) {
        if ( id >=0 && id <lessons.size() ) {
            lessons.set( id, new LessonBase(lessonName,allWord ));
            saveHanziToLocal(c);
        }
    }

    private void saveHanziToLocal( Context c) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        LocalStorage.getInstance(c).saveAllLession( gson.toJson(allLessonData) );
    }


}
