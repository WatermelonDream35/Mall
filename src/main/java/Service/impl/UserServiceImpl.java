package Service.impl;

import DAO.UserDao;
import POJO.User;
import Service.UserService;
import Util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/2
 * @注释
 */
public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     */

    public User login(String username, String password) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserDao
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //4. 调用方法
        User user = mapper.select(username, password);
        //释放资源
        sqlSession.close();

        return user;
    }


    /**
     * 注册方法
     *
     * @return
     */

    public boolean register(User user) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserDao
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        //4. 判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());

        if (u == null) {
            // 用户名不存在，注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();

        return u == null;

    }

    @Override
    public User selectByUsername(String username) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserDao
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //4. 调用方法
        User user = mapper.selectByUsername(username);
        // 释放资源
        sqlSession.close();

        return user;
    }

    @Override
    public String getUsernameById(int id) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserDao
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //4. 调用方法
        String username = mapper.getUsernameById(id);
        // 释放资源
        sqlSession.close();

        return username;
    }

    @Override
    public void updatePwd(int id, String password) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserDao
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //4. 调用方法
        mapper.updatePwd(id, password);

        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}
