package com.example.myapplication.service;

import com.example.myapplication.module.BulletinInfo;

import java.util.List;

public interface BulletinService {

    /**
     * 新增公告
     * @param bulletinInfo
     * @return
     */
    public boolean addBulletin(BulletinInfo bulletinInfo);

    /**
     * 查询所有公告
     * @return
     */
    public List<BulletinInfo> getBulletins();

    /**
     * 删除指定公告
     * @param bulletinInfo
     * @return
     */
    public boolean deleteBulletin(BulletinInfo bulletinInfo);

    /**
     * 根据公告号查询公告
     * @param Id
     * @return
     */
    public List<BulletinInfo> getBulletinsById(Integer Id);

    /**
     * 更新公告
     * @param bulletinInfo
     * @return
     */
    public boolean updateBulletinById(BulletinInfo bulletinInfo);

    /**
     * 点赞
     * @param bulletinInfo
     * @return
     */
    public boolean clickHeart(BulletinInfo bulletinInfo);

    /**
     * 取赞
     * @param bulletinInfo
     * @return
     */
    public boolean cancelHeart(BulletinInfo bulletinInfo);

}
