package com.chens.service.base;

import com.chens.entry.NoteResult;

/**
 * Created by Administrator on 2017/7/15.
 */
public interface BaseNoteService {
    /**加载笔记列表*/
    public NoteResult loadNote(String noteId);
    /**根据noteid加载笔记本*/
    public NoteResult loadNoteBook(String noteid);
    /**保存笔记*/
    public NoteResult updateNote(String noteid ,String title,String body);
    /**添加笔记*/
    public NoteResult addNote(String bookid,String noteName);
    /**删除笔记*/
    public NoteResult deleteNote(String noteid);
    /**移动笔记*/
    public NoteResult moveNotes(String bookid,String noteid);
    /**分享笔记*/
    public NoteResult shareNote(String noteid);
    /**获取已分享笔记内容*/
    public NoteResult  shareLoad(String noteid);
    /**搜索*/
    public NoteResult searchNote(String keyword,int page);
    /**回收站*/
    public  NoteResult   recycleNote();

    /**
     * 删除回收站笔记  测底删除
     * @param id
     * @return
     */
    public NoteResult deleteNo(String id);
    /**
     * 恢复笔记
     */
    public NoteResult recycleNo(String noteid,String bookid);
}
