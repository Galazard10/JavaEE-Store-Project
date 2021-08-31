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
import java.util.ArrayList;

@WebServlet(value="/registerpage")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user == null)
            req.getRequestDispatcher("/registerpage.jsp").forward(req,resp);
        else
            resp.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Users> users = DBManager.getUserList();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("repassword");
        String fullName = req.getParameter("fullName");

        for(Users u: users){
            if(u.getEmail().equals(email)){
                resp.sendRedirect("/registerpage?emailError");
                break;
            }else if(password.length() < 8) {
                resp.sendRedirect("/registerpage?passError");
                break;
            }else if(!password.equals(rePassword)){
                resp.sendRedirect("/registerpage?repassError");
                break;
            }else if(!u.getEmail().equals(email) && password.equals(rePassword)){
                Users user = new Users(email, password, fullName);
                try {
                    DBManager.addUser(user);
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/welcomepage");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
