package com.chens.controller;
import com.chens.entry.NoteResult;
import com.chens.service.base.BaseActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    private BaseActivityService service;

    public BaseActivityService getService() {
        return service;
    }
    @Resource
    public void setService(BaseActivityService service) {
        this.service = service;
    }

    @RequestMapping("/onactivity.do")
    @ResponseBody
    public NoteResult onloadActivity(){
        NoteResult nr = service.onloadActivity();
        System.out.println(nr);
        return nr ;
    }
}
