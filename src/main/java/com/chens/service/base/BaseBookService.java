package com.chens.service.base;

import com.chens.entry.NoteResult;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface BaseBookService {
    /**
     * 加载笔记本
     * @param name
     * @return
     */
    public NoteResult loadBook(String name);

    /**
     * 新增笔记本
     * @param user_id
     * @param bookName
     * @return
     */
    public NoteResult addBooks(String user_id,String bookName);

}
