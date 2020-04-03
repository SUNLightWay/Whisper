package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.NoteDao;
import com.example.myapplication.module.NoteInfo;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class NoteDaoImpl implements NoteDao {
    @Override
    public List<NoteInfo> getTeamNotes(String teamId) {
        List<NoteInfo> noteInfos = LitePal.select()
                .where("idteam = ?", teamId)
                .find(NoteInfo.class);
        return noteInfos;
    }

    @Override
    public boolean addNote(NoteInfo noteInfo) {
        noteInfo.setTime(new Date());
        return noteInfo.save();
    }

    @Override
    public boolean deleteNote(String noteId) {
        return (LitePal.deleteAll(NoteInfo.class, "idnote = ?", noteId) > 0 ? true: false);
    }

    @Override
    public boolean updateNote(NoteInfo noteInfo) {
        return noteInfo.save();
    }
}
