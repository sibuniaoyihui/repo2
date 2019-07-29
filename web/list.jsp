<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        function deleteUser(id){
            if(confirm("你确定要删除吗？")){
                location.href = "${pageContext.request.contextPath}/userDeleteServlet?userId="+id+"&name=${conditions.name[0]}&address=${conditions.address[0]}&email=${conditions.email[0]}&currentPage=${page.currentPage}";
            }
        }
        window.onload =function (ev) {
            var checkAll = document.getElementById("firstCheck");
            var e = document.getElementsByName("check");
            checkAll.onclick = function (ev) {
                for (var i = 0; i < e.length; i++) {
                    e[i].checked = this.checked;
                }
            }
            for (var k = 0;k <e.length;k ++) {
                e[k].onclick = function () {
                    checkAll.checked = false;
                }
            }

        var deleteUsers = document.getElementById("deleteUsers");
        deleteUsers.onclick = function (ev1) {
        var flag = false;
        //表单提交
        if(confirm("你确定要删除选定的数据吗？")){
            for(var j = 0;j < e.length;j ++){
                if(e[j].checked){
                    flag = true;
                    break;
                }
            }
            if(flag){
                document.getElementById("form").submit();
            }
        }
         }
        }
    </script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;margin: 5px">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" class="form-control" id="name"name="name" value="${conditions.name[0]}">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" id="address" name="address" value="${conditions.address[0]}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${conditions.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="deleteUsers">删除选中 </a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/deleteSelectedServlet?&name=${conditions.name[0]}&address=${conditions.address[0]}&email=${conditions.email[0]}&currentPage=${page.currentPage}" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th ><input type="checkbox" id="firstCheck"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:if test="${page.list != null}">
       <c:forEach items="${page.list}" var="user" varStatus="s">
           <tr>
               <td><input type="checkbox" name="check" value="${user.id}"></td>
               <td>${s.count}</td>
               <td>${user.name}</td>
               <td>${user.gender}</td>
               <td>${user.age}</td>
               <td>${user.address}</td>
               <td>${user.qq}</td>
               <td>${user.email}</td>
               <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/userBeanServlet?id=${user.id}&name=${user.name}&gender=${user.gender}&age=${user.age}&address=${user.address}&qq=${user.qq}&email=${user.email}&currentPage=${page.currentPage}" >修改</a>&nbsp;
                   <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})" >删除</a></td>
           </tr>
       </c:forEach>
        </c:if>
    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page.currentPage == 1}">
                    <li class="disabled">
                </c:if>
                <c:if test="${page.currentPage != 1}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?&name=${conditions.name[0]}&address=${conditions.address[0]}&email=${conditions.email[0]}&currentPage=${page.currentPage -1}&rows=5" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${page.totalPage}" var="i">
                <li <c:if test="${page.currentPage == i}">class="active" </c:if>><a href="${pageContext.request.contextPath}/findUserByPageServlet?&name=${conditions.name[0]}&address=${conditions.address[0]}&email=${conditions.email[0]}&currentPage=${i}&rows=5" >${i}</a></li>
                </c:forEach>
                <c:if test="${page.currentPage == page.totalPage}">
                    <li class="disabled">
                </c:if>
                 <c:if test="${page.currentPage != page.totalPage}">
                    <li>
                </c:if>
                    <a  href="${pageContext.request.contextPath}/findUserByPageServlet?&name=${conditions.name[0]}&address=${conditions.address[0]}&email=${conditions.email[0]}&currentPage=${page.currentPage + 1}&rows=5"  aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin: 5px">共${page.totalCount}条记录，共${page.totalPage}页</span>
            </ul>

        </nav>
    </div>
</div>
</body>
</html>
