package com.example.myapplication.ui.find;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.EverydayPhrase;
import com.example.myapplication.LocationActivity;
import com.example.myapplication.PunchActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.find.DeskMate.DeskmateActivity;
import com.example.myapplication.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiscoveryFragment extends Fragment implements View.OnClickListener{
    //public class DiscoveryFragment extends Fragment{
    private View mView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.banner_6,
            R.drawable.banner_5,
            R.drawable.banner_4,
            R.drawable.banner_3,
            R.drawable.banner_2
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "No.1",
            "No.2",
            "No.3",
            "No.4",
            "No.5"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    private FindViewModel notificationsViewModel;
    private View view;

    LinearLayout find_deskmate;
    LinearLayout dailyWord;
    LinearLayout team;
    LinearLayout around;
    LinearLayout punch;

    String userId = "phineas";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel.class);
        mView=inflater.inflate(R.layout.fragment_find, null);
        setView();
        view = mView;
        //initView();
        return view;
    }
    private void setView(){
        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);

        find_deskmate = mView.findViewById(R.id.find_deskmate);
        dailyWord = mView.findViewById(R.id.word_daily);
        team = mView.findViewById(R.id.team);
        around = mView.findViewById(R.id.around);
        punch = mView.findViewById(R.id.punch);

        find_deskmate.setOnClickListener(this);
        dailyWord.setOnClickListener(this);
        team.setOnClickListener(this);
        around.setOnClickListener(this);
        punch.setOnClickListener(this);
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));

        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.wish);
                dots.get(oldPosition).setBackgroundResource(R.drawable.wrong);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_deskmate:
                Utils.actionStart(getActivity(), DeskmateActivity.class, null, userId);
                break;
            case R.id.word_daily:
                Utils.actionStart(getActivity(), EverydayPhrase.class, null, userId);
                break;
            case R.id.around:
                Utils.actionStart(getActivity(), LocationActivity.class,null,userId);
                break;
            case R.id.punch:
                Utils.actionStart(getActivity(), PunchActivity.class,null,userId);
                break;
            case R.id.team:
                //Utils.actionStart(getActivity(), TeamActivity.class,null,userId);
                break;
        }
    }

    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

}
