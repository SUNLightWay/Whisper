package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.PunchDao;
import com.example.myapplication.module.PunchInfo;
import com.example.myapplication.module.SeatmateInfo;

import org.litepal.LitePal;

import java.util.List;

public class PunchDaoImpl implements PunchDao {


    @Override
    public Boolean addPunch(PunchInfo punchInfo) {
        return punchInfo.save();
    }

    @Override
    public List<PunchInfo> findPunchListMyUserId(String userId) {
        return LitePal.select()
                .where("iduser = ?", userId)
                .find(PunchInfo.class);
    }
}
