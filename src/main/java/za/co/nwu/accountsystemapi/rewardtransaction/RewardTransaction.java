package za.co.nwu.accountsystemapi.rewardtransaction;

import javax.persistence.*;

@Entity
@Table(name = "reward_transaction")
public class RewardTransaction {

    @Id
    @SequenceGenerator(name="rewardtransaction_sequence", sequenceName="rewardtransaction_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rewardtransaction_sequence")
    private int id;
    private int rewardId;
    private int userId;
    private int costInMiles;

    public RewardTransaction() {}

    public RewardTransaction(int id, int rewardId, int userId, int costInMiles) {
        this.id = id;
        this.rewardId = rewardId;
        this.userId = userId;
        this.costInMiles = costInMiles;
    }

    public RewardTransaction(int rewardId, int userId, int costInMiles) {
        this.rewardId = rewardId;
        this.userId = userId;
        this.costInMiles = costInMiles;
    }

    public int getId() {
        return id;
    }

    public int getRewardId() {
        return rewardId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCostInMiles() {
        return costInMiles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCostInMiles(int costInMiles) {
        this.costInMiles = costInMiles;
    }
}
