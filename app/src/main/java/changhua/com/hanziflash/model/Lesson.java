package changhua.com.hanziflash.model;



public class Lesson {


    private String  lessonName;
    private int     wordsCount;
    private String  wordsDemo;
    private String  allWords;
    private int     lessonID;

    public  Lesson(String lessonName, String allWords) {

        this.lessonName = lessonName;
        //this.wordsCount = wordsCount;
        this.allWords = allWords;
    }


    public String getLessonName() { return lessonName;}


    public int getWordsCount() { return wordsCount;}

    public String getWordsDemo() {
        return wordsDemo;
    }

    public  void setLessonName(String lessonName){
        this.lessonName = lessonName;

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

    public void setAllWords(String allWords) {
        this.allWords= allWords;

    }
    public String getAllWords( ) {
        return this.allWords;

    }
}
