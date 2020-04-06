package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.SeatmateDao;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class SeatmateDaoImpl implements SeatmateDao {

    private final String TAG = "SeatmateDaoImpl";

    @Override
    public List<SeatmateInfo> findSeatmateBySeatmateId(String seatmateId) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("idseatmate = ?", seatmateId)
                .limit(1)
                .find(SeatmateInfo.class);
        return seatmateInfos;
    }

    @Override
    public List<SeatmateInfo> findSeatmateByUserId(String userId) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("person1 = ? or person2 = ?", userId, userId)
                .limit(10)
                .find(SeatmateInfo.class);
        return seatmateInfos;
    }

    @Override
    public List<SeatmateInfo> findSeatmateByUserIdAndStatus(String userId, int status) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("(person1 = ? or person2 = ?) and status = ?", userId, userId, Integer.toString(status))
                .limit(10)
                .find(SeatmateInfo.class);
        return seatmateInfos;
    }

    @Override
    public List<SeatmateInfo> findSeatmateByUserIdAndStatusLimitPerson1(String userId, int status) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("person1 = ? and status = ?", userId, Integer.toString(status))
                .limit(10)
                .find(SeatmateInfo.class);
        return seatmateInfos;
    }

    @Override
    public List<SeatmateInfo> findSeatmateByUserIdAndStatusLimitPerson2(String userId, int status) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("person2 = ? and status = ?", userId, Integer.toString(status))
                .limit(10)
                .find(SeatmateInfo.class);
        return seatmateInfos;
    }

    @Override
    public Boolean addSeatmate(SeatmateInfo seatmate) {
        return seatmate.save();
    }

    @Override
    public Boolean updateSeatemate(String seatmateId, int status, Date startTime) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("idseatmate = ?", seatmateId)
                .limit(10)
                .find(SeatmateInfo.class);
        if (seatmateInfos.size() == 0){
            return false;
        }
        SeatmateInfo seatmate = seatmateInfos.get(0);
        seatmate.setStatus(status);
        seatmate.setStartTime(startTime);
        return seatmate.save();
    }

    @Override
    public Boolean updateSeatmateStatusOnly(String seatmateId, int status) {
        List<SeatmateInfo> seatmateInfos = LitePal.select()
                .where("idseatmate = ?", seatmateId)
                .limit(10)
                .find(SeatmateInfo.class);
        if (seatmateInfos.size() == 0){
            return false;
        }
        SeatmateInfo seatmate = seatmateInfos.get(0);
        seatmate.setStatus(status);
        return seatmate.save();
    }

}
