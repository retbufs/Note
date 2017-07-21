package com.chens.service.base;

import com.chens.entry.NoteResult;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface BaseActivityService {
    public NoteResult onloadActivity();
    public NoteResult save_load(String title,String body);

}
