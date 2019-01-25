package changhua.com.hanziflash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import changhua.com.hanziflash.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
/*
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


        return false;
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        System.out.println("Inside onScroll() of GenesMotionDetector.java");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        System.out.println("Inside onShowPress() of GenesMotionDetector.java");
    }
    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("Inside onLongPress() of GenesMotionDetector.java");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("Inside onSingleTapUp() of GenesMotionDetector.java");
        return true;
    }


    */
}
