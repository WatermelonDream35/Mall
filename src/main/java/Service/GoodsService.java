package Service;

import POJO.Goods;
import POJO.PageBean;

import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/3
 * @注释
 */
public interface GoodsService {

    /**
     * 查询所有
     * @return List<Goods>
     * @author 杨梓韩
     * @date 2023/2/4
     */
    List<Goods> selectAll();


    /**
     * 分页条件查询
     *
     * @param currentPage
     * @param pageSize
     * @param goods
     * @return POJO.PageBean<POJO.Goods>
     * @author 杨梓韩
     * @date 2023/2/4
     */
    PageBean<Goods> selectByPageAndCondition(int currentPage, int pageSize, Goods goods);

    /**
     * 添加商品
     *
     * @param goods
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    void add(Goods goods);

    /**
     * 删除商品
     *
     * @param id
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    void delete(int id);

    /**
     * 批量删除
     *
     * @param ids
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    void deleteByIds(int[] ids);

    /**
     * 修改商品
     * @param goods
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    void update(Goods goods);
}
