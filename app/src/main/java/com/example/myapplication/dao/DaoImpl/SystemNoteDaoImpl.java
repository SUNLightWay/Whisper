package com.example.myapplication.dao.DaoImpl;

import android.util.Log;

import com.example.myapplication.dao.SystemNoteDao;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.util.ConstUtil;

import org.litepal.LitePal;

import java.util.List;

public class SystemNoteDaoImpl implements SystemNoteDao {

    private final String TAG = "SystemNoteDaoImpl";

    @Override
    public Boolean addNote(SystemNoteInfo noteInfo) {
        return noteInfo.save();
    }

    @Override
    public List<SystemNoteInfo> findNoteNotRead(String userId) {
        List<SystemNoteInfo> systemNoteInfos = LitePal.select()
                .where("to = ? and isread = ?", userId, String.valueOf(ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ))
                .find(SystemNoteInfo.class);
        Log.d(TAG, "findNoteNotRead: " + systemNoteInfos.size());
        return systemNoteInfos;
    }

    @Override
    public Boolean updateReadStatus(String noteId) {
        List<SystemNoteInfo> systemNoteInfos = LitePal.select()
                .where("idnote = ?", noteId)
                .find(SystemNoteInfo.class);
        if (systemNoteInfos.size() == 0)
            return false;
        SystemNoteInfo note = systemNoteInfos.get(0);
        note.setIsRead(ConstUtil.SysNoteRead.SYS_NOTE_ON_READ);
        return note.save();
    }
}
