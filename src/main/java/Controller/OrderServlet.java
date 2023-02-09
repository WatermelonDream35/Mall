package Controller;

import POJO.Goods;
import POJO.Order;
import POJO.OrderItem;
import POJO.User;
import Service.OrderService;
import Service.impl.OrderServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/5
 * @注释
 */

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取购物车商品信息
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        // 转为List<Goods>对象
        List<Goods> goods = JSON.parseArray(params, Goods.class);

        // 获取totalPrice和userId
        String _totalPrice = req.getParameter("totalPrice");
        String _userId = req.getParameter("userId");

        float totalPrice = Float.parseFloat(_totalPrice);
        int userId = Integer.parseInt(_userId);

        if (userId == -1) {
            resp.getWriter().write("error");
            return;
        }

        Order order = orderService.createOrder(goods, totalPrice, userId);

        //2. 转为JSON
        String jsonString = JSON.toJSONStringWithDateFormat(order, "yyyy-MM-dd HH:mm:ss");
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 展示全部订单
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过orderService查询所有订单
        List<Order> orders = orderService.showAllOrders();

        //2. 转为JSON
        String jsonString = JSON.toJSONStringWithDateFormat(orders, "yyyy-MM-dd HH:mm:ss");
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 展示我的订单
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取userId
        String _userId = req.getParameter("userId");

        int userId = Integer.parseInt(_userId);

        List<Order> myOrders = orderService.showMyOrders(userId);

        //2. 转为JSON
        String jsonString = JSON.toJSONStringWithDateFormat(myOrders, "yyyy-MM-dd HH:mm:ss");

        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 显示订单详情
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取orderId
        String orderId = req.getParameter("orderId");

        if (orderId != null && !orderId.equals("")) {
            // 2. 查询订单项
            List<OrderItem> orderItems = orderService.showOrderDetail(orderId);

            //3. 转为JSON
            String jsonString = JSON.toJSONString(orderItems);
            //4. 写数据
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }

    /**
     * 发货
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取Order
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        Order order = JSON.parseObject(params, Order.class);

        // 2. 调用orderService的sendOrder方法发货
        orderService.sendOrder(order);

        resp.getWriter().write("success");
    }

    /**
     * 签收
     *
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取Order
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        Order order = JSON.parseObject(params, Order.class);

        // 2. 调用orderService的receiveOrder方法发货
        orderService.receiveOrder(order);

        resp.getWriter().write("success");
    }

    /**
     * 各种排序规则
     * @param req
     * @param resp
     * @return void
     * @author 杨梓韩
     * @date 2023/2/7
     */
    public void sortByStatusCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 调用方法
        List list = orderService.sortByStatusCount();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(list);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void sortByStatusPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 调用方法
        List list = orderService.sortByStatusPrice();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(list);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void sortByGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 调用方法
        List list = orderService.sortByGoods();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(list);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void sortByType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 调用方法
        List list = orderService.sortByType();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(list);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void sortByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 调用方法
        List list = orderService.sortByUser();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(list);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

}
