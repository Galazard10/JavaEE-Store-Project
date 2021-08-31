package kz.bootcamp.servlets;

import kz.bootcamp.db.DBManager;
import kz.bootcamp.db.Items;
import kz.bootcamp.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value="/ajaxToLike")
public class AjaxToLike extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        int likes = 0;
        if(user != null){
            String id = req.getParameter("itemId");
            Long itemId = Long.parseLong(id);
            Items item = DBManager.getItembyId(itemId);
            if(item != null){
                try {
                    likes = DBManager.toLike(itemId, user.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        PrintWriter out = resp.getWriter();
        out.print(likes);
    }
}
