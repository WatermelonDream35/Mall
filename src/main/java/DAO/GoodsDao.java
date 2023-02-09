package DAO;

import POJO.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/3
 * @注释
 */
public interface GoodsDao {
    /**
     * 查询所有
     *
     * @return java.util.List<POJO.Goods>
     * @author 杨梓韩
     * @date 2023/2/4
     */
    @Select("select * from tb_goods")
    @ResultMap("goodsResultMap")
    List<Goods> selectAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return java.util.List<POJO.Goods>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Select("select * from tb_goods where id = #{id}")
    @ResultMap("goodsResultMap")
    Goods selectById(int id);

    /**
     * 根据分页和条件查询
     *
     * @param begin
     * @param size
     * @param goods
     * @return java.util.List<POJO.Goods>
     * @author 杨梓韩
     * @date 2023/2/4
     */
    List<Goods> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("goods") Goods goods);

    /**
     * 根据条件查询总页码
     *
     * @param goods
     * @return int
     * @author 杨梓韩
     * @date 2023/2/4
     */
    int selectTotalCountByCondition(Goods goods);

    /**
     * 添加商品
     *
     * @param goods
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    @Insert("insert into tb_goods values(null,#{goodsName},#{img},#{price},#{stockNumber},0,0,#{type},#{description})")
    void add(Goods goods);

    /**
     * 删除商品
     *
     * @param id
     * @return
     * @author 杨梓韩
     * @date 2023/2/4
     */
    @Delete("delete from tb_goods where id = #{id}")
    void delete(int id);

    /**
     * 批量删除
     *
     * @param ids
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 修改商品
     *
     * @param goods
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Update("update tb_goods set goods_name=#{goodsName},img=#{img},price=#{price}," +
            "stock_number=#{stockNumber},sales_number=#{salesNumber},type=#{type},description=#{description} " +
            "where id =#{id}")
    void update(Goods goods);
}
