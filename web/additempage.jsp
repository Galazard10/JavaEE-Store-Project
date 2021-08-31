<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bootcamp.db.Items" %>
<%@ page import="kz.bootcamp.db.DBManager" %>
<%@ page import="kz.bootcamp.db.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>BITLAB SHOP</title>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
        <script src="/js/tinymce/tinymce.min.js"></script>
        <script>
            tinymce.init({selector:'textarea'});
        </script>
    </head>
    <body>
        <div class="container-fluid mx-0 p-0">
            <div class="row">
                <div class="col-12">
                    <%@include file="navbar.jsp"%>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form action="/additempage" method="post">
                        <h1 class="mt-4 text-success">ADD NEW ITEM</h1>
                        <%
                            if(request.getParameter("success") != null){
                        %>
                            <div class="container mt-3">
                                <div class="alert alert-success d-flex align-items-center" role="alert">
                                    <div style="width: 98%">
                                        <b>New Item Added successfully!</b>
                                    </div>
                                </div>
                            </div>
                        <%
                            }
                        %>
                        <div class="mt-4">
                            <label for="itemName">Item Name:</label>
                            <input type="text" id="itemName" class="form-control" required name="itemName">
                        </div>
                        <div class="mt-3">
                            <label for="itemPrice">Price:</label>
                            <input type="text" id="itemPrice" class="form-control" required name="itemPrice">
                        </div>
                        <div class="mt-3">
                            <label for="itemMemory">Memory:</label>
                            <input type="text" id="itemMemory" class="form-control" required name="itemMemory">
                        </div>
                        <div class="mt-3">
                            <label for="itemRam">RAM:</label>
                            <input type="text" id="itemRam" class="form-control" required name="itemRam">
                        </div>
                        <div class="mt-3">
                            <label for="itemSystem">System:</label>
                            <input type="text" id="itemSystem" class="form-control" required name="itemSystem">
                        </div>
                        <div class="mt-3">
                            <label for="itemImg">Image URL:</label>
                            <input type="text" id="itemImg" class="form-control" required name="itemImg">
                        </div>
                        <div>
                            <button type="submit" class="btn btn-primary mt-3">ADD ITEM</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
