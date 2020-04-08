package com.example.myapplication.ui.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.EverydayPhrase;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.find.DeskMate.DeskmateActivity;
import com.example.myapplication.util.Utils;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;

public class FindFragment extends Fragment implements View.OnClickListener {

    private FindViewModel notificationsViewModel;
    private View view;
    private Banner banner;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;
    String userId = "phineas";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FindViewModel.class);
        View root = inflater.inflate(R.layout.fragment_find, container, false);
        view = root;



	 initView();
        return root;

    }
      /*
    同桌的实现
     */
    private void initView() {
        LinearLayout find_deskmate = this.view.findViewById(R.id.find_deskmate);
        LinearLayout dailyWord = this.view.findViewById(R.id.word_daily);
        LinearLayout team = this.view.findViewById(R.id.team);
        LinearLayout around = this.view.findViewById(R.id.around);
        LinearLayout punch = this.view.findViewById(R.id.punch);

        find_deskmate.setOnClickListener(this);
        dailyWord.setOnClickListener(this);
        team.setOnClickListener(this);
        around.setOnClickListener(this);
        punch.setOnClickListener(this);
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
                //Utils.actionStart(getActivity(), );
                break;
            case R.id.punch:
                //
                break;
            case R.id.team:
                Utils.actionStart(getActivity(),GroupHome.class,null,userId);
                break;
        }
    }
}