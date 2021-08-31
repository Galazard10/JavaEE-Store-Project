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

@WebServlet(value="/updatepassword")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");

        if(user != null) {
            String old_pass = req.getParameter("oldpass");
            String new_pass = req.getParameter("newpass");
            String re_new_pass = req.getParameter("renewpass");
            if (!user.getPassword().equals(old_pass)) {
                resp.sendRedirect("/welcomepage?oldpassError");
            } else if (new_pass.length() < 8) {
                resp.sendRedirect("/welcomepage?passError");
            } else if (!new_pass.equals(re_new_pass)) {
                resp.sendRedirect("/welcomepage?repassError");
            } else {
                user.setPassword(new_pass);
                try {
                    DBManager.savePassword(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/welcomepage?passSuccess");
            }
        }
    }
}
