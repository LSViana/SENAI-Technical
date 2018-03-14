package sp.senai.stdevelopment.model;

/**
 * Created by Lucas Viana on 3/13/2018.
 */

public class Contact {

    private Long id;
    private String name;
    private String message;

    public Contact() {}

    public Contact(Long id, String name, String message) {
        this();
        this.id = id;
        this.name = name;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Message: %s", name, message);
    }
}
