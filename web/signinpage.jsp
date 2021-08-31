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
    if(session.getAttribute("exist") != null && session.getAttribute("exist").equals("false")){
%>
    <div class="container mt-3">
        <div class="alert alert-danger d-flex align-items-center" role="alert">
            <div style="width: 98%">
                Incorrect <strong>email</strong> or <strong>password</strong>!
            </div>
        </div>
    </div>
<%
    }
%>
<div class="container mt-3">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h5 class="fs-5 mt-2">Login Page</h5>
                </div>
                <form action="/signinpage" method="post">
                    <div class="card-body d-flex">
                        <div style="width: 10%" class="mt-4">
                            <p>Email:</p><br>
                            <p>Password:</p>
                        </div>
                        <div style="width: 90%" class="mt-3">
                            <input type="email" class="form-control border" name="emailInput"><br>
                            <input type="password" class="form-control border" name="passwordInput">
                        </div>
                    </div>
                    <button class="btn btn-success mx-lg-4" type="submit">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
