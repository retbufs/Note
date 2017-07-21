package com.chens.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取请求路径
 */
public class BasePath {
    public static void basepath(HttpServletRequest request){
        String url = request.getRequestURI();
        System.out.println(url);
        String path = request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+url+request.getContextPath();
        System.out.println(path);
        request.getSession().setAttribute("basePath",path);
    }
}
