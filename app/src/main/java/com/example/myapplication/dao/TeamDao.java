package com.example.myapplication.dao;

import com.example.myapplication.module.TeamInfo;

import java.util.List;

public interface TeamDao {

    /**
     * 增加队伍
     * @param team
     * @return
     */
    public Boolean addTeam(TeamInfo team);


    /**
     * 更新队伍信息
     * @param team
     * @return
     */
    public Boolean updateTeamInfo(TeamInfo team);


    /**
     * 根据id获取队伍信息
     * @param teamId
     * @return
     */
    public List<TeamInfo> findTeamById(String teamId);

    /**
     * 获取队伍列表
     * @return
     */
    public List<TeamInfo> findTeamList();

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
     * 根据关键词搜索
     * @param keyword
     * @return
     */
    public List<TeamInfo> findTeamListByKeyword(String keyword);
}
