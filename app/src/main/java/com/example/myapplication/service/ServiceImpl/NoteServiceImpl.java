package com.example.myapplication.service.ServiceImpl;

import com.example.myapplication.module.NoteInfo;
import com.example.myapplication.service.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private final String TAG = "NoteServiceImpl";
    NoteService noteDao = new NoteServiceImpl();

    @Override
    public List<NoteInfo> getTeamNotes(String teamId) {
        return noteDao.getTeamNotes(teamId);
    }

    @Override
    public boolean addNote(NoteInfo noteInfo) {
        return noteDao.addNote(noteInfo);
    }

    @Override
    public boolean deleteNote(String noteId) {
        return noteDao.deleteNote(noteId);
    }

    @Override
    public boolean updateNote(NoteInfo noteInfo) {
        return noteDao.updateNote(noteInfo);
    }
}
