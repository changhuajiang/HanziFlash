package changhua.com.hanziflash.model;



public class Lesson {

    public String title;
    public String hanzi;

    private int     wordsCount;
    private String  wordsDemo;

    private int     lessonID;

    public  Lesson(String title, String hanzi) {
        this.title = title;
        this.hanzi = hanzi;
    }


    public String getTitle() { return title;}

    public int getWordsCount() { return wordsCount;}

    public String getWordsDemo() {
        return wordsDemo;
    }

    public  void setTitle(String title){
        this.title = title;

    }

    public void setWordsDemo(String wordsDemo) {
        this.wordsDemo= wordsDemo;

    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount= wordsCount;

    }

    public void setLessonID(int lessonID) {
        this.lessonID= lessonID;

    }

    public int getLessonID() {
        return this.lessonID;

    }

    public void setHanzi(String hanzi) {
        this.hanzi = hanzi;

    }
    public String getHanzi( ) {
        return this.hanzi;

    }
}
