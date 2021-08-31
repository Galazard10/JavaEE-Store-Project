<%@ page import="kz.bootcamp.db.Users" %>
<nav class="navbar navbar-expand-lg navbar-light bg-dark container-fluid">
    <div class="container-fluid">
        <a class="navbar-brand" href="/homepage" style="color: white">BITLAB SHOP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="d-flex float-end" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%
                    Users user = (Users) request.getSession().getAttribute("user");
                    if(user != null){
                        if(user.isAdmin()){
                    %>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/additempage">Add Item</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/welcomepage">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/signinpage">Sign Out</a>
                        </li>
                    <%
                        }else{
                    %>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/basketpage">Basket</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/welcomepage">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" style="color: white" href="/signinpage">Sign Out</a>
                        </li>
                    <%
                        }
                    %>
                <%
                    }else{
                %>
                <li class="nav-item">
                    <a class="nav-link" style="color: white"  href="/signinpage">Sign In</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="/registerpage">Register</a>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>