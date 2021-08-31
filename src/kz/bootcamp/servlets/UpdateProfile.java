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

@WebServlet(value="/updateprofile")
public class UpdateProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users)req.getSession().getAttribute("user");
        if(user != null){
            String fullName = req.getParameter("fullName");
            user.setFullName(fullName);
            try {
                DBManager.saveUser(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/welcomepage?profilesuccess");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
