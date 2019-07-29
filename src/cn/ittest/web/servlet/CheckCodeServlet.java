package cn.ittest.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int width = 80;
       int heigth = 30;
        //创建对象，在内存中代表一张图片（验证码图片对象）
        BufferedImage img = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_RGB);

        //美化图片
        //2.1填充背景
        Graphics g = img.getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0,0,width,heigth);

//        //2.2画边框
//        g.setColor(Color.blue);
//        g.drawRect(0,0,width-1,heigth-1);

        String str = "ABCDEFGHIJKLMNOPQRSTWVUXYZabcdefghijklmnopqrstwvuxyz0123456789";
        Random ran = new Random();
        String checkcode = "";
//        int index = ran.nextInt(str.length());
        //2.3 写验证码
        for (int j = 1;j <= 4;j++){
            int index = ran.nextInt(str.length());
            checkcode = checkcode+str.charAt(index);
        }
        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        g.drawString(checkcode,15,25);
        HttpSession session = request.getSession();
        session.setAttribute("checkcode",checkcode);
//        g.drawString(str.charAt(ran.nextInt(str.length()))+"",20,25);
//        g.drawString(str.charAt(ran.nextInt(str.length()))+"",40,25);
//        g.drawString(str.charAt(ran.nextInt(str.length()))+"",60,25);
//        g.drawString(str.charAt(ran.nextInt(str.length()))+"",80,25);

        //画干扰线
        g.setColor(Color.yellow);
        for (int i = 0;i <5;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(heigth);
            int y2 = ran.nextInt(heigth);

            g.drawLine(x1,y1,x2,y2);
        }
        //将图片输出到页面展示
        ImageIO.write(img,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            this.doPost(request,response);
    }
}
