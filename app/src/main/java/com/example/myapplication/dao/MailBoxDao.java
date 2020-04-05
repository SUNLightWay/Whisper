package com.example.myapplication.dao;

import com.example.myapplication.module.MailboxInfo;

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


    /**
     * 根据用户Id查询邮件列表
     * @param userId
     * @return
     */
    public List<MailboxInfo> findMailListByUserId(String userId);


    /**
     * 根据用户Id查询已发送的邮件
     * @param userId
     * @return
     */
    public List<MailboxInfo> findMailSentListByUserId(String userId);
}
