package cn.ittest.web.servlet;

import cn.ittest.service.UserService;
import cn.ittest.service.impl.UserServiceImpl;
import cn.ittest.util.GetCharset;
import cn.ittest.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("charset=uft-8");
        //获取id数值
        String ids [] = request.getParameterValues("check");  //获得的参数的编码格式是GB2312
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String currentPage = request.getParameter("currentPage");
//        System.out.println(GetCharset.getEncoding(address));

//        System.out.println(address);
        String email = request.getParameter("email");

        //建立Service对象,执行删除操作
        UserService userService = new UserServiceImpl();
        userService.deleteUsers(ids);
        //页面跳转，使用重定向
//        request.getRequestDispatcher("/findUserByPageServlet?name="+name+"&address="+address+"&email="+email).forward(request,response);
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?name="+URLEncoder.encode(name,"utf-8")+"&address="+URLEncoder.encode(address,"utf-8")+"&email="+URLEncoder.encode(email,"utf-8")+"&currentPage="+URLEncoder.encode(currentPage,"utf-8"));
//         response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?name="+name+"&address="+address+"&email="+email);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
