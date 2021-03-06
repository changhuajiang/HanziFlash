package changhua.com.hanziflash.ui.lessonlist;

import android.view.View;

import java.util.List;

import changhua.com.hanziflash.R;
import changhua.com.hanziflash.data.LessonBase;


public class MyAdapter extends MyBaseAdapter {
    private List<LessonBase> data;

    //private ItemClickListener mClickListener;

    public MyAdapter() {
    }

    public void setData(List<LessonBase> myDataset) {
        if (data != null) {
            data.clear();
        }
        data = myDataset;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.rowlayout;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }


}