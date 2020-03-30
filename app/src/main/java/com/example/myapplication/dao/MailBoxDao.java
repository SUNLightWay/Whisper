package com.example.myapplication.dao;

import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.vo.Mail;

import java.util.List;

public interface MailBoxDao {

    /**
     * 查询邮件列表
     * @return
     */
    public List<MailboxInfo> findAllMailBoxInfo();

    /**
     * 获取指定标号邮件
     * @param Id
     * @return
     */
    public List<MailboxInfo> findMailById(String Id);

    /**
     * 发送邮件
     * @param mail
     * @return
     */
    public boolean sendMail(MailboxInfo mail);
}
