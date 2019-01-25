package changhua.com.hanziflash.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import changhua.com.hanziflash.model.Lesson;

public class LessonItem extends BaseObservable {

    private String lessonName;
    private int wordsCount;
    private String wordsDemo;

    private int lessionID;


    Lesson lesson;

    public LessonItem(Lesson lesson) {
        this.lesson = lesson;
        this.lessonName = lesson.getLessonName();
        this.wordsCount = lesson.getWordsCount();
        this.wordsDemo = lesson.getAllWords().substring(0,10);
        this.lessionID = lesson.getLessonID();
    }
    public LessonItem(String lessonName, int wordsCount, String wordsDemo) {

        this.lessonName = lessonName;
        this.wordsCount = wordsCount;
        this.wordsDemo = wordsDemo;
        //lesson = new Lesson( lessonName, wordsCount, wordsDemo);
    }

    @Bindable
    public String getLessonName() { return this.lessonName;}

    @Bindable
    public int getWordsCount() { return lesson.getWordsCount();}
    @Bindable
    public String getWordsDemo() {
        return this.wordsDemo;
    }


    public  void setLessonName(String lessonName){
        this.lessonName = lessonName;
        //notifyPropertyChanged(BR.location);
    }

    public void setWordsDemo(String wordsDemo) {
        this.wordsDemo= wordsDemo;
        //notifyPropertyChanged(BR.celsius);
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount= wordsCount;
        //notifyPropertyChanged(BR.celsius);
    }


}
