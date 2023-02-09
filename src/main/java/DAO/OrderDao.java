package DAO;

import POJO.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/5
 * @注释
 */
public interface OrderDao {
    /**
     * 保存订单
     *
     * @param order
     * @return int
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Insert("insert into tb_order values(#{orderId},#{userId},#{totalPrice},#{time},#{status})")
    void saveOrder(Order order);

    /**
     * 查询全部订单
     *
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Select("select * from tb_order")
    @ResultMap("orderResultMap")
    List<Order> queryOrders();

    /**
     * 根据userId查询订单
     *
     * @param userId
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Select("select * from tb_order where user_id = #{userId}")
    @ResultMap("orderResultMap")
    List<Order> queryOrdersByUserId(Integer userId);

    /**
     * 修改订单
     *
     * @param order
     * @return int
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Update("update tb_order set status = #{status} where order_id = #{orderId}")
    void updateOrder(Order order);

    /**
     * 各种排序规则
     *
     * @return java.util.List
     * @author 杨梓韩
     * @date 2023/2/7
     */
    @Select("select status as name,count(*) as value from tb_order GROUP BY status ORDER BY count(*) desc;")
    List<LinkedHashMap<String,Integer>> sortByStatusCount();

    @Select("select status as name,sum(total_price) as value from tb_order GROUP BY status ORDER BY sum(total_price) desc;")
    List<LinkedHashMap<String,Float>> sortByStatusPrice();

    @Select("select name as name,sum(price*count) as value from tb_order_item GROUP BY name ORDER BY sum(price) desc;")
    List<LinkedHashMap<String,Float>> sortByGoods();

    @Select("select type as name,sum(tb_order_item.price*count) as value from tb_order_item,tb_goods " +
            "where tb_order_item.name=tb_goods.goods_name GROUP BY type " +
            "ORDER BY sum(tb_order_item.price*count) desc;")
    List<LinkedHashMap<String,Float>> sortByType();

    @Select("select user_id as name,sum(total_price) as value from tb_order GROUP BY user_id ORDER BY sum(total_price) desc;")
    List<LinkedHashMap<String,Float>> sortByUser();
}
