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

@WebServlet (value="/ajaxdeletefeedback")
public class AjaxDeleteFeedback extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if(user != null){
            String id = req.getParameter("feedbackId");
            Long feedbackId = Long.parseLong(id);
            try {
                DBManager.deleteFeedback(feedbackId, user.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
