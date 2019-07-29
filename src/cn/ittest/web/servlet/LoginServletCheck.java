package cn.ittest.web.servlet;


import cn.ittest.domain.Admin;
import cn.ittest.service.UserService;
import cn.ittest.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServletCheck")
public class LoginServletCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String rightCode = (String) request.getSession().getAttribute("checkcode");
        String checkcode = request.getParameter("checkC");
        request.getSession().removeAttribute("checkcode");//确保验证码的一次性
        if(rightCode != null && rightCode.equalsIgnoreCase(checkcode)) {
            Map<String,String[]> map = request.getParameterMap();
//            Iterator<Map.Entry<String,String[]>> it= map.entrySet().iterator();
//            while (it.hasNext()){
//                Map.Entry<String,String[]> entry = it.next();
//                System.out.println(entry.getKey()+"="+entry.getValue()[0]);
//            }
            Admin userLogin = new Admin();
            try {
                BeanUtils.populate(userLogin,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
//        userLogin.setUsername(username);
//        userLogin.setPassword(password);
            UserService userService = new UserServiceImpl();
//            System.out.println(userLogin.getUsername()+":"+userLogin.getPassword());
            Admin user =userService.Login(userLogin);
            if(user == null){

//                request.setAttribute("error","用户名或密码错误");
                request.getSession().setAttribute("error","用户名或密码错误");
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }else{
                request.getSession().setAttribute("user",user.getUsername());
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }

        }else{
            request.getSession().setAttribute("error","验证码错误");
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
