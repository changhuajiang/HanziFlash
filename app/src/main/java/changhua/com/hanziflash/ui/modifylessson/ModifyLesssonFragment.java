package changhua.com.hanziflash.ui.modifylessson;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import changhua.com.hanziflash.R;
import changhua.com.hanziflash.databinding.ModifyLesssonFragmentBinding;

public class ModifyLesssonFragment extends Fragment {

    private ModifyLesssonViewModel mViewModel;
    ModifyLesssonFragmentBinding binding;


    public static ModifyLesssonFragment newInstance() {
        return new ModifyLesssonFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        int index = -1;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            index = this.getArguments().getInt("LessonID");
        }

        mViewModel = new ModifyLesssonViewModel(getActivity().getApplication()); //ViewModelProviders.of(this).get(ModifyLesssonViewModel.class);
        mViewModel.initWord(index);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.modify_lessson_fragment, container, false);

        binding.setViewModel(mViewModel);

        View view = (View) binding.getRoot();


        MutableLiveData<Boolean> event = mViewModel.getOnEndLive();
        event.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                getActivity().finish();
            }
        });
        return view;
        //return inflater.inflate(R.layout.modify_lessson_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(ModifyLesssonViewModel.class);
        // TODO: Use the ViewModel
    }

}
