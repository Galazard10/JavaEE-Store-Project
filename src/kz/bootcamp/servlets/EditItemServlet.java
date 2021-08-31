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

@WebServlet(value="/edititempage")
public class EditItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null && user.isAdmin()) {
            String id = req.getParameter("itemId");
            long itemId = Long.parseLong(id);
            req.setAttribute("itemId", itemId);
            req.getRequestDispatcher("/edititempage.jsp").forward(req, resp);
        }else{
            resp.sendRedirect("/homepage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("itemId");
        long itemId = Long.parseLong(id);
        try {
            Items item = DBManager.getItembyId(itemId);
            String itemName = req.getParameter("itemName");
            String itemPrice = req.getParameter("itemPrice");
            String itemMemory = req.getParameter("itemMemory");
            String itemRam = req.getParameter("itemRam");
            String itemSystem = req.getParameter("itemSystem");
            String itemImg = req.getParameter("itemImg");

            item.setName(itemName);
            item.setPrice(itemPrice);
            item.setMemory(itemMemory);
            item.setRam(itemRam);
            item.setSystem(itemSystem);
            item.setImg(itemImg);

            DBManager.editItem(item);

            resp.sendRedirect("/detailspage?itemId=" + item.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
