package com.chens.service;

import com.chens.BaseMd5;
import com.chens.dao.NoteDao;
import com.chens.dao.ShareDao;
import com.chens.entry.Note;
import com.chens.entry.NoteResult;
import com.chens.entry.Share;
import com.chens.service.base.BaseNoteService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/15.
 */
@Service
public class NoteService implements BaseNoteService {
    private NoteDao noteDao;
    @Resource
    private ShareDao shareDao;

    public NoteDao getNoteDao() {
        return noteDao;
    }
      @Resource
      public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
    //获取NOte
    @Override
    public NoteResult loadNote(String noteId){
        NoteResult nr = new NoteResult();
        System.out.println(noteId);
        if(noteId == null){
         nr.setMsg("null");
         nr.setStatus(0);
         return nr;
        }
      if(noteId != null){
       List<Note> notes = noteDao.findBookById(noteId);
       nr.setStatus(1);
       nr.setMsg("ok");
       nr.setObj(notes);
       return nr;
      }
        return null;
    }
    /**
     * 获取笔记内容
     */
    public NoteResult loadNoteBook(String noteid){
        NoteResult nr = new NoteResult();
        if(noteid == null ){
          nr.setStatus(0);
          nr.setMsg("没有笔记哟！赶紧添加吧。");
          return nr;
        }
       Note note = noteDao.findByNoId(noteid);
       nr.setMsg("ok");
       nr.setStatus(1);
       nr.setObj(note);
      return nr;

    }
    /**
     * 更新
     */
    public NoteResult updateNote(String noteid ,String title,String body){
        NoteResult nr = new NoteResult();
        System.out.println(noteid+":"+title+":"+body);
        if(title.equals("")){
            nr.setMsg("标题不能为空");
            nr.setStatus(1);
            return nr;
        }

         Note  note = new Note();
         note.setCn_note_id(noteid);
         note.setCn_note_title(title);
         note.setCn_note_body(body);
         long time =System.currentTimeMillis();
         note.setCn_note_create_time(time);
        int counm = noteDao.update(note);
        if(counm == 1){
            System.out.println(counm);
            nr.setStatus(0);
            nr.setMsg("ok");
            nr.setObj(note);
            return nr;
        }else{
            nr.setStatus(1);
            nr.setMsg("保存失败！请重新保存！");
            nr.setObj(note);
            return nr;
        }
    }
   @Override
    public NoteResult addNote(String bookid,String noteName){
        NoteResult  nr = new NoteResult();
        if(bookid.equals("")&&noteName.equals("")){
               nr.setMsg("创建失败！");
               nr.setStatus(0);
               return nr;
        }
          Note note = new Note();
          note.setCn_note_id(BaseMd5.createId());
          note.setCn_note_title(noteName);
          note.setCn_user_id(bookid);
          note.setCn_note_status_id("1");
          note.setCn_note_type_id("1");
          note.setCn_notebook_id(bookid);
          note.setCn_note_body(" ");
          long time = System.currentTimeMillis();
          note.setCn_note_create_time(time);
          note.setCn_note_last_modify_time(time);
          int temp = noteDao.addNote(note);
          nr.setStatus(temp);
          nr.setMsg("ok");
          nr.setObj(note.getCn_note_id());
          return nr;

    }

    @Override
    public NoteResult deleteNote(String noteid) {
        NoteResult nr = new NoteResult();
        boolean flag = true;
        if(noteid.equals("")){
            flag = false;
            nr.setMsg("请选择笔记后在操作");
            nr.setStatus(1);
            return nr;
        }
        if(flag){
            int count= noteDao.deleteNote(noteid);
            System.out.println(count);
            if(count >0){
                nr.setStatus(count);
                nr.setMsg("ok");
                nr.setObj(noteid);
                return nr;
            }
            nr.setStatus(1);
            nr.setMsg("请重新提交");
            nr.setObj(noteid);
            return nr;

        }
        return null;
    }
    //移动笔记
    @Override
    public NoteResult moveNotes(String bookid,String noteid){
        NoteResult nr = new NoteResult();
        if(bookid.equals("")||noteid.equals("")){
            nr.setStatus(0);
            nr.setMsg("请选择要移动目标");
            return nr;
        }
        Note  note = new Note();
        note.setCn_notebook_id(bookid);
        note.setCn_note_id(noteid);
         int x =noteDao.moveNote(note);
         nr.setMsg("ok");
         nr.setStatus(1);
         nr.setObj(x);
         return nr;
    }
    @Override
    public NoteResult shareNote(String noteid){
        NoteResult nr = new NoteResult();
        Note note = noteDao.findByNoId(noteid);
        Share share1 = shareDao.findByNoteId(noteid);
        if(share1 == null){
            Share share = new Share();
            share.setCn_note_id(note.getCn_note_id());
            share.setCn_share_body(note.getCn_note_body());
            share.setCn_share_title(note.getCn_note_title());
            String shareId = BaseMd5.createId();
            share.setCn_share_id(shareId);
            shareDao.shareNote(share);
            nr.setObj(share.getCn_share_id());
            nr.setStatus(0);
            nr.setMsg("ok");
            return nr;
        }
        nr.setObj(share1.getCn_share_id());
        nr.setStatus(0);
        nr.setMsg("ok");
        return nr;
    }

    @Override
    public NoteResult shareLoad(String noteid) {
        NoteResult nr = new NoteResult();
       Share share = shareDao.findByShareId(noteid);
        nr.setObj(share);
        nr.setMsg("ok");
        nr.setStatus(0);
        return nr;
    }
    //查询
    @Override
    public NoteResult searchNote(String keyword,int page){
      NoteResult nr = new NoteResult();
      Map<String ,Object> map = new HashMap<>();
      //处理搜条件
        String title = "%";
        if(keyword != null&&!"".equals(keyword)){
         title = "%"+keyword+"%";
        }
        if(page <1){
            page = 1;
        }
        int begint = (page -1)*5;
        map.put("keyword",title);
        map.put("begint",begint);
       List<Share> lis = shareDao.findLike(map);
      System.out.println(lis);
        nr.setStatus(0);
        nr.setObj(lis);
        nr.setMsg("查询完成");
        return nr;
    }
    //回收站
    @Override
    public  NoteResult   recycleNote(){
        NoteResult nr = new NoteResult();
        List<Note> list = noteDao.recycle();
        if(list == null){
            nr.setStatus(0);
            nr.setMsg("当前回收站是空的");
            return nr;
        }
        nr.setMsg("ok");
        nr.setStatus(1);
        nr.setObj(list);
       return nr;
    }
    //删除笔记
    public NoteResult deleteNo(String id){
        int count = noteDao.delete(id);
        NoteResult nr = new NoteResult();
        if(count > 1){
          nr.setStatus(count);
          nr.setMsg("删除成功");
          return nr;
        }
        nr.setMsg("删除异常");
        nr.setStatus(count);
        return nr;
    }
    //恢复笔记
    public NoteResult recycleNo(String noteid,String bookid){
        NoteResult nr = new NoteResult();
        Note note = new Note();
        note.setCn_note_id(noteid);
        note.setCn_notebook_id(bookid);
        int count = noteDao.recycleNote(note);
        if(count >0){
            nr.setMsg("ok");
            nr.setStatus(count);
        }
        nr.setStatus(0);
        nr.setMsg("异常");
        return nr;
    }

}
