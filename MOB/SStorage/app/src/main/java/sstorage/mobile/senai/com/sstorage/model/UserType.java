package sstorage.mobile.senai.com.sstorage.model;

import java.util.HashMap;

public enum UserType {

    REGULAR(0),
    ADMINISTRATOR(1);

    private int value;

    private UserType(int value) {
        this.value = value;
    }

    private static final HashMap<Integer, UserType> map = new HashMap<Integer, UserType>();

    static {
        for (UserType userType : UserType.values()) {
            map.put(userType.value, userType);
        }
    }

    private static final UserType valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

}
