package com.example.myapplication.service;

import com.example.myapplication.module.TeamInfo;

import java.util.List;

public interface TeamService {

    /**
     * 创建队伍 0 simple 1 advance
     * @param userId
     * @return
     */
    public TeamInfo createTeam(String userId, String teamTitle, String teamInfo, int type);


    /**
     * 更新队伍信息
     * @param team
     * @return
     */
    public Boolean updateTeamInfo(TeamInfo team);


    /**
     * 加入队伍
     * @param userId
     * @param teamId
     * @return
     */
    public Boolean joinTeam(String userId, String teamId);


    /**
     * 退出队伍
     * @param userId
     * @param teamId
     * @return
     */
    public Boolean exitTeam(String userId, String teamId);


    /**
     * 根据id获取队伍
     * @param teamId
     * @return
     */
    public TeamInfo findTeamById(String teamId);


    /**
     * 获取队伍列表
     * @return
     */
    public List<TeamInfo> findTeamList();

    /**
     * 根据关键词搜索
     * @param keyword
     * @return
     */
    public List<TeamInfo> findTeamListByKeyword(String keyword);

}
