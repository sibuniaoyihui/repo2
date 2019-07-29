package cn.ittest.web.servlet;

import cn.ittest.domain.User;
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

@WebServlet("/userModifyServlet")
public class UserModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String,String[]> map = request.getParameterMap();
        User user = new User();
        try{
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        userService.modifyUser(user);
//        request.getRequestDispatcher("/findUserByPageServlet").forward(request,response);
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?currentPage="+request.getParameter("currentPage"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
