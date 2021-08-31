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
    <body onload="loadFeedback()">
        <div class="container-fluid mx-0 p-0">
            <div class="row">
                <div class="col-12">
                    <%@include file="navbar.jsp"%>
                </div>
            </div>
        </div>
        <%
            Items item = (Items) request.getAttribute("item");
            if(item!=null){

        %>
        <div class="d-flex justify-content-around container">
            <div class="container mt-3 text-center" style="width: 50%">
                <img src="<%=item.getImg()%>" class="img-fluid" style="width: 700px; object-fit: contain; ">
            </div>
            <div class="col-3 text-center container" style="width: 30%; margin-top: 100px">
                <label for="itemName" class="mt-3">Name:</label>
                <input type="text" id="itemName" readonly value="<%=item.getName()%>" class="form-control">
                <label for="itemPrice" class="mt-3">Price:</label>
                <input type="text" id="itemPrice" readonly value="<%=item.getPrice()%>" class="form-control">
                <label for="itemMemory" class="mt-3">Memory:</label>
                <input type="text" id="itemMemory" readonly value="<%=item.getMemory()%>" class="form-control">
                <label for="itemRam" class="mt-3">RAM:</label>
                <input type="text" id="itemRam" readonly value="<%=item.getRam()%>" class="form-control">
                <label for="itemSystem" class="mt-3">System:</label>
                <input type="text" id="itemSystem" readonly value="<%=item.getSystem()%>" class="form-control">
                <p class="fs-5 mt-2">
                    <%
                        if(user != null){
                    %>
                        <a href="JavaScript:void(0)" onclick="toLike()" style="color: red; text-decoration: none">&#9829;</a>
                        <script type="text/javascript">
                            function toLike(){
                                $.post("ajaxToLike", {
                                    itemId: <%=item.getId()%>
                                }, function(data){
                                    document.getElementById("likeAmount").innerHTML = data;
                                });
                            }
                        </script>
                    <%
                        }
                    %>
                    <span style="font-weight: bold;" id="likeAmount"><%=item.getLikes()%> like<%=(item.getLikes()==1?"": "s")%></span>
                </p>
                <%
                    if(user!=null && user.isAdmin()){
                %>
                    <form action="/edititempage" method="get" class="mt-3">
                        <input type="hidden" name="itemId" value="<%=item.getId()%>">
                        <button type="submit" class="btn btn-primary">Edit Item</button>
                    </form>
                <%
                    }
                %>
            </div>
        </div>
        <script type="text/javascript">
            function loadFeedback(){
                $.get("/ajaxreadfeedback", {
                    itemId: <%=item.getId()%>
                }, function (result){
                    feedbacklist = JSON.parse(result);
                    htmlCode = "";

                    for(i=0;i<feedbacklist.length;i++){
                        htmlCode += "<a href='JavaScript: void(0)' class='list-group-item list-group-item-action'>";
                        htmlCode += "<div class='d-flex w-100 justify-content-between'>"
                        htmlCode += "<h5 class='mb-1'>" + feedbacklist[i].feedback +"</h5>"
                        if((<%=(user!=null?user.getId():-1)%> == feedbacklist[i].user.id)) {
                            htmlCode += "<small onclick='deleteFeedback(" + feedbacklist[i].id + ")'>x</small>"
                        }
                        htmlCode += "</div>"
                        htmlCode += "<p class='mb-1'>" + feedbacklist[i].user.fullName +"</p>"
                        htmlCode += "<small>"+ feedbacklist[i].postDate +"</small>"
                        htmlCode += "</a>"
                    }
                    document.getElementById("feedbackDiv").innerHTML = htmlCode;
                });
            }
        </script>

        <%--            COMMENTS--%>
        <div class="mb-5 mt-5 container">
            <%
                if(user != null){
            %>
            <div>
                <textarea class="form-control" id="comment_text_id"></textarea>
                <button class="btn btn-primary btn-sm mt-3" onclick="addComment()">LEAVE FEEDBACK</button>
            </div>
            <script type="text/javascript">
                function addComment(){
                    let feedbackText = document.getElementById("comment_text_id");

                    $.post("/ajaxaddfeedback", {
                        feedback: feedbackText.value,
                        itemId: <%=item.getId()%>
                    }, function(result){
                        feedbackText.value = "";
                        loadFeedback();
                    });
                }
                function deleteFeedback(id){
                    toDelete = confirm("Are you sure?");
                    if(toDelete){
                        $.post("/ajaxdeletefeedback", {
                            feedbackId: id
                        }, function (result){
                            loadFeedback();
                        });
                    }
                }
            </script>
            <%
                }else{
            %>
            <h5>Please, <a href="/signinpage">sign in</a> or <a href="/registerpage">register</a> to leave a feedback</h5>
            <%
                }
            %>
            <div class="list-group mt-3" id="feedbackDiv">

            </div>
        </div>

        <%
            }
        %>
        <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
