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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value="/basketpage")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null) {
            if(user.isAdmin())
                req.getRequestDispatcher("/home.jsp").forward(req, resp);
            else
                req.getRequestDispatcher("/basketpage.jsp").forward(req, resp);
        }
        else
            resp.sendRedirect("/signinpage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null) {
            if(user.isAdmin()) {
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }else{
                String id = req.getParameter("id");
                long idValue = Long.parseLong(id);
                ArrayList<Items> items = (ArrayList<Items>) req.getSession().getAttribute("items");
                if (items == null) {
                    items = new ArrayList<>();
                }
                    Items item = DBManager.getItembyId(idValue);
                    items.add(item);
                    req.getSession().setAttribute("items", items);
                    resp.sendRedirect("/homepage");

            }
        }else{
            resp.sendRedirect("/signinpage");
        }
    }
}
