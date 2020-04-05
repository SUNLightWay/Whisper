package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.dao.DaoImpl.SeatmateDaoImpl;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.SystemNoteInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.SeatmateService;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.util.Date;
import java.util.List;

public class SeatmateServiceImpl implements SeatmateService {

    private SeatmateDaoImpl seatmateDao = new SeatmateDaoImpl();
    private SystemNoteServiceImpl systemNoteService = new SystemNoteServiceImpl();

    private final String TAG = "SeatmateServiceImpl";

    @Override
    public Boolean sendRequest(SeatmateInfo seatmateInfo) {
        seatmateInfo.setStatus(ConstUtil.SeatmateStatus.STATUS_WAITING_ANOTHER_RESPONSE);
        Boolean isSend = seatmateDao.addSeatmate(seatmateInfo);
        Log.d(TAG, "sendRequest: " + isSend);

        //向对方发送系统消息
        systemNoteService.addNote(new SystemNoteInfo(Utils.getRandomString(10),
                "同桌邀请", seatmateInfo.getPerson2(), new Date(), "来自" + seatmateInfo.getPerson1() + "同桌的邀请",
                ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_INVITATION, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ));

        return isSend;
    }

    @Override
    public Boolean replyRequest(String seatmateId, int reply) {
        SeatmateInfo seatmateInfo = seatmateDao.findSeatmateBySeatmateId(seatmateId).get(0);
        //接受
        if (reply == ConstUtil.SeatmateReplyType.TYPE_APPROVE){
            systemNoteService.addNote(new SystemNoteInfo(Utils.getRandomString(10),
                    "同桌邀请", seatmateInfo.getPerson1(), new Date(), "对方已同意，快搬过来吧",
                    ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_RECEIVE, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ));

            return seatmateDao.updateSeatemate(seatmateId, ConstUtil.SeatmateStatus.STATUS_PROCESSING, new Date());

        } else{
            //拒绝
            systemNoteService.addNote(new SystemNoteInfo(Utils.getRandomString(10),
                    "同桌邀请", seatmateInfo.getPerson1(), new Date(), "很遗憾，对方已拒绝，试着向其他人发起邀请吧",
                    ConstUtil.SysNoteType.SYS_NOTE_SEATMATE_REJECT, ConstUtil.SysNoteRead.SYS_NOTE_NOT_ON_READ));

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
