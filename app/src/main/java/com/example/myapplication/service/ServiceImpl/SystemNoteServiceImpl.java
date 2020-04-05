package com.example.myapplication.service.ServiceImpl;

import com.example.myapplication.dao.DaoImpl.SystemNoteDaoImpl;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.service.SystemNoteService;

import java.util.List;

public class SystemNoteServiceImpl implements SystemNoteService {
    private final String TAG ="SystemNoteServiceImpl";
    private SystemNoteDaoImpl systemNoteDao = new SystemNoteDaoImpl();

    @Override
    public Boolean addNote(SystemNoteInfo noteInfo) {
        return systemNoteDao.addNote(noteInfo);
    }

    @Override
    public List<SystemNoteInfo> findNoteNotRead(String userId) {
        return systemNoteDao.findNoteNotRead(userId);
    }

    @Override
    public Boolean updateReadStatus(String noteId) {
        return systemNoteDao.updateReadStatus(noteId);
    }

    @Override
    public List<SystemNoteInfo> findNoteByType(String userId, int type) {
        return null;
    }
}
