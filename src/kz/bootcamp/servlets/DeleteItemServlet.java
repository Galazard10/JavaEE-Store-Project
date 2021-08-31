package kz.bootcamp.servlets;

import kz.bootcamp.db.DBManager;
import kz.bootcamp.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/deleteitempage")
public class DeleteItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user == null || user.isAdmin() == false)
            resp.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        long id = Long.parseLong(itemId);
        try {
            DBManager.deleteItem(id);
            resp.sendRedirect("/homepage");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
