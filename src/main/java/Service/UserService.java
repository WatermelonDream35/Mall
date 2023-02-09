package Service;

import POJO.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/2
 * @注释
 */
public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return POJO.User
     * @author 杨梓韩
     * @date 2023/2/2
     */
    User login(String username, String password);



    /**
     * 注册
     * @param user
     * @return boolean
     * @author 杨梓韩
     * @date 2023/2/2
     */
    boolean register(User user);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return POJO.User
     * @author 杨梓韩
     * @date 2023/2/7
     */
    User selectByUsername(String username);

    /**
     * 根据id查询用户名
     * @param id
     * @return java.lang.String
     * @author 杨梓韩
     * @date 2023/2/7
     */
    String getUsernameById(int id);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return void
     * @author 杨梓韩
     * @date 2023/2/7
     */
    void updatePwd(int id,String password);
}
