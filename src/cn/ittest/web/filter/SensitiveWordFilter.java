package cn.ittest.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/*
*
* 敏感词汇过滤器
* */
@WebFilter(value= {"/userAddServlet","/userModifyServlet","/servletdemo2"},dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class SensitiveWordFilter implements Filter {
    public void destroy() {
    }
    public String replaceSensitiveWord(String para){
        if (para != null){
            for (String value:sensitiveWord){
                if (para.contains(value)){
                    para = para.replaceAll(value,"***");
                }
            }
            return para;
        }else {
            return null;
        }

    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 创建代理对象，增强getParameter
        ServletRequest proxy_req= (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否是getParameter()方法
                if(method.getName().equals("getParameter")) {
                    //增强返回值
                    //获取返回值
                    String obj = (String) method.invoke(req, args);
                    if (replaceSensitiveWord(obj) != null) {
                        return replaceSensitiveWord(obj);
                    }
                }else if (method.getName().equals("getParameterMap")){
                    Map<String,String[]> values = (Map<String, String[]>) method.invoke(req,args);
                    Map<String,String[]> map = new HashMap<>();
                    Iterator<String> iterator = values.keySet().iterator();
                    while (iterator.hasNext()){
                        String key = iterator.next();
                        String [] params =  values.get(key);
                        for (int j = 0;j < params.length;j++){
                            if (params[j] != null) {
                                for (String value : sensitiveWord) {
                                    if (params[j].contains(value)) {
                                        params[j] = params[j].replaceAll(value, "***");
                                    }
                                }
                            }
//                            if(replaceSensitiveWord(para) != null){
//                                para = replaceSensitiveWord(para);
//                            }
                        }
                        map.put(key,params);
                    }
                    return map;
                }
                return method.invoke(req, args);
            }
        });
        //2. 放行
        chain.doFilter(proxy_req, resp);
    }
    private List<String> sensitiveWord = new ArrayList<String>();
    public void init(FilterConfig config) throws ServletException {
        BufferedReader br = null;
        try {
            //1. 获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            String realpath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2. 读取文件
             br = new BufferedReader(new FileReader(realpath));
            //3. 将文件的每一行数据增加到List中
            String line = null;
            while ((line = br.readLine()) != null){
                sensitiveWord.add(line);
            }
            System.out.println(sensitiveWord);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
