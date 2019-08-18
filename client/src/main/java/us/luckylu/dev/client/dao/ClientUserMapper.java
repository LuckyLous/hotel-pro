package us.luckylu.dev.client.dao;

import org.apache.ibatis.annotations.Param;
import us.luckylu.dev.model.User;

import java.util.List;

/**
 * @author lu
 * @create 2019-03-21 17:48
 */
public interface ClientUserMapper {

    /**
     * 保存用户
     * @param user
     * @return
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @return
     */
    void deleteUser(Integer id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 根据用户名模糊查找用户列表
     * @return
     */
    List<User> findUserByName(String user_name);

    /**
     * 根据用户名和密码查询用户登录
     * @param userName
     * @param password
     * @return
     */
    User login(@Param("userName") String userName, @Param("password") String password);

    List<User> test();
}
