package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.Impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service进行查询
        List<Brand> brands = brandService.selectAll();

        // 将查询到的数据封装成json数据
        String jsonString = JSON.toJSONString(brands);

        // 将数据中的中文进行编码处理
        response.setContentType("text/json;charset=utf-8");
        // 写回数据
        response.getWriter().write(jsonString);
    }

    /**
     * 添加品牌
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 删除单个品牌
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        System.out.println("deletebrand01");

        // 转换为对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //System.out.println(brand);
        System.out.println("转换对象");

        // 调用方法进行删除
        brandService.deleteBrand(brand.getId());
        // 删除成功后写回success
        response.getWriter().write("success");

    }

    /**
     * 更新数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端数据
        BufferedReader br = request.getReader();
        // 读取数据
        String params = br.readLine();
        // 转换成对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 调用方法传入对象进行更新
        brandService.updateBrand(brand);
        // 更新成功 写回success
        response.getWriter().write("success");
    }

    /**
     * 根据id进行查找
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收前端传回的数据
        BufferedReader br = request.getReader();
        // 读取数据
        String params = br.readLine();
        // 解析为int类型数据
        int id = JSON.parseObject(params, Integer.class);

        // 调用方法查询
        Brand brand = brandService.selectById(id);
        // 转换为json数据
        String jsonString = JSON.toJSONString(brand);
        // 将数据中的中文进行编码处理
        response.setContentType("text/json;charset=utf-8");
        // 写回数据
        response.getWriter().write(jsonString);

    }

    /**
     * 批量删除
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受前端数据
        BufferedReader br = request.getReader();
        // 读取数据
        String params = br.readLine();
        // 转换成数组对象
        int[] ids = JSON.parseObject(params, int[].class);
        // 调用服务层方法进行删除
        brandService.deleteByIds(ids);
        //删除成功 写回数据
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受数据 （注意:前端以url发送携带数据到后端） 变量前面加上下划线表示临时的数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        // 转换成int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 调用service进行数据库查询 获取对象
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        // 转换成json数据
        String jsonString = JSON.toJSONString(pageBean);

        // 设置字符格式
        response.setContentType("text/json;charset=utf-8");

        // 写回数据
        response.getWriter().write(jsonString);

    }

    /**
     * 根据条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受数据 （注意:前端以url发送携带数据到后端） 变量前面加上下划线表示临时的数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //System.out.println(_currentPage+"----");

        // 转换成int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //System.out.println("转换成int之后");

        // 接受条件数据
        BufferedReader br = request.getReader();  // 既有url前端参数的传递 也有data数据传递
        //System.out.println("reader读取");

        // 读取数据
        String params = br.readLine();
        //System.out.println("reader转换成字符串"+params);

        // 封装成brand
        Brand brand = JSON.parseObject(params, Brand.class);
        //System.out.println("params转换成brand对象"+brand);
        //System.out.println("brand");


        // 调用service进行数据库查询 获取对象
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        //System.out.println("转换成json数据之前一步");
        // 转换成json数据
        String jsonString = JSON.toJSONString(pageBean);
        //System.out.println(jsonString);
        // 设置字符格式
        response.setContentType("text/json;charset=utf-8");
        // 写回数据
       // System.out.print("json!!!!!!!!!!:");
        //System.out.println(jsonString);
        response.getWriter().write(jsonString);


    }
}
