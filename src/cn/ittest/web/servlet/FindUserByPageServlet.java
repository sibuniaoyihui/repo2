package cn.ittest.web.servlet;

import cn.ittest.domain.PageBean;
import cn.ittest.domain.User;
import cn.ittest.service.UserService;
import cn.ittest.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //设置编码格式
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String address = request.getParameter("address");
//            System.out.println("response"+address);
            //获取参数
            String currentPage = request.getParameter("currentPage");//当前页码数
            String rows = request.getParameter("rows");//每页显示条数
           //调用service查询
            if(currentPage == null || currentPage == ""){
                currentPage="1";
            }
            if (rows == null || rows == ""){
                rows = "5";
            }
            //获取条件查询
            Map<String,String[]> conditions = request.getParameterMap();
//            System.out.println("2"+conditions.get("address")[0]);
            UserService userService = new UserServiceImpl();
            PageBean<User> pb = userService.findUserByPage(currentPage,rows,conditions);
            //将PageBean存入request域中
            request.setAttribute("page",pb);
            request.setAttribute("conditions",conditions);
//            System.out.println(pb);
            //转发到list.jsp中
            request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
