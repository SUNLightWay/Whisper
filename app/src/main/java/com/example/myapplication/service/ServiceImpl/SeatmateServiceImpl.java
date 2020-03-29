package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.dao.DaoImpl.SeatmateDaoImpl;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.SeatmateService;
import com.example.myapplication.util.ConstUtil;

import java.util.Date;
import java.util.List;

public class SeatmateServiceImpl implements SeatmateService {

    private SeatmateDaoImpl seatmateDao = new SeatmateDaoImpl();
    private final String TAG = "SeatmateServiceImpl";

    @Override
    public Boolean sendRequest(SeatmateInfo seatmateInfo) {
        seatmateInfo.setStatus(ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE);
        Boolean isSend = seatmateDao.addSeatmate(seatmateInfo);
        Log.d(TAG, "sendRequest: " + isSend);
        return isSend;
    }

    @Override
    public Boolean replyRequest(String seatmateId, int reply) {
        if (reply == ConstUtil.SeatmateReplyType.TYPE_APPROVE){
            return seatmateDao.updateSeatemate(seatmateId, ConstUtil.SeatmateStatus.STATUS_PROCESSING, new Date());

        } else{
            seatmateDao.updateSeatmateStatusOnly(seatmateId, ConstUtil.SeatmateStatus.STATUS_NONESENSE);
            return false;
        }
    }

    @Override
    public List<SeatmateInfo> findSeatmateNeedToResponse(String userId) {
        return seatmateDao.findSeatmateByUserIdAndStatusLimitPerson2(userId, ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE);
    }

    @Override
    public List<SeatmateInfo> findSeatmateWaitingForAnotherResponse(String userId) {
        return seatmateDao.findSeatmateByUserIdAndStatusLimitPerson1(userId, ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE);

    }

    @Override
    public List<SeatmateInfo> findSeatmateFailedorSucceeded(String userId) {
        List<SeatmateInfo> seatmates = seatmateDao.findSeatmateByUserIdAndStatus(userId, ConstUtil.SeatmateStatus.STATUS_FAIL);
        seatmates.addAll(seatmateDao.findSeatmateByUserIdAndStatus(userId, ConstUtil.SeatmateStatus.STATUS_SUCCEED));
        return seatmates;
    }

    @Override
    public List<SeatmateInfo> findSeatmateProcessing(String userId) {
        return seatmateDao.findSeatmateByUserIdAndStatus(userId, ConstUtil.SeatmateStatus.STATUS_PROCESSING);
    }


}
