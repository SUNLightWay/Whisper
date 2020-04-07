package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.dao.DaoImpl.MailBoxDaoImpl;
import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.service.MailService;
import com.example.myapplication.util.ConstUtil;

import java.util.Date;
import java.util.List;

public class MailServiceImpl implements MailService {
    private final String TAG = "MailServiceImpl";
    MailBoxDaoImpl mailDao = new MailBoxDaoImpl();

    @Override
    public List<MailboxInfo> findMailBoxList() {
        List<MailboxInfo> mailInfos = mailDao.findAllMailBoxInfo();
//        for(MailboxInfo mailboxInfo : mailInfos)
//            Log.d(TAG, "findmailList: " + mailboxInfo.toString());
        return mailInfos;
    }

    @Override
    public MailboxInfo getMailDetailById(String Id) {
        List<MailboxInfo> mailInfos = mailDao.findMailById(Id);
        if(mailInfos.size() > 0){
            Log.d(TAG, "findmailByID: " + mailInfos.get(0).toString());
            return mailInfos.get(0);
        }
        return null;
    }

    @Override
    public boolean sendMail(Integer idMail, String from, String to, Integer isPublic, Integer isDelay , Date sendTime, Date recelveTime, String title, String content) {
        MailboxInfo mail = new MailboxInfo(idMail,from,to,isPublic,isDelay,sendTime,recelveTime,title,content, ConstUtil.MailSendStatus.UNREACHED);
        if(mailDao.sendMail(mail)){
            Log.d(TAG, "sendMail success!");
            return true;
        }
        return false;
    }

    @Override
    public List<MailboxInfo> findMailListByUserId(String userId) {
        return mailDao.findMailListByUserId(userId);
    }

    @Override
    public List<MailboxInfo> findMailSentListByUserId(String userId) {
        return mailDao.findMailSentListByUserId(userId);
    }


}
