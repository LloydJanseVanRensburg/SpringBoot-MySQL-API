package za.co.nwu.accountsystemapi.activereward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveRewardRepository extends JpaRepository<ActiveReward, Integer> {
}
