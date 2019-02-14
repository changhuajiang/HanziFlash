package changhua.com.hanziflash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void onClickNew(View view) {
        Toast.makeText(this, "You clicked NEW lesson", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, ModifyLesssonActivity.class);
        startActivity(i);
    }

}
