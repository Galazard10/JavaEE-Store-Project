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
        ArrayList<Items> items = (ArrayList<Items>) session.getAttribute("items");
    %>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style="width: 5%">Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Memory</th>
                    <th>Ram</th>
                    <th>System</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                if(items != null){
                    for(Items i: items){
                %>
                        <tr>
                            <td><%=i.getId()%></td>
                            <td><%=i.getName()%></td>
                            <td><%=i.getPrice()%></td>
                            <td><%=i.getMemory()%></td>
                            <td><%=i.getRam()%></td>
                            <td><%=i.getSystem()%></td>
                            <td>
                                <form action="/deletebasket" method="post">
                                    <input type="hidden" value="<%=i.getId()%>" name="id">
                                    <button class="btn btn-danger" type="submit">- Remove</button>
                                </form>
                            </td>
                        </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
