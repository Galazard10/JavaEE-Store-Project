package kz.bootcamp.servlets;

import kz.bootcamp.db.DBManager;
import kz.bootcamp.db.Feedbacks;
import kz.bootcamp.db.Items;
import kz.bootcamp.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/ajaxaddfeedback")
public class AjaxAddFeedback extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null){
            String feed = req.getParameter("feedback");
            String id = req.getParameter("itemId");
            Long itemId = Long.parseLong(id);

            Items item = DBManager.getItembyId(itemId);
            if(item != null){
                Feedbacks feedback = new Feedbacks();
                feedback.setUser(user);
                feedback.setFeedback(feed);
                feedback.setItem(item);

                try {
                    if(DBManager.addFeedback(feedback)){

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
    }
}
