<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bootcamp.db.Items" %>
<%@ page import="kz.bootcamp.db.DBManager" %>
<%@ page import="kz.bootcamp.db.Users" %>
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
        <%
        Users user1 = (Users) session.getAttribute("user");
        if(user1 != null){
        %>
            <div class="container">
                <div class="justify-content-center d-flex mt-4">
                    <h1>Welcome
                    <%
                        if(user1.isAdmin()){
                    %>
                        Admin
                    <%
                        }
                    %>
                    <strong><%=user1.getFullName()%></strong>!</h1>
                </div>
                <div class="justify-content-center d-flex fs-4">
                    <p>This is your profile page</p>
                </div>
            </div>
            <div class="container mt-3">
                <label for="emailLabel">EMAIL:</label>
                <input type="email" readonly class="form-control" id="emailLabel" value="<%=user1.getEmail()%>">
            </div>
            <form action="/updateprofile" method="post">
                <div class="container mt-3">
                    <label for="nameLabel">NAME:</label>
                    <input type="text" class="form-control" id="nameLabel" value="<%=user1.getFullName()%>" name="fullName">
                </div>
                <div class="container">
                    <button class="btn btn-success mt-3" type="submit">UPDATE PROFILE</button>
                </div>
            </form>
            <form action="/updatepassword" method="post">
                <div class="container mt-3">
                    <%
                        if(request.getParameter("passSuccess") != null){
                    %>
                        <div class="container mb-3">
                            <div class="alert alert-success d-flex align-items-center" role="alert">
                                <div style="width: 98%">
                                    <b>Password changed successfully!</b>
                                </div>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    <label for="oldpass">OLD PASSWORD:</label>
                    <input class="form-control mb-3" type="password" id="oldpass" placeholder="Insert your old password" name="oldpass" required>
                    <%
                        if(request.getParameter("oldpassError") != null){
                    %>
                        <div class="container mb-3">
                            <div class="alert alert-danger d-flex align-items-center" role="alert">
                                <div style="width: 98%">
                                    <b>Old password is incorrect!</b>
                                </div>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    <label for="newpass">NEW PASSWORD:</label>
                    <input class="form-control mb-3" type="password" id="newpass" placeholder="Insert your new password" name="newpass" required>
                    <%
                        if(request.getParameter("passError") != null){
                    %>
                        <div class="container mb-3">
                            <div class="alert alert-danger d-flex align-items-center" role="alert">
                                <div style="width: 98%">
                                    <b>Password is too short!</b>
                                </div>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    <label for="renewpass">RETYPE NEW PASSWORD:</label>
                    <input class="form-control mb-3" type="password" id="renewpass" placeholder="Retype your new password" name="renewpass" required>
                    <%
                        if(request.getParameter("repassError") != null){
                    %>
                        <div class="container mb-3">
                            <div class="alert alert-danger d-flex align-items-center" role="alert">
                                <div style="width: 98%">
                                    <b>Passwords are not the same!</b>
                                </div>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    <button type="submit" class="btn btn-success">CHANGE PASSWORD</button>
                </div>
            </form>`
        <%
            }
        %>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
