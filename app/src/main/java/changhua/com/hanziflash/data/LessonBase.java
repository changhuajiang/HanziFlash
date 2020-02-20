package changhua.com.hanziflash.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class LessonBase extends BaseObservable {
    public String title;
    public String hanzi;
    public String pinyin;


    private int wordsCount;
    private String wordsDemo;
    private int lessonID;


    public LessonBase(String title, String hanzi) {
        this.hanzi = hanzi;
        this.title = title;

        makeShowDemo();
    }

    public void makeShowDemo() {
        wordsCount = hanzi.length();
        if ( wordsCount > 10 ) {
            this.wordsDemo = hanzi.substring(0, 10);
        } else {
            this.wordsDemo = hanzi;
        }
    }
    public LessonBase(String title, String hanzi,String pinyin) {
        this.hanzi = hanzi;
        this.title = title;
        this.pinyin = pinyin;
    }

    @Bindable
    public String getLessonName() { return this.title;}

    @Bindable
    public int getWordsCount() { return wordsCount;}
    @Bindable
    public String getWordsDemo() {
        return this.wordsDemo;
    }

    @Bindable
    public int getLessonID() {
        return this.lessonID;
    }


    public  void setTitle(String title){
        this.title = title;

    }

    public String getTitle() { return title;}


    public void setWordsDemo(String wordsDemo) {
        this.wordsDemo= wordsDemo;

    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount= wordsCount;

    }

    public void setLessonID(int lessonID) {
        this.lessonID= lessonID;

    }

    public void setHanzi(String hanzi) {
        this.hanzi = hanzi;

    }
    public String getHanzi( ) {
        return this.hanzi;

    }
}
