package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.TeamDao;
import com.example.myapplication.module.TeamInfo;
import com.example.myapplication.module.UserInfo;

import org.litepal.LitePal;

import java.util.List;

public class TeamDaoImpl implements TeamDao {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public Boolean addTeam(TeamInfo team) {
        return team.save();
    }

    @Override
    public Boolean updateTeamInfo(TeamInfo team) {
        List<TeamInfo> teamInfos = LitePal.select()
                .where("idteam = ?", team.getIdTeam())
                .limit(1)
                .find(TeamInfo.class);
        if(teamInfos.size() == 0)
            return false;
        TeamInfo teamInfo = teamInfos.get(0);
        if (team.getCaptain() != null){
            teamInfo.setCaptain(team.getCaptain());
        }
        if (team.getNumber() != null){
            teamInfo.setNumber(team.getNumber());
        }
        if (team.getTeamInfo() != null){
            teamInfo.setTeamInfo(team.getTeamInfo());
        }
        if (team.getTeamTitle() != null){
            teamInfo.setTeamTitle(team.getTeamTitle());
        }
        return teamInfo.save();
    }

    @Override
    public List<TeamInfo> findTeamById(String teamId) {
        List<TeamInfo> teamInfos = LitePal.select()
                .where("idteam = ?", teamId)
                .limit(1)
                .find(TeamInfo.class);
        return teamInfos;
    }

    @Override
    public List<TeamInfo> findTeamList() {
        List<TeamInfo> teamInfos = LitePal.findAll(TeamInfo.class);
        return teamInfos;
    }

    @Override
    public Boolean joinTeam(String userId, String teamId) {
        UserInfo user = userDao.findUserInfoByID(userId).get(0);
        user.setIdTeam(teamId);
        TeamInfo team = LitePal.select()
                .where("idteam = ?", teamId)
                .limit(1)
                .find(TeamInfo.class).get(0);
        team.setNumber(team.getNumber() + 1);

        if (user.save() && team.save())
            return true;
        else
            return false;
    }

    @Override
    public Boolean exitTeam(String userId, String teamId) {
        UserInfo user = userDao.findUserInfoByID(userId).get(0);
        user.setIdTeam(null);
        TeamInfo team = LitePal.select()
                .where("idteam = ?", teamId)
                .limit(1)
                .find(TeamInfo.class).get(0);
        team.setNumber(team.getNumber() - 1);

        if (user.save() && team.save())
            return true;
        else
            return false;
    }

    @Override
    public List<TeamInfo> findTeamListByKeyword(String keyword) {
        return LitePal.select()
                .where("teamtitle like ?", "%" + keyword + "%")
                .find(TeamInfo.class);
    }
}
