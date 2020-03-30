package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.MailBoxDao;
import com.example.myapplication.module.MailboxInfo;

import org.litepal.LitePal;

import java.util.List;

public class MailBoxDaoImpl implements MailBoxDao {
    @Override
    public List<MailboxInfo> findAllMailBoxInfo() {
        List<MailboxInfo> mailInfos = LitePal.findAll(MailboxInfo.class);
        return mailInfos;
    }

    @Override
    public List<MailboxInfo> findMailById(String Id) {
        List<MailboxInfo> mailboxInfos = LitePal.select()
                .where("id = ?", Id)
                .limit(1)
                .find(MailboxInfo.class);
        return mailboxInfos;
    }

    @Override
    public boolean sendMail(MailboxInfo mail) {
        return mail.save();
    }


}
