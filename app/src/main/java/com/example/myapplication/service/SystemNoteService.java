package com.example.myapplication.service;

import com.example.myapplication.module.SystemNoteInfo;

import java.util.List;

public interface SystemNoteService {

    /**
     * 新增note
     * @param noteInfo
     * @return
     */
    public Boolean addNote(SystemNoteInfo noteInfo);


    /**
     * 查询未读消息
     * @param userId
     * @return
     */
    public List<SystemNoteInfo> findNoteNotRead(String userId);


    /**
     * 将消息设置为已读
     * @param noteId
     * @return
     */
    public Boolean updateReadStatus(String noteId);


    /**
     * 根据类型查找系统公告
     * @param userId
     * @param type
     * @return
     */
    public List<SystemNoteInfo> findNoteByType(String userId, int type);
}
