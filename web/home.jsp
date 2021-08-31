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
        <div class="container-fluid p-0 mx-0">
            <div class="row">
                <div class="col-12">
                    <%@include file="navbar.jsp"%>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center mt-5">
            <h1>Welcome to BITLAB SHOP</h1>
        </div>
        <div class="d-flex justify-content-center mt-1 fs-5">
            <p class="text-black-50">Electronic serviceswith high quality and service</p>
        </div>
        <%
            ArrayList<Items> itemsList = DBManager.getItemlist();
        %>
        <div class="container">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    if(itemsList != null){
                      for(Items i: itemsList){
                %>
                    <div style="display: flex; justify-content: space-around;">
                        <div class="card mt-4" style="min-width: 20rem;">
                            <div class="card-header text-center">
                                <p class="fs-5 mt-2"><%=i.getName()%></p>
                            </div>
                            <div class="card-body text-center d-flex flex-column">
                                <img src="<%=i.getImg()%>" class="img-thumbnail rounded" style="height: 300px; width: 350px; object-fit: contain; object-position:center; background-position: center">
                                <h2 class="text-success"><%=i.getPrice()%></h2>
                                <p class="card-text"><%=i.getRam()%><br><%=i.getMemory()%><br><%=i.getSystem()%></p>
                                <span style="font-weight: bold;" id="likeAmount"><%=i.getLikes()%> like<%=(i.getLikes()==1?"": "s")%></span>
                                <a type="submit" class="btn btn-link" href="/detailspage?itemId=<%=i.getId()%>">More</a>
                                <form action="/basketpage" method="post">
                                    <input type="hidden" name="id" value="<%=i.getId()%>">
                                    <button type="submit" class="btn btn-success form-control">Buy Now</button>
                                </form>
                            </div>
                        </div>
                    </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
      <script type="text/javascript" src="js/bootstrap.min.js"></script>
      </body>
</html>
