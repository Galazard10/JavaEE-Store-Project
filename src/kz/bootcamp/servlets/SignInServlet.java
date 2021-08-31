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

@WebServlet(value="/signinpage")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("exist");
        req.getSession().removeAttribute("items");
        req.getRequestDispatcher("/signinpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailInput");
        String password = req.getParameter("passwordInput");
        Users user = null;
        try {
            user = DBManager.getUser(email, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(user != null){
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/welcomepage.jsp").forward(req, resp);
        }else{
            req.getSession().setAttribute("exist", "false");
            req.getRequestDispatcher("/signinpage.jsp").forward(req, resp);
        }
    }
}