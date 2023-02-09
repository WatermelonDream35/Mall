package Service.impl;

import DAO.GoodsDao;
import DAO.OrderDao;
import DAO.OrderItemDao;
import DAO.UserDao;
import POJO.Goods;
import POJO.Order;
import POJO.OrderItem;
import Service.OrderService;
import Util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/5
 * @注释
 */
public class OrderServiceImpl implements OrderService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 创建订单
     *
     * @param goods
     * @param userId
     * @return java.lang.String
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public Order createOrder(List<Goods> goods, Float totalPrice, Integer userId) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取OrderDao和OrderItemDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        OrderItemDao orderItemDao = sqlSession.getMapper(OrderItemDao.class);
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);

        // 创建一个订单对象
        String orderId = System.currentTimeMillis() + "" + userId;

        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setTime(new Date());
        order.setStatus(0); // 未发货

        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Goods good : goods) {
            // 获取购物车中的每一个订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setName(good.getGoodsName());
            orderItem.setPrice(good.getPrice());
            orderItem.setCount(good.getBuyNumber());
            orderItem.setOrderId(orderId);

            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Goods _goods = goodsDao.selectById(good.getId());
            _goods.setStockNumber(_goods.getStockNumber() - good.getBuyNumber());
            _goods.setSalesNumber(_goods.getSalesNumber() + good.getBuyNumber());
            goodsDao.update(_goods);
        }

        // 释放资源
        sqlSession.close();

        return order;
    }

    /**
     * 展示所有订单
     *
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public List<Order> showAllOrders() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List<Order> orders = orderDao.queryOrders();

        //释放资源
        sqlSession.close();

        return orders;
    }

    /**
     * 展示我的订单
     *
     * @param id
     * @return java.util.List<POJO.Order>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public List<Order> showMyOrders(Integer id) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List<Order> orders = orderDao.queryOrdersByUserId(id);

        //释放资源
        sqlSession.close();

        return orders;
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return java.util.List<POJO.OrderItem>
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取orderItemDao
        OrderItemDao orderItemDao = sqlSession.getMapper(OrderItemDao.class);

        List<OrderItem> orderItems = orderItemDao.queryOrderItemsById(orderId);

        //释放资源
        sqlSession.close();

        return orderItems;
    }

    /**
     * 发货
     *
     * @param order
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public void sendOrder(Order order) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        order.setStatus(1); // 已发货
        orderDao.updateOrder(order);

        sqlSession.commit();//提交事务

        //释放资源
        sqlSession.close();
    }

    /**
     * 签收
     *
     * @param order
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    @Override
    public void receiveOrder(Order order) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        order.setStatus(2); //已签收
        orderDao.updateOrder(order);

        sqlSession.commit();//提交事务

        //释放资源
        sqlSession.close();
    }

    @Override
    public List sortByStatusCount() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List list = orderDao.sortByStatusCount();

        //释放资源
        sqlSession.close();

        return list;
    }

    @Override
    public List sortByStatusPrice() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List list = orderDao.sortByStatusPrice();

        //释放资源
        sqlSession.close();

        return list;
    }

    @Override
    public List sortByGoods() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List list = orderDao.sortByGoods();

        //释放资源
        sqlSession.close();

        return list;
    }

    @Override
    public List sortByType() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List list = orderDao.sortByType();

        //释放资源
        sqlSession.close();

        return list;
    }

    @Override
    public List sortByUser() {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取OrderDao
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);

        List list = orderDao.sortByUser();

        //释放资源
        sqlSession.close();

        return list;
    }
}
