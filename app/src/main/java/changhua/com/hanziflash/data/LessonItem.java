package changhua.com.hanziflash.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import changhua.com.hanziflash.model.Lesson;

public class LessonItem extends BaseObservable {

    private String lessonName;
    private int wordsCount;
    private String wordsDemo;

    private int lessionID;


    Lesson lesson;

    public LessonItem(Lesson lesson) {
        this.lesson = lesson;
        this.lessonName = lesson.getTitle();
        this.wordsCount = lesson.getWordsCount();
        this.wordsDemo = lesson.getHanzi().substring(0,10);
        this.lessionID = lesson.getLessonID();
    }
    public LessonItem(String lessonName, int wordsCount, String wordsDemo) {

        this.lessonName = lessonName;
        this.wordsCount = wordsCount;
        this.wordsDemo = wordsDemo;

    }

    @Bindable
    public String getLessonName() { return this.lessonName;}

    @Bindable
    public int getWordsCount() { return lesson.getWordsCount();}
    @Bindable
    public String getWordsDemo() {
        return this.wordsDemo;
    }

    @Bindable
    public int getLessionID() {
        return this.lessionID;
    }

    public  void setLessonName(String lessonName){
        this.lessonName = lessonName;
        //notifyPropertyChanged(BR.);
    }

    public void setWordsDemo(String wordsDemo) {
        this.wordsDemo= wordsDemo;
        //notifyPropertyChanged(BR.);
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount= wordsCount;
        //notifyPropertyChanged(BR.);
    }

    public void setLessionID(int lessionID) {
        this.lessionID= lessionID;
        //notifyPropertyChanged(BR.);
    }

}
