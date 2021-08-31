package kz.bootcamp.db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    public static ArrayList<Items> getItemlist(){
        ArrayList<Items> itemlist = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM items");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                itemlist.add(new Items(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("price"),
                        resultSet.getString("ram"), resultSet.getString("memory"), resultSet.getString("system"),
                        resultSet.getString("image_url"), resultSet.getInt("likes")));
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return itemlist;
    }

    public static Items getItembyId(Long id) {
        Items item = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM items WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = new Items(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("price"),
                        resultSet.getString("memory"), resultSet.getString("ram"), resultSet.getString("system"),
                        resultSet.getString("image_url"), resultSet.getInt("likes"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    public static void addItem(Items item) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO items(name, price, memory, ram, system, image_url) " +
                "VALUES(?, ?, ?, ?, ?, ?)");
        statement.setString(1,item.getName());
        statement.setString(2,"$"+item.getPrice());
        statement.setString(3,item.getMemory() + " GB SSD");
        statement.setString(4,item.getRam() + " GB RAM");
        statement.setString(5,item.getSystem());
        statement.setString(6,item.getImg());
        statement.executeUpdate();
        statement.close();
    }

    public static void editItem(Items item) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("" +
                "UPDATE items SET name = ?, price = ?, memory = ?, ram = ?, system = ?, image_url = ? WHERE id = ? ");

        statement.setString(1, item.getName());
        statement.setString(2, item.getPrice());
        statement.setString(3, item.getMemory());
        statement.setString(4, item.getRam());
        statement.setString(5, item.getSystem());
        statement.setString(6, item.getImg());
        statement.setLong(7, item.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteItem(long itemId) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM items WHERE id=?");
        statement.setLong(1, itemId);
        statement.executeUpdate();
        statement.close();
    }

    public static ArrayList<Users> getUserList(){
        ArrayList<Users> userList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                userList.add(new Users(resultSet.getLong("id"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("full_name"), resultSet.getBoolean("isAdmin")));
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    public static Users getUser(String email, String password) throws SQLException {
        Users user = null;
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("password").equals(password))
                    user = new Users(resultSet.getLong("id"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("full_name"), resultSet.getBoolean("isAdmin"));
            }
            return user;
    }

    public static void addUser(Users user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO users(email, password, full_name) " +
                "VALUES(?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.executeUpdate();
            statement.close();
    }

    public static void saveUser(Users user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
        "UPDATE users SET full_name = ? WHERE id=?");
        statement.setString(1, user.getFullName());
        statement.setLong(2, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void savePassword(Users user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "UPDATE users SET password = ? WHERE id = ?");
        statement.setString(1, user.getPassword());
        statement.setLong(2, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static boolean addFeedback(Feedbacks feedback) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO feedbacks(user_id, item_id, feedback, post_date)" +
                "VALUES (?, ?, ?, NOW())");
        statement.setLong(1, feedback.getUser().getId());
        statement.setLong(2, feedback.getItem().getId());
        statement.setString(3, feedback.getFeedback());
        statement.executeUpdate();
        statement.close();
        return false;
    }

    public static ArrayList<Feedbacks> getFeedback(Long itemId) throws SQLException{
        ArrayList<Feedbacks> feedbacks = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT f.id, f.item_id, f.user_id, f.feedback, f.post_date, a.full_name " +
                "FROM feedbacks f " +
                "INNER JOIN users a ON f.user_id = a.id " +
                "WHERE f.item_id = ? " +
                "ORDER BY f.post_date DESC");
            statement.setLong(1, itemId);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            feedbacks.add(new Feedbacks(
                resultSet.getLong("id"),
                new Users(
                        resultSet.getLong("user_id"),
                        null,
                        null,
                        resultSet.getString("full_name"),
                        false
                ),
                new Items(
                        resultSet.getLong("item_id"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                resultSet.getString("feedback"),
                resultSet.getTimestamp("post_date")
            ));
        }
        return feedbacks;
    }

    public static boolean deleteFeedback(Long feedbackId, Long user_id) throws SQLException {
        int rows = 0;
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM feedbacks " +
                "WHERE id = ? AND user_id = ?");
        statement.setLong(1, feedbackId);
        statement.setLong(2, user_id);
        rows = statement.executeUpdate();
        statement.close();
        return rows>0;
    }

    public static int toLike(Long itemId, Long userId) throws SQLException {
        boolean liked = false;
        int likeAmount = 0;
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM item_likes WHERE user_id = ? AND item_id = ? ");
        statement.setLong(1, userId);
        statement.setLong(2, itemId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            liked = true;
        }
        statement.close();


        String query = "DELETE FROM item_likes WHERE user_id = ? AND item_id = ?";
        if(!liked)
            query = "INSERT INTO item_likes(user_id, item_id) VALUES (?, ?)";
        PreparedStatement statement1 = connection.prepareStatement(query);
        statement1.setLong(1, userId);
        statement1.setLong(2, itemId);
        statement1.executeUpdate();
        statement1.close();

        PreparedStatement statement2 = connection.prepareStatement("" +
                "SELECT COUNT(*) like_amount FROM item_likes WHERE item_id = ? ");
        statement2.setLong(1, itemId);
        ResultSet resultSet1 = statement2.executeQuery();
        if(resultSet1.next())
            likeAmount = resultSet1.getInt("like_amount");
        statement2.close();

        PreparedStatement statement3 = connection.prepareStatement("" +
                "UPDATE items SET likes = ? WHERE id = ? ");
            statement3.setInt(1, likeAmount);
            statement3.setLong(2, itemId);
            statement3.executeUpdate();
            statement3.close();

        return likeAmount;
    }

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_db", "root", "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
