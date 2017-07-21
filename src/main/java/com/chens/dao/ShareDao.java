package com.chens.dao;

import com.chens.dao.annotation.MyBatisAnnotation;
import com.chens.entry.Share;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/19.
 */
@Component
@MyBatisAnnotation
public interface ShareDao {
    public int shareNote(Share share);
    public Share findByShareId(String shareId);
    public Share findByNoteId(String noteid);
    public List<Share> findLike(Map map);
}
