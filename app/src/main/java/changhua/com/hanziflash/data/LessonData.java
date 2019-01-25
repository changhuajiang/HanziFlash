package changhua.com.hanziflash.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import changhua.com.hanziflash.model.Lesson;

public class LessonData {

    List<LessonItem> lessonList = new ArrayList<LessonItem>() ;
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


    Lesson[] lessons ;
    public void initLessonData( Context c) {
        loadLessonFromAsset(  c );

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
        try {
            JSONObject root = new JSONObject(json );

            JSONObject obj = root.getJSONObject("collection");
            JSONArray jArry = obj.getJSONArray("lessons");

            lessons  = new Lesson[jArry.length()];
            //words = new String[m_jArry.length()];

            for (int i = 0; i < jArry.length(); i++) {
                //words[i]  = jArry.getString(i);
                lessons[i] = new Lesson(jArry.getJSONObject(i).getString("title" ),
                        jArry.getJSONObject(i).getString("hanzi" ));
                lessons[i].setLessonID( i );
//                lessons[i].setLessonName( jArry.getJSONObject(i).getString("title" ) );
//                lessons[i].setAllWords( jArry.getJSONObject(i).getString("hanzi" ) );
//                lessons[i].setWordsDemo( jArry.getJSONObject(i).getString("hanzi" ).substring(0, 10) );

                lessonList.add( new LessonItem(lessons[i] ));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String[] getHanzi( ){
        return getHanzi(current);
    }

    public void  setCurrenID( int currentID){
        current = currentID;
    }

    public String[] getHanzi( int index ){
        int wordCount = lessons[index].getAllWords().length();
        String[]words = new String[wordCount];

        for ( int i = 0; i < wordCount; i ++  ) {

            words[i] = new String( lessons[index].getAllWords().charAt(i) + "");
        }

        return words;
    }


    private MutableLiveData <String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

}
