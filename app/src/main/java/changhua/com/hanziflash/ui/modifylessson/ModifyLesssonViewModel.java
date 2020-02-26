package changhua.com.hanziflash.ui.modifylessson;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import changhua.com.hanziflash.model.LessonData;

public class ModifyLesssonViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    public int lessonID;
    public ObservableField<String> lessonName;
    public ObservableField<String> word;

    private MutableLiveData<Boolean> onEndLive = new MutableLiveData<>();

    public MutableLiveData<Boolean> getOnEndLive() {
        return onEndLive;
    }

    public ModifyLesssonViewModel(Application app) {
        super(app);
        word = new ObservableField<>("");
        lessonName = new ObservableField<>("");
    }

    public void onClickedSave() {

        if (lessonID < 0) {
            LessonData.getInstance().appendNewLesson(getApplication(), lessonName.get(), word.get());
        } else {
            LessonData.getInstance().setLesson(getApplication(), lessonID, lessonName.get(), word.get());
        }
        onEndLive.setValue(true);
    }


    public void onClickedDelete() {

        LessonData.getInstance().removeLesson(getApplication(), lessonID);
        onEndLive.setValue(true);
    }


    public void initWord(int lessonID) {
        this.lessonID = lessonID;
        if (lessonID < 0) {
            return;
        }
        word.set(LessonData.getInstance().getHanziAsString(lessonID));
        lessonName.set(LessonData.getInstance().getLessonName(lessonID));

    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

}
