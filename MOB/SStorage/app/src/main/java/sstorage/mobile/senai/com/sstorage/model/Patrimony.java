package sstorage.mobile.senai.com.sstorage.model;

import java.time.LocalDate;
import java.util.Date;


public class Patrimony {

    private Long id;

    private String name;

    private Long patrimonyCategoryId;
    private PatrimonyCategory patrimonyCategory;

    private Long userId;
    private User user;

    private Date date;

    public Patrimony() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPatrimonyCategoryId() {
        return patrimonyCategoryId;
    }

    public void setPatrimonyCategoryId(Long patrimonyCategoryId) {
        this.patrimonyCategoryId = patrimonyCategoryId;
    }

    public PatrimonyCategory getPatrimonyCategory() {
        return patrimonyCategory;
    }

    public void setPatrimonyCategory(PatrimonyCategory patrimonyCategory) {
        this.patrimonyCategory = patrimonyCategory;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
