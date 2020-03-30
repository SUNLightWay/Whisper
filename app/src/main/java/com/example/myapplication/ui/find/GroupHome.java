package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class GroupHome extends AppCompatActivity implements OnBannerClickListener {
    private Banner banner;
    private Context context;   //轮播用到的上下文
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        setBanner();

    }

    /*
     * 小组首页轮播图片实现
     */
    private void setBanner() {
        context = GroupHome.this;
        banner = findViewById(R.id.group_banner);
        List imgs = new ArrayList<>();
        imgs.add(getStringFromDrawableRes(context,R.drawable.p1));
        imgs.add(getStringFromDrawableRes(context,R.drawable.p2));
        imgs.add(getStringFromDrawableRes(context,R.drawable.p3));
        List img_titles = new ArrayList<>();
        img_titles.add("小组1");
        img_titles.add("小组2");
        img_titles.add("小组3");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GroupLoader());
        //设置图片网址或地址的集合
        banner.setImages(imgs);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(img_titles);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                //必须最后调用的方法，启动轮播图。
                .setOnBannerClickListener(this).start();


    }

    /*
     * 轮播图的点击事件
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
    }

    /*
     *自定义的图片加载器
     */
    private class GroupLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String)path).into(imageView);
        }

    }

    /*
     * 获取资源路径
     */
    public static String getStringFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE +"://"
                + resources.getResourcePackageName(id) +"/"
                + resources.getResourceTypeName(id) +"/"
                + resources.getResourceEntryName(id);
        return path;
    }

    /*
     * 返回界面时，重新轮播图片
     */
    @Override
    protected void onResume() {
        super.onResume();
        banner.start();
    }

    /*
     * 退出界面 ，轮播结束
     */

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
