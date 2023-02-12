package com.itheima.service.Impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取mapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 查询所有
        List<Brand> brands = brandMapper.selectAll();

        // 释放资源
        sqlSession.close();

        // 返回查询到的值
        return brands;
    }

    @Override
    public void addBrand(Brand brand) {
        // 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取mapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 调用方法添加数据
        brandMapper.addBrand(brand);

        // 增 删 改 需要提交事物
        sqlSession.commit();

        // 释放资源
        sqlSession.close();

    }

    /**
     * 删除品牌
     */
    @Override
    public void deleteBrand(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 调用方法删除
        brandMapper.deleteBrand(id);
        //删除成功 提交事物
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Override
    public void updateBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 调用更新方法
        brandMapper.updateBrand(brand);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Override
    public Brand selectById(int id) {
        // 接受前端传入的数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 调用更新方法
        Brand brand = brandMapper.selectById(id);

        // 释放资源
        sqlSession.close();
        return brand;
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 调用方法进行删除
        brandMapper.deleteByIds(ids);
        // 提交事物
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 查询页面所需数据
        int begin = (currentPage - 1) * pageSize; // 计算从开始查询
        int size = pageSize;
        List<Brand> rows = brandMapper.selectByPage(begin, size);

        // 查询总条目数
        int totalCount = brandMapper.selectTotalCount();

        // 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        // 返回PageBean
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 查询页面所需数据
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //System.out.println("接口实现中:"+pageSize+"和"+begin);
        //处理条件
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%" + brandName + "%");
        }

        String CompanyName = brand.getCompanyName();

        if (CompanyName != null && CompanyName.length() > 0){
            brand.setCompanyName("%" + CompanyName + "%");
        }

        //System.out.println("处理条件后:"+brand.getCompanyName());

        // 查询当前页数据
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin, size, brand);
        //System.out.println("这里是rows"+rows);
        // 查询数据数据条
        int totalCountByCondition = brandMapper.selectTotalCountByCondition(brand);
       // System.out.println("总数:"+totalCountByCondition);

        // 封装javaBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCountByCondition);

        sqlSession.close();
        // 返回对象
        return pageBean;
    }
}
