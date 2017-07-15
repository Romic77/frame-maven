package com.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.frame.entity.Admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    public static List<String> pattenURL = new ArrayList<String>();

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        pattenURL.add("login.jsp");//登录jsp
        pattenURL.add("login.do");//登录方法
        pattenURL.add("sendCode.do");//发送验证码方法
        pattenURL.add("updateOPpassword.do");//修改密码方法

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        // 登陆url
        String loginUrl = httpRequest.getContextPath() + "/login.jsp";
        String url = httpRequest.getContextPath().toString();

        /*
         * 注：在pattenURL中的全部不拦截
         * url.indexOf(urlStr) > -1  表示urlStr在url中出现过，出现就不拦截
         * */
        for (String urlStr : pattenURL) {
            if (url.indexOf(urlStr) > -1) {
                return true;
            }
        }


        /*
         * 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向
         * session.getAttribute("")是获取到登录人的session信息
         * */
        if (session.getAttribute("admin") == null) {
            // 判断是否为ajax请求
            if (httpRequest.getHeader("x-requested-with") != null
                    && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                httpResponse.addHeader("sessionstatus", "timeOut"); //返回超时标识
                httpResponse.addHeader("loginPath", loginUrl);// 返回url
                return true;// 不可少，否则请求会出错
            } else {
                //alert('会话过期,请重新登录');
                String str = "<script language='javascript'>"
                        + "window.top.location.href='"
                        + loginUrl
                        + "';</script>";
                response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(str);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }
        return false;






        /*//获取请求的URL
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");

        if(admin != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
        request.getRequestDispatcher("/login/toLogin").forward(request, response);

        return false;*/
    }

}  