package com.chens.dao;

import com.chens.dao.annotation.MyBatisAnnotation;
import com.chens.entry.Note;
import com.chens.entry.Share;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Component
@MyBatisAnnotation
public interface NoteDao {
    //根据Bookid查询笔记
  public List<Note> findBookById(String book_id);
   public Note  findByNoId(String noteid);
  // public void addNote(Note note);
   public int update(Note note);
   public int addNote(Note note);
   //删除note 变更至回收站
    public int  deleteNote(String noteid);
    //恢复
    public int recycleNote(Note note);
    //移动
    public  int  moveNote(Note note);
    //回收
    public List<Note> recycle();
    //测底删除
    public int delete(String id);

}
