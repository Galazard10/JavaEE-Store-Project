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
            String id = request.getParameter("itemId");
            long itemId = Long.parseLong(id);
            Items item = DBManager.getItembyId(itemId);
        %>
        <div class="container mt-3 text-center">
            <img src="<%=item.getImg()%>" class="img-fluid" style="width: 700px; object-fit: contain; ">
        </div>

        <div class="col-3 text-center container mb-5">
            <form action="/edititempage" method="post" class="mt-3">
            <label for="itemName" class="mt-3">Name:</label>
            <input name="itemName" type="text" id="itemName" value="<%=item.getName()%>" class="form-control">
            <label for="itemPrice" class="mt-3">Price:</label>
            <input name="itemPrice" type="text" id="itemPrice" value="<%=item.getPrice()%>" class="form-control">
            <label for="itemMemory" class="mt-3">Memory:</label>
            <input name="itemMemory" type="text" id="itemMemory" value="<%=item.getMemory()%>" class="form-control">
            <label for="itemRam" class="mt-3">RAM:</label>
            <input name="itemRam" type="text" id="itemRam" value="<%=item.getRam()%>" class="form-control">
            <label for="itemSystem" class="mt-3">System:</label>
            <input name="itemSystem" type="text" id="itemSystem" value="<%=item.getSystem()%>" class="form-control">
            <label for="itemImg" class="mt-3">New Image:</label>
            <input name="itemImg" type="text" id="itemImg" value="<%=item.getImg()%>" class="form-control">
                <input type="hidden" name="itemId" value="<%=item.getId()%>">
                <button type="submit" class="btn btn-success mt-3">Save Changes</button>
            </form>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">Delete Item</button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Delete Item</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            You are deleting this Item from the store.
                            Are you sure?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <form action="/deleteitempage" method="post">
                                <input type="hidden" name="itemId" value="<%=item.getId()%>">
                                <button type="submit" class="btn btn-danger">Delete Item</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
