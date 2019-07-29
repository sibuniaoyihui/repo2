package cn.ittest.dao;

import cn.ittest.domain.Admin;
import cn.ittest.domain.User;

import java.util.List;
import java.util.Map;

/*
*
* 用户操作的DAO
* */
public interface UserDao {
    public List<User> findAll();
    public Admin Login(Admin adminLogin);
    public void deleteUser(String userId);
    public void addUser(User user);
    public void modifyUser(User user);
    /*
    * 查询数据库值记录的条数
    * */
    public int findTotalCount(Map<String,String[]> conditions);
    /*
    * 查询指定起始位开始的几条数据
    * */
    public List<User> findBypage(int start, int rows, Map<String, String[]> conditions);


}
