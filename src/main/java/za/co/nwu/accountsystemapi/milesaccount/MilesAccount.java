package za.co.nwu.accountsystemapi.milesaccount;

import javax.persistence.*;

@Entity
@Table
public class MilesAccount {

    @Id
    @SequenceGenerator(name="milesaccount_sequence", sequenceName="milesaccount_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="milesaccount_sequence")
    private Integer id;
    private Integer userId;
    private Integer totalMilesCount;

    public MilesAccount() {
    }

    public MilesAccount(Integer id, Integer userId, Integer totalMilesCount) {
        this.id = id;
        this.userId = userId;
        this.totalMilesCount = totalMilesCount;
    }

    public MilesAccount(Integer userId, Integer totalMilesCount) {
        this.userId = userId;
        this.totalMilesCount = totalMilesCount;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTotalMilesCount() {
        return totalMilesCount;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTotalMilesCount(Integer totalMilesCount) {
        this.totalMilesCount = totalMilesCount;
    }

}
