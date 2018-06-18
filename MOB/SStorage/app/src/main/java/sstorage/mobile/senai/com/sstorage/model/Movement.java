package sstorage.mobile.senai.com.sstorage.model;

import java.time.LocalDate;
import java.util.Date;

public class Movement {

    private Long id;
    private Date dateTime;

    private PatrimonyItem patrimonyItem;
    private Long patrimonyItemId;

    private Environment originEnvironment;
    private Long originEnvironmentId;

    private Environment destinyEnvironment;
    private Long destinyEnvironmentId;

    private User user;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public PatrimonyItem getPatrimonyItem() {
        return patrimonyItem;
    }

    public void setPatrimonyItem(PatrimonyItem patrimonyItem) {
        this.patrimonyItem = patrimonyItem;
    }

    public Long getPatrimonyItemId() {
        return patrimonyItemId;
    }

    public void setPatrimonyItemId(Long patrimonyItemId) {
        this.patrimonyItemId = patrimonyItemId;
    }

    public Environment getOriginEnvironment() {
        return originEnvironment;
    }

    public void setOriginEnvironment(Environment originEnvironment) {
        this.originEnvironment = originEnvironment;
    }

    public Long getOriginEnvironmentId() {
        return originEnvironmentId;
    }

    public void setOriginEnvironmentId(Long originEnvironmentId) {
        this.originEnvironmentId = originEnvironmentId;
    }

    public Environment getDestinyEnvironment() {
        return destinyEnvironment;
    }

    public void setDestinyEnvironment(Environment destinyEnvironment) {
        this.destinyEnvironment = destinyEnvironment;
    }

    public Long getDestinyEnvironmentId() {
        return destinyEnvironmentId;
    }

    public void setDestinyEnvironmentId(Long destinyEnvironmentId) {
        this.destinyEnvironmentId = destinyEnvironmentId;
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
}
