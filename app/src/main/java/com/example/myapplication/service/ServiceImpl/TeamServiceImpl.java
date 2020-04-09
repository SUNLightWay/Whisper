package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.dao.DaoImpl.TeamDaoImpl;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.service.TeamService;
import com.example.myapplication.util.ConstUtil;
import com.example.myapplication.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private final String TAG = "TeamServiceImpl";
    private TeamDaoImpl teamDao = new TeamDaoImpl();

    @Override
    public TeamInfo createTeam(String userId, String teamTitle, String teamDesc, int type) {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = new Date();
        TeamInfo teamInfo;
        if (type == 0) {
            teamInfo = new TeamInfo(Utils.getRandomString(20), userId, 1, ConstUtil.TeamNumberLimit.Type_simple, date, 0, Utils.getRandomString(30), teamTitle, teamDesc);
        }
        else{
            teamInfo = new TeamInfo(Utils.getRandomString(20), userId, 1, ConstUtil.TeamNumberLimit.Type_advance, date, 0, Utils.getRandomString(30), teamTitle, teamDesc);
        }
        if(teamDao.addTeam(teamInfo)){
            Log.d(TAG, "createTeam: true");
            return teamInfo;
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean updateTeamInfo(TeamInfo team) {
        return teamDao.updateTeamInfo(team);
    }

    @Override
    public Boolean joinTeam(String userId, String teamId) {
        return teamDao.joinTeam(userId, teamId);
    }

    @Override
    public Boolean exitTeam(String userId, String teamId) {
        return teamDao.exitTeam(userId, teamId);
    }

    @Override
    public TeamInfo findTeamById(String teamId) {
        List<TeamInfo> teams = teamDao.findTeamById(teamId);
        if (teams.size() == 0)
            return null;
        return teams.get(0);
    }

    @Override
    public List<TeamInfo> findTeamList() {
        return teamDao.findTeamList();
    }

    @Override
    public List<TeamInfo> findTeamListByKeyword(String keyword) {
        return teamDao.findTeamListByKeyword(keyword);
    }


}
