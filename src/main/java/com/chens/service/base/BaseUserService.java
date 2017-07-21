package com.chens.service.base;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface BaseUserService {
    /**
     * 登入
     * @param name
     * @param password
     * @return User  to token
     */
    public Map<String, String> login(String name, String password);

    /**
     * 注册
     * @param name
     * @param password
     * @param nick
     * @return
     */
    public Map<String,String> register(String name,String password,String nick);

}
