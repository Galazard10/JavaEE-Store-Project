package kz.bootcamp.servlets;

import kz.bootcamp.db.Items;
import kz.bootcamp.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/deletebasket")
public class DeleteBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user == null)
            resp.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null) {
            String id = req.getParameter("id");
            long idValue = Long.parseLong(id);
            ArrayList<Items> items = (ArrayList<Items>) req.getSession().getAttribute("items");
            for (int i = 0; i < items.size(); ++i) {
                if (items.get(i).getId() == idValue) {
                    items.remove(i);
                    break;
                }
            }
            req.getSession().setAttribute("items", items);
            resp.sendRedirect("/basketpage");
        }else{
            resp.sendRedirect("/homepage");
        }
    }
}
