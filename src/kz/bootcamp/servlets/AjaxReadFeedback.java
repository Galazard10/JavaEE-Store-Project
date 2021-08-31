package kz.bootcamp.servlets;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet (value="/ajaxreadfeedback")
public class AjaxReadFeedback extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("itemId");
        Long itemId = Long.parseLong(id);

        Items item = DBManager.getItembyId(itemId);

        if(item != null){
            try {
                ArrayList<Feedbacks> feedbacks = DBManager.getFeedback(itemId);
                PrintWriter out = resp.getWriter();
                Gson gson = new Gson();
                String result  = gson.toJson(feedbacks);
                out.print(result);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
