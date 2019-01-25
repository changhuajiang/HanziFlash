package changhua.com.hanziflash.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import changhua.com.hanziflash.LessonListActivity;
import changhua.com.hanziflash.R;
import changhua.com.hanziflash.data.LessonData;
import changhua.com.hanziflash.databinding.MainFragmentBinding;

public class MainFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mViewModel;
    private MainFragmentBinding  binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        LessonData.getInstance().initLessonData(this.getActivity());

        mViewModel = new MainViewModel(this.getActivity());
        mViewModel.init(getActivity());


        binding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);

        View view = (View) binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setViewModel(mViewModel);
        binding.setListener(this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        TextView mCard = (TextView) view.findViewById(R.id.TextCard);
        //mWebView = (WebView) findViewById(R.id.webView);

        Typeface fontFace = Typeface.createFromAsset(getActivity().getAssets(),
                "simkai.ttf");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        Log.v( "mywordcard", "height= " + height );
        Log.v( "mywordcard", "width= " + width );

        mCard.setTypeface( fontFace );
        mCard.setTextSize(TypedValue.COMPLEX_UNIT_PX, width- 32);
        mCard.setTextColor(Color.BLACK);

        return view;

    }
    @Override
    public void onResume( ) {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    final GestureDetector gesture = new GestureDetector(getActivity(),
            new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    //Log.i(TAG, "onFling has been called!");
                    final int SWIPE_MIN_DISTANCE = 120;
                    final int SWIPE_MAX_OFF_PATH = 250;
                    final int SWIPE_THRESHOLD_VELOCITY = 200;
                    try {
                        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                            return false;
                        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            //Log.i(Constants.APP_TAG, "Right to Left");
                            mViewModel.onClickedBtNext();
                        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            //Log.i(Constants.APP_TAG, "Left to Right");
                            mViewModel.onClickedBtPre();
                        }
                    } catch (Exception e) {
                        // nothing
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
            }
    });

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mViewModel.handleActivityResult( requestCode, resultCode);
    }


    @Override
    public void onClick(View view) {
        if (view == binding.more) {
            Intent i = new Intent(getContext(), LessonListActivity.class);
            startActivityForResult(i, LessonListActivity.REQUEST_CODE );
        }
    }

}
