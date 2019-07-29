package cn.ittest.service.impl;

import cn.ittest.dao.UserDao;
import cn.ittest.dao.impl.UserDaoImpl;
import cn.ittest.domain.Admin;
import cn.ittest.domain.PageBean;
import cn.ittest.domain.User;
import cn.ittest.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private  UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public Admin Login(Admin adminLogin) {
        return dao.Login(adminLogin);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }
    public void deleteUser(String userId){
        dao.deleteUser(userId);
    }

    @Override
    public void deleteUsers(String[] userIds) {
        //遍历id数组
        for(int i = 0;i < userIds.length;i++){
            dao.deleteUser(userIds[i]);
        }
    }

    @Override
    public void modifyUser(User user) {
        dao.modifyUser(user);
    }


    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> conditions) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        PageBean<User> pb = new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //用到dao访问totalCount
        int totalCount = dao.findTotalCount(conditions);

        pb.setTotalCount(totalCount);
        //设置总页码数
        int totalPage = totalCount % rows == 0?totalCount/rows:totalCount/rows + 1;
        if (currentPage > totalPage){
            currentPage = totalPage;
            pb.setCurrentPage(currentPage);
        }
        if(currentPage <= 0){
            currentPage = 1;
            pb.setCurrentPage(currentPage);
        }
        pb.setTotalPage(totalPage);
        //设置每页查询数据
        int start = (currentPage -1) * rows;
        List<User> users = dao.findBypage(start,rows,conditions);
        pb.setList(users);
        return pb;
    }
}
