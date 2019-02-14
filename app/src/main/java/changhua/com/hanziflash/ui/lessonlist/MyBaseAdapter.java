package changhua.com.hanziflash.ui.lessonlist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import changhua.com.hanziflash.databinding.RowlayoutBinding;
import changhua.com.hanziflash.BR;

public abstract class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private EditBtClickListener mEditBtClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private final RowlayoutBinding binding;

        public MyViewHolder(RowlayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            itemView.setOnClickListener(this);
        }

        public void bind(Object obj) {
            binding.setVariable(BR.lessonRow, obj);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowlayoutBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
        // set the view's size, margins, paddings and layout parameters
        //binding.setListener( this);
        return new MyViewHolder(binding);


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getDataAtPosition(position));
        holder.binding.setItemClickListener(mEditBtClickListener);
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);


    //        // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public void seteEditClickListener(EditBtClickListener itemClickListener) {
        this.mEditBtClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface EditBtClickListener {
        public void onClickEdit(int position);
    }

}