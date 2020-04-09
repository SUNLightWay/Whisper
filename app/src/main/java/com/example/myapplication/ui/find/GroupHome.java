package com.example.myapplication.ui.find;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.NewTeamAdapter;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.ServiceImpl.TeamServiceImpl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GroupHome extends AppCompatActivity implements OnBannerListener {

    private  final String TAG = "GroupHome";
    private Banner banner;
    private Context context;   //轮播用到的上下文

    private ImageView mIv_back;
    private ImageView mIv_search;
    private ImageView mIv_create;
    private ImageView mGroupHotContent, mNewActivity, mGroupRank;  //组内热帖  最新活动  组内排名
    private LinearLayout mManyGroup,mPostGraduate; //更多

    private List<TeamInfo> data;
    private TeamServiceImpl teamService = new TeamServiceImpl();

    private RecyclerView recyclerView;
    private NewTeamAdapter newTeamAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        setBanner();
        initView();

        ///加载最新成立的小组
        loadNewTeam();

        //返回
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //小组搜索
        mIv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupHome.this,ManyGroupActivity.class);
                startActivity(intent);
            }
        });

        //创建小组
        mIv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupHome.this,CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        //更多小组
        mManyGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupHome.this,ManyGroupActivity.class);
                startActivity(intent);
            }
        });


    }

    private void loadNewTeam() {
        data = teamService.findTeamList();
        Log.e(TAG,"打印列表数据"+data);
        if (data == null){
            Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();
        }else{
            newTeamAdapter = new NewTeamAdapter(context,data);
            //recyclerView横向显示数据
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
           // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setAdapter(newTeamAdapter);
        }

    }

    private void initView() {
        mIv_back = findViewById(R.id.iv_back);
        mIv_search = findViewById(R.id.iv_search_group);
        mIv_create = findViewById(R.id.iv_addGroup);
        mGroupHotContent = findViewById(R.id.iv_groupHotContent);
        mNewActivity = findViewById(R.id.iv_newActivity);
        mGroupRank = findViewById(R.id.iv_groupRank);
        mManyGroup = findViewById(R.id.manyGroup);
       // mPostGraduate = findViewById(R.id.postgraduate);

        recyclerView = findViewById(R.id.rv);

    }

    /*
     * 小组首页轮播图片实现
     */
    private void setBanner() {
        context = GroupHome.this;
        banner = findViewById(R.id.group_banner);
        List imgs = new ArrayList<>();
        imgs.add(getStringFromDrawableRes(context, R.drawable.p1));
        imgs.add(getStringFromDrawableRes(context, R.drawable.p8));
        imgs.add(getStringFromDrawableRes(context, R.drawable.p9));
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
        banner.setIndicatorGravity(BannerConfig.LEFT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                //必须最后调用的方法，启动轮播图。
                .setOnBannerListener(this).start();


    }

    /*
     * 轮播图的点击事件
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
    }

    /*
     *自定义的图片加载器
     */
    private class GroupLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }

    }

    /*
     * 获取资源路径
     */
    public static String getStringFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
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
