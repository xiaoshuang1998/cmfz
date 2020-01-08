<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>love</title>
    <link href="favicon.ico" rel="shortcut icon" />
    <link href="../boot/css/bootstrap.min.css" rel="stylesheet">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript">
       function login() {
           $.ajax({
                url:"${pageContext.request.contextPath}/admin/login",
               type:"post",
               dataType:"json",
               data:$("#loginForm").serialize(),
               success:function (data) {
                    if (data.status==200){
                        location.href = "${pageContext.request.contextPath}/jsp/main.jsp";
                    }else {
                        alert(data.msg);
                    }
               }
           })
       }
       function chgCode(){
           document.getElementById("img1").src = "${pageContext.request.contextPath }/admin/createImg?xx="+Math.random();
       }
    </script>
</head>
<body style=" background: url(../img/a2.jpg); background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">持明法洲</h4>
        </div>
        <form id="loginForm" method="post">
        <div class="modal-body" id = "model-body">
            <div class="form-group">
                <input type="text" class="form-control"placeholder="用户名" autocomplete="off" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" autocomplete="off" name="password">
            </div>
            <div class="form-group">
                <input type="text" name="clientCode" size="4" maxlength="4"/>
                <img id="img1" src="${pageContext.request.contextPath}/admin/createImg"/>
                <a href="javascript:void(0)" onclick="chgCode()">看不清</a>
            </div>
            <span id="msg"></span>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <button type="button" class="btn btn-primary form-control" id="log" onclick="login()">登录</button>
            </div>
        </div>
        </form>
    </div>
</div>
</body>
</html>
