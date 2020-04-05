package com.example.myapplication.dao.DaoImpl;

import android.util.Log;

import com.example.myapplication.dao.BulletinDao;
import com.example.myapplication.module.BulletinInfo;

import org.litepal.LitePal;

import java.util.List;

public class BulletinDaoImpl implements BulletinDao {
    private final String TAG = "BulletinDaoImpl";

    @Override
    public boolean addBulletin(BulletinInfo bulletinInfo) {
        return bulletinInfo.save();
    }

    @Override
    public List<BulletinInfo> getBulletins() {
        List<BulletinInfo> BulletinInfos = LitePal.findAll(BulletinInfo.class);
        return BulletinInfos;
    }

    @Override
    public boolean deleteBulletin(BulletinInfo bulletinInfo) {
        return (LitePal.deleteAll(BulletinInfo.class, "idbuttetin = ?", bulletinInfo.getIdButtetin().toString()) > 0 ? true: false);
    }

    @Override
    public List<BulletinInfo> getBulletinsById(Integer Id) {
        return (LitePal.select()
                .where("idbuttetin = ?", Id.toString())
                .find(BulletinInfo.class));
    }

    @Override
    public boolean updateBulletinById(BulletinInfo bulletinInfo) {
        List<BulletinInfo> bulletinInfos = LitePal.select()
                .where("idbuttetin = ?", bulletinInfo.getIdButtetin().toString())
                .find(BulletinInfo.class);
        if (bulletinInfos.size() == 0)
            return false;
        BulletinInfo bulletinInfo1 = bulletinInfos.get(0);

        if(bulletinInfo1.getContent() != bulletinInfo.getContent()){
            bulletinInfo1.setContent(bulletinInfo.getContent());
        }
        if(bulletinInfo1.getAuthor() != bulletinInfo.getAuthor()){
            bulletinInfo1.setAuthor(bulletinInfo.getAuthor());
        }
        if(bulletinInfo1.getTitle() != bulletinInfo.getTitle()){
            bulletinInfo1.setTitle(bulletinInfo.getTitle());
        }
        bulletinInfo1.setTime(bulletinInfo.getTime());
        return bulletinInfo1.save();
    }

    @Override
    public boolean clickHeart(BulletinInfo bulletinInfo) {
        bulletinInfo.setHeart(bulletinInfo.getHeart() + 1);
        return bulletinInfo.save();
    }

    @Override
    public boolean cancelHeart(BulletinInfo bulletinInfo) {
        if(bulletinInfo.getHeart() < 0){
            Log.d(TAG,"点赞失败！");
            return false;
        }
        else {
            bulletinInfo.setHeart(bulletinInfo.getHeart() - 1);
            return bulletinInfo.save();
        }
    }

    @Override
    public boolean updateClickNum(BulletinInfo bulletinInfo){
        bulletinInfo.setClickNum(bulletinInfo.getClickNum() + 1);
        return bulletinInfo.save();
    }

    @Override
    public List<BulletinInfo> getRecentBullets() {
        return LitePal.limit(5).order("time desc").find(BulletinInfo.class);
    }
}
