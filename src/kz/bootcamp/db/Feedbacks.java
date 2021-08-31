package kz.bootcamp.db;

import java.sql.Timestamp;

public class Feedbacks {

    private Long id;
    private Users user;
    private Items item;
    private String feedback;
    private Timestamp postDate;

    public Feedbacks(){}

    public Feedbacks(Users user, Items item, String feedback, Timestamp postDate) {
        this.user = user;
        this.item = item;
        this.feedback = feedback;
        this.postDate = postDate;
    }

    public Feedbacks(Long id, Users user, Items item, String feedback, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.feedback = feedback;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}