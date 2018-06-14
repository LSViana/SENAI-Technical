package sstorage.mobile.senai.com.sstorage.model.ViewModel;

import sstorage.mobile.senai.com.sstorage.model.User;
import sstorage.mobile.senai.com.sstorage.model.UserType;

public class Authentication {

    private String userName;
    private String userLastName;
    private Long userId;
    private UserType userType;
    private String token;

    public Authentication() {
    }

    public User asUser() {
        User user = new User();
        user.setId(userId);
        user.setName(userName);
        user.setLastName(userLastName);
        user.setUserType(userType);
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
