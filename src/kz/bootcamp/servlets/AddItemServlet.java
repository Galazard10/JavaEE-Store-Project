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

@WebServlet (value="/additempage")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null)
            req.getRequestDispatcher("/additempage.jsp").forward(req, resp);
        else
            resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        String name = req.getParameter("itemName");
        String price = req.getParameter("itemPrice");
        String memory = req.getParameter("itemMemory");
        String ram = req.getParameter("itemRam");
        String system = req.getParameter("itemSystem");
        String img_url = req.getParameter("itemImg");
        if(user != null && user.isAdmin()){
            Items item = new Items(name, price, memory, ram, system, img_url);
            try {
                DBManager.addItem(item);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            resp.sendRedirect("/additempage?success");
        }
    }
}
