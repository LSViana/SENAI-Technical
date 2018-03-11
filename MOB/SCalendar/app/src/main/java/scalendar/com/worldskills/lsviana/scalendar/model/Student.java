package scalendar.com.worldskills.lsviana.scalendar.model;

/**
 * Created by Lucas Viana on 2/21/2018.
 */

public class Student {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String telephone;
    private Integer classification;

    public Student() {
        // Standard constructor
    }

    public Student(Long id, String name, String address, String email, String telephone, Integer classification) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.classification = classification;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }
}
