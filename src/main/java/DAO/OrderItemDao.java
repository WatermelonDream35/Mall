package DAO;

import POJO.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/5
 * @注释
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return int
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Insert("insert into tb_order_item values(null,#{name},#{price},#{count},#{orderId})")
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据orderId查询订单项
     * @param orderId
     * @return java.util.List<POJO.OrderItem>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Select("select * from tb_order_item where order_id = #{orderId}")
    @ResultMap("orderItemResultMap")
    List<OrderItem> queryOrderItemsById(String orderId);
}
