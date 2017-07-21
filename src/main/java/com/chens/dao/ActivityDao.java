package com.chens.dao;

import com.chens.dao.annotation.MyBatisAnnotation;
import com.chens.entry.Activity;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@MyBatisAnnotation
public interface ActivityDao{
    public List<Activity> findByActivity();


}
