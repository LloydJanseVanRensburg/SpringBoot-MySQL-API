package za.co.nwu.accountsystemapi.user;

import javax.persistence.*;

@Entity(name = "User")
@Table(
        name="user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                )
        }
)
public class User {

    @Id
    @SequenceGenerator(name="users_sequence", sequenceName="users_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_sequence")
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "VARCHAR(50)",
            unique = true
    )
    private String email;

    public User() {}

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

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
