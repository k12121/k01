package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 新增品牌
     */

    void addBrand(Brand brand);

    /**
     * 删除品牌
     */
    void deleteBrand(int id);

    /**
     * 更新品牌
     */

    void updateBrand(Brand brand);

    /**
     * 根据id查找
     */
    Brand selectById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(int []ids);

    /**
     *
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
