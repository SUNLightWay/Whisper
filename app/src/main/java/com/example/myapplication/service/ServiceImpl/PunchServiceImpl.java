package com.example.myapplication.service.ServiceImpl;

import com.example.myapplication.dao.DaoImpl.PunchDaoImpl;
import com.example.myapplication.module.PunchInfo;
import com.example.myapplication.service.PunchService;

import java.util.List;

public class PunchServiceImpl implements PunchService {
    private PunchDaoImpl punchDao = new PunchDaoImpl();

    @Override
    public Boolean addPunch(PunchInfo punchInfo) {
        return punchDao.addPunch(punchInfo);
    }

    @Override
    public List<PunchInfo> findPunchListMyUserId(String userId) {
        return punchDao.findPunchListMyUserId(userId);
    }
}
