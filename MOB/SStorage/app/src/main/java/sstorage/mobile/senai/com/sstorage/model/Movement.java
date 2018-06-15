package sstorage.mobile.senai.com.sstorage.model;

import java.time.LocalDate;

public class Movement {

    private Long id;
    private LocalDate dateTime;

    private PatrimonyItem patrimonyItem;
    private Long patrimonyItemId;

    private Environment originEnvironment;
    private Long originEnvironmentId;

    private Environment destinyEnvironment;
    private Long destinyEnvironmentId;

    private User user;
    private Long userId;

}
