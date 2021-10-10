package za.co.nwu.accountsystemapi.rewardtransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardTransactionRepository extends JpaRepository<RewardTransaction, Integer> {
}
