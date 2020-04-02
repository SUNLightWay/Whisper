package com.example.myapplication.service;

import com.example.myapplication.module.NoteInfo;

import java.util.List;

public interface NoteService {
    /**
     * 获取团队记录
     * @return
     */
    public List<NoteInfo> getTeamNotes(String teamId);

    /**
     * 增加记录
     * @return
     */
    public boolean addNote(NoteInfo noteInfo);

    /**
     * 删除记录
     * @param noteId
     * @return
     */
    public boolean deleteNote(String noteId);

    /**
     * 更新记录
     * @param noteInfo
     * @return
     */
    public boolean updateNote(NoteInfo noteInfo);
}
