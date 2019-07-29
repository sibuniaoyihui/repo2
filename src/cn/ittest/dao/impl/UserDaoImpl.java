package cn.ittest.dao.impl;

import cn.ittest.dao.UserDao;
import cn.ittest.domain.Admin;
import cn.ittest.domain.User;
import cn.ittest.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        String sql = "select *from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
    public Admin Login(Admin adminLogin){
        //使用JDBC操作数据库
       try {
           String sql = "select * from admin where username = ? and password = ?";
           Admin admin = template.queryForObject(sql,new BeanPropertyRowMapper<Admin>(Admin.class), adminLogin.getUsername(), adminLogin.getPassword());
           return admin;
       }catch (Exception e){
        e.printStackTrace();
        return null;
       }
    }
    @Override
    public void deleteUser(String userId) {
        try{
            String sql = "delete from user where id = ?";
            template.update(sql,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void addUser(User user) {
        try{
            String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
            template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyUser(User user) {
        try{
            String sql = "update user set gender = ?,age = ?,address = ?,qq = ?,email = ? where id =?";
            template.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int findTotalCount( Map<String, String[]> conditions) {
        //定义模板初始化sql
        String sql = "select count(*) from user where 1=1";
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer(sql);
        //遍历Map集合
        Set<String> ketset = conditions.keySet();
        for (String key:ketset){
            if("currentPage".equals(key)|| "rows".equals(key) || "userId".equals(key)||"check".equals(key)){
                continue;
            }
            String value = conditions.get(key)[0];
            if(value != null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//对应？参数的值
            }
        }
//        int a = template.queryForObject(sb.toString(),Integer.class,params.toArray());
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findBypage(int start, int rows, Map<String, String[]> conditions) {
        String sql = "select *from user where 1 = 1";
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer(sql);
        //遍历Map集合
        Set<String> ketset = conditions.keySet();
        for (String key:ketset){
            if("currentPage".equals(key)|| "rows".equals(key) || "userId".equals(key) || "check".equals(key)){
                continue;
             }
            String value = conditions.get(key)[0];
            if(value != null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//对应？参数的值
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
//        List<User> a = template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
