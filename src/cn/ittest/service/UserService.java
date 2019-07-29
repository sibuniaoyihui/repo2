package cn.ittest.service;

import cn.ittest.domain.Admin;
import cn.ittest.domain.PageBean;
import cn.ittest.domain.User;

import java.util.List;
import java.util.Map;

/*
* 用户管理的业务接口
*/
public interface UserService {
    /*
    *
    * 查询所有用户信息
    * */
    public List<User> findAll();
    public Admin Login(Admin adminLogin);
    public void addUser(User user);
    public void deleteUser(String userId);
    public void deleteUsers(String[] userIds);
    public void modifyUser(User user);
    /*
    *
    * 分页条件查询
    * */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> conditions);
}
