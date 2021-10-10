package za.co.nwu.accountsystemapi.activereward;

import javax.persistence.*;

@Entity
@Table
public class ActiveReward {

    @Id
    @SequenceGenerator(name="activereward_sequence", sequenceName="activereward_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="activereward_sequence")
    private int id;
    private String rewardTitle;
    private String rewardDescription;
    private int rewardCostInMiles;

    public ActiveReward() {}

    public ActiveReward(int id, String rewardTitle, String rewardDescription, int rewardCostInMiles) {
        this.id = id;
        this.rewardTitle = rewardTitle;
        this.rewardDescription = rewardDescription;
        this.rewardCostInMiles = rewardCostInMiles;
    }

    public ActiveReward(String rewardTitle, String rewardDescription, int rewardCostInMiles) {
        this.rewardTitle = rewardTitle;
        this.rewardDescription = rewardDescription;
        this.rewardCostInMiles = rewardCostInMiles;
    }

    public int getId() {
        return id;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public int getRewardCostInMiles() {
        return rewardCostInMiles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    public void setRewardCostInMiles(int rewardCostInMiles) {
        this.rewardCostInMiles = rewardCostInMiles;
    }
}
