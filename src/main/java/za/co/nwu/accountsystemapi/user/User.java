package za.co.nwu.accountsystemapi.user;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name="users_sequence", sequenceName="users_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
