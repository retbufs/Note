package com.chens.controller;

import com.chens.entry.NoteResult;
import com.chens.service.base.BaseBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
public class BookController {
    @Resource
    private BaseBookService service;
    @RequestMapping("/book/loadbook.do")
    @ResponseBody
    public NoteResult  loadBooks(String userId, HttpServletRequest request){
        NoteResult book =  service.loadBook(userId);
        return  book;
    }
    @RequestMapping("/book/addbook.do")
    @ResponseBody
    public NoteResult onaddBooks(String userid,String bookname){
        NoteResult nr = service.addBooks(userid,bookname);
        System.out.println(nr);
        return  nr;
    }
}
