package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    // 重写方法 分配方法

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取方法路径
        String uri = req.getRequestURI();  // 注意:路径中带有参数一依旧可以获取路径,不会出现问题
        //System.out.println(uri);
        // 获取方法出现位置
        int index = uri.lastIndexOf('/');

        // 提取出方法名称
        String methodName = uri.substring(index + 1); // 从index+1出开始分割提取数据
        //System.out.println(methodName);
        // 获取调用该方法class对象  注意:这里的this是这调用我,这就是this对象
        /*
         * brandServlet调用service方法,this就指向brandServlet
         * this:谁调用我,我代表谁
         */
        Class<? extends BaseServlet> cls = this.getClass();
        // 获取方法
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class); // 获取方法对象
            // 执行方法
            try {
                method.invoke(this,req,resp);  // 执行方法
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("test BaseServlet");

    }
}
