package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.dao.BulletinDao;
import com.example.myapplication.dao.DaoImpl.BulletinDaoImpl;
import com.example.myapplication.module.BulletinInfo;
import com.example.myapplication.service.BulletinService;

import java.util.List;

public class BulletinServiceImpl implements BulletinService {
    private BulletinDao bulletinDao = new BulletinDaoImpl();
    private final String TAG = "BulletinServiceImpl";

    @Override
    public boolean addBulletin(BulletinInfo bulletinInfo) {
        return bulletinDao.addBulletin(bulletinInfo);
    }

    @Override
    public List<BulletinInfo> getBulletins() {
        return bulletinDao.getRecentBullets();
    }

    @Override
    public boolean deleteBulletin(BulletinInfo bulletinInfo) {
        return bulletinDao.deleteBulletin(bulletinInfo);
    }

    @Override
    public List<BulletinInfo> getBulletinsById(Integer Id) {
        return bulletinDao.getBulletinsById(Id);
    }

    @Override
    public boolean updateBulletinById(BulletinInfo bulletinInfo) {
        return bulletinDao.updateBulletinById(bulletinInfo);
    }

    @Override
    public boolean clickHeart(BulletinInfo bulletinInfo) {
        return bulletinDao.clickHeart(bulletinInfo);
    }

    @Override
    public boolean cancelHeart(BulletinInfo bulletinInfo) {
        if(bulletinInfo.getHeart() > 0)
            return bulletinDao.clickHeart(bulletinInfo);
        else {
            Log.d(TAG,"取消点赞失败");
            return false;
        }
    }
}
