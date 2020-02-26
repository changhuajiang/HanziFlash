package changhua.com.hanziflash;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class HanziFlashApplication extends Application {

    //LessonData lessonBases = new LessonData();
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!

        Stetho.initializeWithDefaults(this);
    }
}
