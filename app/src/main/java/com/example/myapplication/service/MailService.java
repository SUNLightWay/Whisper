package com.example.myapplication.service;

import com.example.myapplication.module.MailboxInfo;
import com.example.myapplication.module.UserInfo;

import java.util.Date;
import java.util.List;

public interface MailService {

    /**
     * 查询信箱列表
     * @return
     */
    public List<MailboxInfo> findMailBoxList();

    /**
     * 获取指定邮件详情
     * @param Id
     * @return
     */
    public MailboxInfo getMailDetailById(String Id);

    /**
     * 发送邮件
     * @param idMail
     * @param from
     * @param to
     * @param isPublic
     * @param isDelay
     * @param sendTime
     * @param recelveTime
     * @param title
     * @param content
     * @return
     */
    public boolean sendMail(Integer idMail, String from, String to, Integer isPublic, Integer isDelay , Date sendTime, Date recelveTime, String title, String content);


    /**
     * 根据用户ID查找邮件列表
     * @param userId
     * @return
     */
    public List<MailboxInfo> findMailListByUserId(String userId);
}
