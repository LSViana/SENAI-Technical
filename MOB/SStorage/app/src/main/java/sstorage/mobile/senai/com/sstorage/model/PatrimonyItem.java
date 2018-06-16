package sstorage.mobile.senai.com.sstorage.model;

import java.time.LocalDate;

public class PatrimonyItem {

    private Long id;

    private Patrimony patrimony;
    private Long patrimonyId;

    private Environment environment;
    private Long environmentId;

    private User user;
    private User userId;

    private LocalDate lastMovement;

    private PatrimonyItemState state;

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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(LocalDate lastMovement) {
        this.lastMovement = lastMovement;
    }

    public PatrimonyItemState getState() {
        return state;
    }

    public void setState(PatrimonyItemState state) {
        this.state = state;
    }
}
