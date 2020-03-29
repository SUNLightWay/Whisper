package com.example.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.bumptech.glide.Glide;
import com.example.myapplication.ui.plan.PlanBaseFragment;
import com.example.myapplication.ui.plan.PlanFragment;
import com.example.myapplication.util.DBUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //public class MainActivity extends AppCompatActivity implements OnBannerListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plan, R.id.navigation_mail, R.id.navigation_find, R.id.navigation_my)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //设置底部导航栏不会遮挡布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = DBUtil.getConn();
                if (connection != null){
                    Log.d("MainActivity", "connection return succ");
                }else{
                    Log.d("MainActivity", "connection return fail");

                }
            }
        }).start();

        //轮播图初始化
        //initView();
    }

//    //自定义的图片加载器
//    public class GlideLoader extends ImageLoader implements ImageLoaderInterface {
//
//        @Override
//        public void displayImage(Context context, Object path, View imageView) {
//            Glide.with(context).load((Integer) path).into((ImageView) imageView);
//        }
//
//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            return new ImageView(context);
//        }
//
//    }
//
//    /**
//     * 轮播图组件初始化
//     */
//    private void initView() {
//        setContentView(R.layout.fragment_find);
//        /*发现轮播图组件*/
//        Banner banner;
//        ArrayList<Integer> list_path;
//        ArrayList<String> list_title;
//        banner = (Banner) findViewById(R.id.banner);
//        //放图片地址的集合
//        list_path = new ArrayList<>();
//        //放标题的集合
//        list_title = new ArrayList<>();
//
//        list_path.add(R.drawable.banner_3);
//        list_path.add(R.drawable.banner_5);
//        list_path.add(R.drawable.banner_6);
//        list_title.add("好好学习");
//        list_title.add("天天向上");
//        list_title.add("热爱生活");
//        //设置内置样式，共有六种可以点入方法内逐一体验使用。
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        //设置图片加载器，图片加载器在下方
//        banner.setImageLoader(new MainActivity.GlideLoader());
//        //设置图片网址或地址的集合
//        banner.setImages(list_path);
//        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
//        //设置轮播间隔时间
//        banner.setDelayTime(3000);
//        //设置是否为自动轮播，默认是“是”。
//        banner.isAutoPlay(true);
//        //设置指示器的位置，小点点，左中右。
//        banner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//                .setOnBannerListener(this)
//                //必须最后调用的方法，启动轮播图。
//                .start();
//    }
//
//    @Override
//    public void OnBannerClick(int position) {
//        Log.i("tag", "你点了第"+position+"张轮播图");
//    }

}
