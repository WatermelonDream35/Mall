package DAO;

import POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/2
 * @注释
 */
public interface UserDao {
    /**
     * 根据用户名和密码查询用户对象
     * @param username
     * @param password
     * @return POJO.User
     * @author 杨梓韩
     * @date 2023/2/2
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return POJO.User
     * @author 杨梓韩
     * @date 2023/2/2
     */
    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(String username);

    /**
     * 根据id查询用户名
     *
     * @param id
     * @return java.lang.String
     * @author 杨梓韩
     * @date 2023/2/7
     */
    @Select("select username from tb_user where id = #{id}")
    String getUsernameById(int id);

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into tb_user values(null,#{username},#{password},#{email})")
    void add(User user);

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @return void
     * @author 杨梓韩
     * @date 2023/2/7
     */
    @Update("update tb_user set password=#{password} where id=#{id}")
    void updatePwd(@Param("id") int id, @Param("password") String password);
}
