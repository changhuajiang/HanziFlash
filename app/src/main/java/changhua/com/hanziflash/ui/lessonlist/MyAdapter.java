package changhua.com.hanziflash.ui.lessonlist;

import android.view.View;

import java.util.List;

import changhua.com.hanziflash.R;
import changhua.com.hanziflash.data.LessonItem;

public class MyAdapter extends MyBaseAdapter {
        private List<LessonItem> data;

        //private ItemClickListener mClickListener;

        public MyAdapter(List<LessonItem> myDataset) {
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