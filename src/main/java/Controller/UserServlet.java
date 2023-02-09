package Controller;


import POJO.User;
import Service.UserService;
import Service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取用户名和密码
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为User对象
        User user = JSON.parseObject(params, User.class);

        //2. 调用service查询
        User u = userService.login(user.getUsername(), user.getPassword());

        //3. 判断
        if (u != null) {
            //登录成功
            response.getWriter().write(u.getId().toString());
        } else {
            // 登录失败,
            response.getWriter().write("error");
        }
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取用户信息
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为User对象
        User user = JSON.parseObject(params, User.class);

//        // 获取用户输入的验证码
//        String checkCode = request.getParameter("checkCode");
//
//        // 程序生成的验证码，从Session获取
//        HttpSession session = request.getSession();
//        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
//
//        // 比对
//        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
//
//            request.setAttribute("register_msg","验证码错误");
//            request.getRequestDispatcher("/register.jsp").forward(request,response);
//
//            // 不允许注册
//            return;
//        }


        //2. 调用service 注册
        boolean flag = userService.register(user);
        //3. 判断注册成功与否
        if (flag) {
            //注册成功
            response.getWriter().write("success");
        } else {
            //注册失败
            response.getWriter().write("error");
        }
    }

    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取username
        String username = request.getParameter("username");

        //2. 调用service查询
        User user = userService.selectByUsername(username);

        //3. 转为JSON
        String jsonString = JSON.toJSONString(user);
        //4. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void getUsernameById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取userId
        String _id = request.getParameter("id");

        int id = Integer.parseInt(_id);

        //2. 调用service查询
        String username = userService.getUsernameById(id);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(username);
    }

    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取id和password
        String _id = request.getParameter("id");
        String password = request.getParameter("password");

        int id = Integer.parseInt(_id);

        //2. 调用service查询
        userService.updatePwd(id, password);

        response.getWriter().write("success");
    }
}
