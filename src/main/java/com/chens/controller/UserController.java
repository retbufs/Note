package com.chens.controller;

import com.chens.entry.NoteResult;
import com.chens.service.base.BaseUserService;
import com.chens.util.BasePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name ="userService")
    private BaseUserService service;
    @RequestMapping("/login.do")
    public String index(){
        return "log_in";
    }
    /**
     * 登入事件
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/checklog.do")
    @ResponseBody
    public Map excute(String name, @RequestParam("password")
            String password, HttpServletRequest request, HttpServletResponse response){
            BasePath.basepath(request);
            NoteResult msg = new NoteResult();
            Map map = service.login(name,password);
           System.out.println("登入操作");
           System.out.println("name:"+name+";password:"+password);
           System.out.println(map);
           return map;
    }
    @RequestMapping("/checkreg.do")
    @ResponseBody
    public Map<String ,String > check_reg(String name,
                                           @RequestParam("nick")String nick,
                                           @RequestParam("password") String password){
        Map map = service.register(name,password,nick);
        System.out.println(map.get("data"));
        return map;
    }
    //笔记url
    @RequestMapping("/edit.do")
    public String  esots(){
        return "edit";
    }
    //修改Miami
    @RequestMapping("/chang.do")
    public String changps(){
        return "Change_password";
    }
    /**
     * 修改密码
     * */
    @RequestMapping("/changpass.do")
    @ResponseBody
    //data:{"uid":uuid,"newPass":newPass,"lastPass":lastPass},
    public  NoteResult changPass(String lastPass,String newPass ,String uuid){
        NoteResult nr = new NoteResult();
        return nr;
    }
}
