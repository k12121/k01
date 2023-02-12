package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from tb_brand ;")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加品牌
     */
    @Insert("insert into tb_brand values (null,#{brandName}, #{companyName}, #{ordered}, #{description}, #{status});")
    void addBrand(Brand brand);

    /**
     * 删除品牌
     */
    @Delete("delete from tb_brand where id = #{id};")
    void deleteBrand(int id);

    /**
     * 修改品牌
     */
    @Update("update tb_brand set company_name = #{companyName},brand_name = #{brandName},status = #{status},ordered = #{ordered},description=#{description} where id=#{id};")
    void updateBrand(Brand brand);

    /**
     * 根据id查询
     */
    @Select("select * from tb_brand where id = #{id} ;")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    /**
     * 批量删除
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 查询当前页数据
     *
     * @param begin 从哪里开始查询
     * @param size  查询大小
     * @return 返回查询到的数据
     */

    @Select("select * from tb_brand limit #{begin},#{size};")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_brand ;")
    int selectTotalCount();


    /**
     * 查询数据 分页并且符合条件
     *
     * @param begin 从哪里开始查询
     * @param size  查询大小
     * @return 返回查询到的数据
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("brand")Brand brand);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);


}
