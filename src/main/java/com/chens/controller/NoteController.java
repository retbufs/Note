package com.chens.controller;

import com.chens.entry.NoteResult;
import com.chens.entry.Share;
import com.chens.service.NoteService;
import com.chens.service.base.BaseNoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/15.
 */
@Controller
@RequestMapping("/note")
public class NoteController {
    public BaseNoteService getService() {
        return service;
    }
   @Resource
    public void setService(BaseNoteService service) {
        this.service = service;
    }

    private BaseNoteService service;

    @RequestMapping("/onloadnote.do")
    @ResponseBody
    public NoteResult onloadNote(String bookId){
       NoteResult nr =  service.loadNote(bookId);
       return nr;
    }

    @RequestMapping("/onloadnots.do")
    @ResponseBody
    public NoteResult  loadBookNote(String noteid){
        NoteResult nr =  service.loadNoteBook(noteid);
        return nr;
    }
    @RequestMapping("/save_note.do")
    @ResponseBody
    public NoteResult save_Note(String  noteid,String title,String body){
        NoteResult nr = null;
            if(noteid == null){
                nr = new NoteResult();
                nr.setMsg("请先创建笔记后再保存");
                nr.setStatus(1);
                return nr;
        }

        if(noteid != null && !noteid.equals(" ")){
           nr = service.updateNote(noteid,title,body);
        }

//        if(body == null){
//            nr = new NoteResult();
//            nr.setMsg("标题不能为空");
//            nr.setStatus(2);
//        }

        System.out.println(title);
        System.out.println(body);
        System.out.println(noteid);
        System.out.println(nr);
        return nr;
    }
    @RequestMapping("/addnote.do")
    @ResponseBody
    public NoteResult addNote(String bookid,String notename){
        NoteResult nr = service.addNote(bookid,notename);
        return nr;
    }
    //删除
    @RequestMapping("/delenete.do")
    @ResponseBody
    public NoteResult deleteNote(String noteid){
        NoteResult nr = service.deleteNote(noteid);
        return nr;
    }
    //转移
    @RequestMapping("/move.do")
    @ResponseBody
    public NoteResult moveNote(String bookid,String noteid){
        NoteResult nr = service.moveNotes(bookid,noteid);
        return nr;
    }
    //分享笔记
    @RequestMapping("/share.do")
    @ResponseBody
    public NoteResult shareNote(String noteid){
        NoteResult nr =service.shareNote(noteid);
        return nr;
    }
    //查看分享的笔记
    @RequestMapping("/shareurl.do")
    @ResponseBody
    public NoteResult shareNotes(String share, HttpServletRequest request){
        NoteResult nr = service.shareLoad(share);
        request.getSession().setAttribute("share",nr);
        return nr;
    }
    //分享页面
    @RequestMapping("/duang.do")
    public String  onloadShare(String share,HttpServletRequest request){
       request.setAttribute("note",share);
     return "share";
    }
    //
    //搜索
    @RequestMapping("/search.do")
    @ResponseBody
    public NoteResult search(String share, @RequestParam("page")Integer page){
        NoteResult nr =service.searchNote(share,page);
        return nr;
    }
    //回收站
    @RequestMapping("/recycle.do")
    @ResponseBody
    public NoteResult  recycle(){
        NoteResult nr = service.recycleNote();
        return nr;
    }
    //加载回收站内容
    @RequestMapping("/rcecontent.do")
    @ResponseBody
    public  NoteResult rcecontent(String noteid){
        NoteResult nr = service.loadNoteBook(noteid);
        return nr;
    }
    //回收站删除
    @RequestMapping("/rcedele.do")
    @ResponseBody
    public NoteResult rcedele(String noteid){
        System.out.println("rcedel："+noteid);
        NoteResult nr = service.deleteNo(noteid);
        return nr;
    }
    //恢复笔记
    @RequestMapping("/recover.do")
    @ResponseBody
    public NoteResult recycleNote(String noteid,String bookid){
        NoteResult nr = service.recycleNo(noteid, bookid);
        return nr;
    }
}