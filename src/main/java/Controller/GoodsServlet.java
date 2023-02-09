package Controller; /**
 * @author 杨梓韩
 * @date 2023/2/3
 * @version 1.0
 * @注释
 */

import POJO.Goods;
import POJO.PageBean;
import Service.GoodsService;
import Service.impl.GoodsServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/goods/*")
public class GoodsServlet extends BaseServlet {
    private GoodsService goodsService = new GoodsServiceImpl();

    /**
     * 查询所有
     * @return java.util.List<POJO.Goods>
     * @author 杨梓韩
     * @date 2023/2/4
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Goods> goods = goodsService.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(goods);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Goods
        Goods goods = JSON.parseObject(params, Goods.class);

        //2. 调用service查询
        PageBean<Goods> pageBean = goodsService.selectByPageAndCondition(currentPage, pageSize, goods);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加商品
     *
     * @param request
     * @param response
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Goods对象
        Goods goods = JSON.parseObject(params, Goods.class);

        //2. 调用service添加
        goodsService.add(goods);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 删除商品
     *
     * @param request
     * @param response
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _id = request.getParameter("id");

        int id = Integer.parseInt(_id);

        //2. 调用service添加
        goodsService.delete(id);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @return void
     * @author 杨梓韩
     * @date 2023/2/4
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        goodsService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 修改商品
     * @param request
     * @param response
     * @return void
     * @author 杨梓韩
     * @date 2023/2/5
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Goods对象
        Goods goods = JSON.parseObject(params, Goods.class);

        //2. 调用service添加
        goodsService.update(goods);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
}
