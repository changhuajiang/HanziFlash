package changhua.com.hanziflash.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import changhua.com.hanziflash.model.Lesson;

public class LessonData {

    List<LessonItem> lessonList = new ArrayList<LessonItem>() ;

    List<Lesson>  lessons  = new ArrayList<Lesson>();

    HanziCollection allLessonData;


    int current = 0;

    static LessonData instance;

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
        if ( jsonText == null || jsonText.isEmpty()) {
            loadLessonFromAsset(c);
            saveHanziToLocal(c);
        }

    }

    public List<LessonItem> getLessionList( ) {
        return lessonList;
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

    private void getAllLesson(String json ) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        allLessonData = gson.fromJson( json,  HanziCollection.class);

        for (int i = 0; i < allLessonData.collection.lessons.size(); i++) {

            Lesson lesson = new Lesson(allLessonData.collection.lessons.get(i).title,
                    allLessonData.collection.lessons.get(i).hanzi);

            lesson.setLessonID( i );

            lessonList.add( new LessonItem(lesson ));
            lessons.add(lesson );
        }
    }

    public String[] getHanzi( ){
        return getHanzi(current);
    }

    public void  setCurrenID( int currentID){
        current = currentID;
    }

    public String[] getHanzi( int index ){
        int wordCount = lessons.get(index).getHanzi().length();
        String[]words = new String[wordCount];

        for ( int i = 0; i < wordCount; i ++  ) {

            words[i] = new String( lessons.get(index).getHanzi().charAt(i) + "");
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

        lessons.set(index,new Lesson( lessonName, newWord ));

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
        Lesson lesson = new Lesson(lessonName, allWord);
        lesson.setLessonID(lessonList.size());
        lessonList.add( new LessonItem(lesson ) );

        allLessonData.collection.lessons.add( new LessonBase(lessonName,allWord ));

        saveHanziToLocal(c);

    }

    public void setLesson( Context c, int id, String lessonName, String allWord) {
        if ( id >=0 && id <lessonList.size() ) {
            Lesson lesson = new Lesson(lessonName, allWord);
            lesson.setLessonID(id);
            lessonList.set(id, new LessonItem(lesson));

            allLessonData.collection.lessons.set( id, new LessonBase(lessonName,allWord ));

            saveHanziToLocal(c);
        }
    }

    private void saveHanziToLocal( Context c) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        LocalStorage.getInstance(c).saveAllLession( gson.toJson(allLessonData) );
    }


}
