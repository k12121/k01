package com.itheima.web.oldServlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.Impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet(urlPatterns = "/addBrandServlet")
public class AddBrandServlet extends HttpServlet {
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收品牌数据
        BufferedReader br = request.getReader();

        // 读取一行
        String params = br.readLine();// json字符串

        // 将json转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 调用service添加品牌数据
        brandService.addBrand(brand);

        // 响应成功的标识
        response.getWriter().write("success");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
