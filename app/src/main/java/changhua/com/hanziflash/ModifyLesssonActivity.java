package changhua.com.hanziflash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import changhua.com.hanziflash.ui.modifylessson.ModifyLesssonFragment;

public class ModifyLesssonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_lessson_activity);

        ModifyLesssonFragment fragment = ModifyLesssonFragment.newInstance();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt("LessonID");
            fragment.setArguments(bundle);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }
}
