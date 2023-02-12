package com.itheima.web.oldServlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.Impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet(urlPatterns = "/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    // 实现service接口
    private BrandService brandService= new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service进行查询
        List<Brand> brands = brandService.selectAll();

        // 将查询到的数据封装成json数据
        String jsonString = JSON.toJSONString(brands);

        // 将数据中的中文进行编码处理
        response.setContentType("text/json;charset=utf-8");
        // 写回数据
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
