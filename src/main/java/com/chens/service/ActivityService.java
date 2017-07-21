package com.chens.service;

import com.chens.dao.ActivityDao;
import com.chens.entry.Activity;
import com.chens.entry.NoteResult;
import com.chens.service.base.BaseActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityService implements BaseActivityService {
    public ActivityDao getActivityDao() {
        return activityDao;
    }
    @Resource
    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
    private ActivityDao activityDao;

    public NoteResult onloadActivity(){
        NoteResult nr = new NoteResult();
        List<Activity> at = activityDao.findByActivity();
        nr.setMsg("ok");
        nr.setStatus(0);
        nr.setObj(at);
        return nr;
    }

    @Override
    public NoteResult save_load(String title, String body) {

        return null;
    }

//    @Override
//    public NoteResult save_load(String title, String body) {
//        NoteResult nr = activityDao
//        return null;
//    }
}
