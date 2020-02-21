package changhua.com.hanziflash.ui.lessonlist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import java.util.List;

import changhua.com.hanziflash.LessonListActivity;
import changhua.com.hanziflash.ModifyLesssonActivity;
import changhua.com.hanziflash.R;
import changhua.com.hanziflash.data.LessonBase;
import changhua.com.hanziflash.model.LessonData;


public class LessonListFragment extends Fragment implements MyAdapter.ItemClickListener, MyBaseAdapter.EditBtClickListener {


    private LessonListViewModel mViewModel;

    private RecyclerView recyclerView;
    //private RecyclerView.Adapter mAdapter;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static LessonListFragment newInstance() {
        return new LessonListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lesson_list_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        List<LessonBase> items = LessonData.getInstance().getLessionList();

        // define an adapter
        mAdapter = new MyAdapter();

        mAdapter.setData(items);

        mAdapter.setClickListener(this);
        mAdapter.seteEditClickListener(this);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //List<LessonItem> items = LessonData.getInstance().getLessionList();

        // define an adapter


        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LessonListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(getActivity(), "You clicked on row number " + position, Toast.LENGTH_SHORT).show();

        LessonData.getInstance().setCurrenID(position);

        getActivity().setResult(LessonListActivity.REQUEST_CODE);
        getActivity().finish();

    }

    @Override
    public void onClickEdit(int position) {
        Toast.makeText(getActivity(), "You clicked on row number " + position, Toast.LENGTH_SHORT).show();


        Intent i = new Intent(getContext(), ModifyLesssonActivity.class);

        Bundle b = new Bundle();
        b.putInt("LessonID", position); //Your id
        i.putExtras(b); //Put your id to your next Intent

        //i.putExtra( "LessonID", position);
        startActivity(i);


    }


}
