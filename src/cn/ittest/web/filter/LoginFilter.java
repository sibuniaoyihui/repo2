package cn.ittest.web.filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/*
*
* 完成登录验证的过滤器
* */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 强转格式
        HttpServletRequest request = (HttpServletRequest) req;
        //2. 获去资源的请求路径
        String uri = request.getRequestURI();
        //3. 判断是否是登录相关的资源
        if(uri.contains("/login.jsp")||uri.contains("/checkCodeServlet")||uri.contains("/loginServletCheck")||uri.contains("/js/")||uri.contains("/css/")||uri.contains("/fonts/")){
            // 包含说明用户就是想要去登录，方行
            chain.doFilter(req, resp);
        }else{
            //不包含说明用户不是想要去登录,判断用户是否登录
            //从session中获取user
            Object obj = request.getSession().getAttribute("user");
            if(obj != null){
                //说明用户已经登录了，放行
                chain.doFilter(req, resp);
            }else{
                request.getSession().setAttribute("error","你尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }

        }

       // chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
