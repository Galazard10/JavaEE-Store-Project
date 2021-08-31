<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2021
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>BITLAB SHOP</title>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    </head>
<body>
    <div class="container-fluid mx-0 p-0">
        <div class="row">
            <div class="col-12">
                <%@include file="navbar.jsp"%>
            </div>
        </div>
    </div>
    <div class="container mt-3">
        <h2 class="mb-4 text-success">USER REGISTRATION</h2>
        <form action="/registerpage" method="post">
            <label for="newUserFullName">Full Name:</label>
            <input type="text" id="newUserFullName" placeholder="Insert Your Full Name" class="form-control mb-3" name="fullName" required>
            <label for="newUserEmail">E-mail:</label>
            <input type="email" id="newUserEmail" placeholder="Insert Your Email" class="form-control mb-3" name="email" required>
            <%
                String emailError = request.getParameter("emailError");
                if(emailError != null){
            %>
                <div class="container mt-3">
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <div style="width: 98%">
                            <strong>This E-mail is already in use!</strong>
                        </div>
                    </div>
                </div>
            <%
                }
            %>
            <label for="newUserPass">Password:</label>
            <input type="password" id="newUserPass" placeholder="Insert Your Password" class="form-control mb-3" name="password" required>
            <%
                String passerror = request.getParameter("passError");
                if(passerror != null){
            %>
                <div class="container mt-3">
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <div style="width: 98%">
                            <b>Password is too short!</b>
                        </div>
                    </div>
                </div>
            <%
                }
            %>
            <label for="newUserRePass">Retype Password:</label>
            <input type="password" id="newUserRePass" placeholder="Retype Your Password" class="form-control mb-3" name="repassword" required>
            <%
                String repasserror = request.getParameter("repassError");
                if(repasserror != null){
            %>
                <div class="container mt-3">
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <div style="width: 98%">
                            <b>Passwords are not the same!</b>
                        </div>
                    </div>
                </div>
            <%
                }
            %>
            <button type="submit" class="btn btn-primary mb-3">Register</button>
        </form>
    </div>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
