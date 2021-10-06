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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalMilesCount() {
        return totalMilesCount;
    }

    public void setTotalMilesCount(Integer totalMilesCount) {
        this.totalMilesCount = totalMilesCount;
    }

}
