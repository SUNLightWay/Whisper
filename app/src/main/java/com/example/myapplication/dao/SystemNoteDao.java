package com.example.myapplication.dao;

import com.example.myapplication.module.SystemNoteInfo;

import java.util.List;

public interface SystemNoteDao {

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
}
