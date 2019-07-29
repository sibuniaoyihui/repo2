<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
    	<%--<base href="<%=basePath%>"/>--%>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
    <%--<%--%>
        <%--String name = request.getParameter("name");--%>
        <%--String id = request.getParameter("id");--%>
        <%--String gender = request.getParameter("gender");--%>
        <%--request.setAttribute("gender",gender);--%>
        <%--String age = request.getParameter("age");--%>
        <%--String address = request.getParameter("address");--%>
        <%--request.setAttribute("address",address);--%>
        <%--String qq = request.getParameter("qq");--%>
        <%--String email = request.getParameter("email");--%>
        <%--String currentPage = request.getParameter("currentPage");--%>
        <%--request.getSession().setAttribute("currentPage",currentPage);--%>
    <%--%>--%>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath}/userModifyServlet?currentPage=${requestScope.currentPage}" method="post">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" value="${user.name}" />
          </div>
            <div class="form-group">
                <label for="id">id：</label>
                <input type="text" class="form-control" id="id" name="id"  readonly="readonly" value="${user.id}" />
            </div>
          <div class="form-group">
            <label>性别：</label>
           <c:if test="${user.gender eq '男'}">
               <input type="radio" name="gender" value="男"  checked="checked"/>男
               <input type="radio" name="gender" value="女"  />女
           </c:if>
              <c:if test="${user.gender eq '女'}">
                  <input type="radio" name="gender" value="男"  />男
                  <input type="radio" name="gender" value="女" checked="checked"/>女
              </c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" placeholder="请输入年龄" value="${user.age}"/>
          </div>

          <div class="form-group">
            <label for="address">籍贯：</label>
             <select name="address" class="form-control" id="address">
                 <option value="广东" <c:if test="${user.address eq '广东'}">selected</c:if>>广东</option>
                <option value="广西"<c:if test="${user.address eq '广西'}">selected</c:if>>广西</option>
                <option value="湖南"<c:if test="${user.address eq '湖南'}">selected</c:if>>湖南</option>
            </select>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" placeholder="请输入QQ号码" id="qq" value="${user.qq}"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" placeholder="请输入邮箱地址" id="email" value="${user.email}"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回" id="re"/>
             </div>
        </form>
        </div>
    <script>
        var re = document.getElementById("re");
        re.onclick = function (ev) {
            history.back();
        }
    </script>
    </body>
</html>