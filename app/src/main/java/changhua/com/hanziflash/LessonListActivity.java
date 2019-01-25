package changhua.com.hanziflash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import changhua.com.hanziflash.ui.lessonlist.LessonListFragment;

public class LessonListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LessonListFragment.newInstance())
                    .commitNow();
        }
    }
}
