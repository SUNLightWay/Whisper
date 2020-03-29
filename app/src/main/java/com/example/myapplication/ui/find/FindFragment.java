package com.example.myapplication.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;

public class FindFragment extends Fragment {

    private FindViewModel notificationsViewModel;
    private View view;
    private Banner banner;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel.class);
        View root = inflater.inflate(R.layout.fragment_find, container, false);
        view = root;

        initBanner();

	 initView();
        return root;

    }
      /*
    同桌的实现
     */
    private void initView() {
        find_deskmate = this.view.findViewById(R.id.find_deskmate);

        find_deskmate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeskmateActivity.class);
                startActivity(intent);
            }
        });
    }

   
    public void initBanner(){
        banner = view.findViewById(R.id.banner);
        ArrayList<String> imagelist = new ArrayList<>();
        //添加图片
        imagelist.add("drawable/banner_1.jpg");
        imagelist.add("drawable/banner_2.jpg");
        imagelist.add("drawable/banner_3.jpg");

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
                instance.init(ImageLoaderConfiguration.createDefault(getActivity()));
                instance.displayImage((String) path,imageView);
            }
        });
        banner.setImages(imagelist);
        banner.start();
    }
    /**
     * 轮播图组件初始化
     */
 /*   private void initView() {
        banner = (Banner) getActivity().findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add(R.drawable.banner_1);
        list_path.add(R.drawable.banner_2);
        list_path.add(R.drawable.banner_3);
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱生活");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override*/
    /**
     * 处理按钮点击事件
     */
/*    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(getActivity(),"Back up",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(getActivity(),"Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(getActivity(),"Setting",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
        return true;
    }


    //自定义的图片加载器
    private class GlideLoader extends ImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load((Integer) path).into((ImageView) imageView);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            return new ImageView(context);
        }

    }*/
}