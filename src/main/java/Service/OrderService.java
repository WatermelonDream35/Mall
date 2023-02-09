package Service;

import POJO.Goods;
import POJO.Order;
import POJO.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/5
 * @注释
 */
public interface OrderService {
    /**
     * 创建订单
     * @param goods
     * @param totalPrice
     * @param userId
     * @return java.lang.String
     * @author 杨梓韩
     * @date 2023/2/5
     */
    Order createOrder(List<Goods> goods, Float totalPrice, Integer userId);

    /**
     * 展示所有订单
     *
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    List<Order> showAllOrders();

    /**
     * 展示我的订单
     *
     * @param id
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    List<Order> showMyOrders(Integer id);

    /**
     * 查询订单详情
     *
     * @param id
     * @return java.util.List<POJO.OrderItem>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    List<OrderItem> showOrderDetail(String id);

    /**
     * 发货
     *
     * @param order
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    void sendOrder(Order order);

    /**
     * 签收
     *
     * @param order
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    void receiveOrder(Order order);

    /**
     * 各种排序规则
     *
     * @return java.util.List
     * @author 杨梓韩
     * @date 2023/2/7
     */
    List sortByStatusCount();

    List sortByStatusPrice();

    List sortByGoods();

    List sortByType();

    List sortByUser();
}
