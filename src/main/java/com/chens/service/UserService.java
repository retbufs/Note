package com.chens.service;
import com.chens.BaseMd5;
import com.chens.dao.UserDao;
import com.chens.entry.NoteResult;
import com.chens.entry.User;
import com.chens.service.base.BaseUserService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserService implements BaseUserService{
    public UserDao getUserDao() {
        return userDao;
    }
    @Resource(name="userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    private  UserDao userDao;
    /**
     * 登入
     * @param name
     * @param password
     * @return User  to token
     */
    @Override
    public Map<String, String> login(String name, String password) {
        Map<String,String > map = new HashMap<String,String>();
        System.out.println(name+"::"+password);
        if(name.equals("")){
            map.put("msg","用户名能为空");
            map.put("status","2");
            return map;
        }
        User  user = userDao.findByName(name);
        System.out.println("[USER]");
        if(user == null ){
            map.put("msg","用户名不存在");
            map.put("status","2");
            return map;
        }
        String mdg = BaseMd5.manage(password);
        if(!user.getCn_user_password().equals(mdg)){
            map.put("msg","用密码不正确");
            map.put("status","3");
            return map;
        }
        if(user.getCn_user_name().equals(name)&&
                user.getCn_user_password().equals(mdg)){
            user.setCn_user_password("");
            JSONObject json = new JSONObject(user);
            map.put("data",json.toString());
            map.put("msg","ok");
            map.put("status","1");
            return map;
        }
       return null;
    }
    public Map<String,String> register(String name,String password,String nick) {
        Map<String, String> map = new HashMap<String, String>();
        boolean falg = true;
        try {
            if (name == null) {
                map.put("msg", "用户名不能为空");
                map.put("status", "2");
                falg = false;
            }
            if (name == "") {
                map.put("msg", "用户名不能为空");
                map.put("status", "2");
                falg = false;
            }
            User user2 = userDao.findByName(name);
            System.out.println(user2);
            if (user2 != null) {
                map.put("msg", "用户不可用");
                map.put("status", "2");
                falg = false;
            }
            if (password == null) {
                map.put("msg", "不正确的格式");
                map.put("status", "3");
                falg = false;
            }
            if (password == "") {
                map.put("msg", "密码不能为空");
                map.put("status", "3");
                falg = false;
            }
            String mdg = BaseMd5.manage(password);
            System.out.println(name + "\t" + password + "\t" + nick);
            if (falg) {
                User user = new User();
                user.setCn_user_id(BaseMd5.createId());
                user.setCn_user_name(name);
                user.setCn_user_nick(nick);
                user.setCn_user_password(mdg);
                userDao.addUser(user);
                map.put("status", "1");
                map.put("msg", "注册成功！");
            }
            return map;
        } catch (Exception e) {
            map.put("msg", "系统繁忙，请稍候重试");
            map.put("status", "0");
            throw new RuntimeException("请稍候重试,系统繁忙");
        }

    }

    /**
     * 修改密码
     * @param lasrPass
     * @param newPass
     * @param uuid
     * @return
     */
    public NoteResult changPass(String lasrPass,String newPass,String uuid){
        User  user = userDao.findById(uuid);
        NoteResult nr = new NoteResult();
        if(user.getCn_user_password().equals(lasrPass)){
            user.setCn_user_password(newPass);
            userDao.changPass(user);
            nr.setMsg("修改完成");
            nr.setStatus(0);
            return nr;
        }
        nr.setMsg("error");
        nr.setStatus(1);
        return nr;
    }
}
