package sstorage.mobile.senai.com.sstorage.model;

import android.content.Intent;

import java.time.LocalDate;
import java.util.Date;

public class PatrimonyItem {

    private Long id;

    private Patrimony patrimony;
    private Long patrimonyId;

    private Environment environment;
    private Long environmentId;

    private Long userId;
    private User user;

    private Date lastMovement;

    private Integer state;

    public PatrimonyItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patrimony getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(Patrimony patrimony) {
        this.patrimony = patrimony;
    }

    public Long getPatrimonyId() {
        return patrimonyId;
    }

    public void setPatrimonyId(Long patrimonyId) {
        this.patrimonyId = patrimonyId;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(Date lastMovement) {
        this.lastMovement = lastMovement;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
