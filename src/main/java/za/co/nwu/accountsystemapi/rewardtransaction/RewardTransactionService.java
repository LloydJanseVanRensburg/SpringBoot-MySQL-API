package za.co.nwu.accountsystemapi.rewardtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.activereward.ActiveReward;
import za.co.nwu.accountsystemapi.activereward.ActiveRewardService;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;
import za.co.nwu.accountsystemapi.milesaccount.MilesAccountService;
import za.co.nwu.accountsystemapi.user.User;
import za.co.nwu.accountsystemapi.user.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RewardTransactionService {

    @Autowired
    RewardTransactionRepository rewardTransactionRepository;
    @Autowired
    MilesAccountService milesAccountService;
    @Autowired
    UserService userService;
    @Autowired
    ActiveRewardService activeRewardService;

    public RewardTransactionService(RewardTransactionRepository rewardTransactionRepository) {
        this.rewardTransactionRepository = rewardTransactionRepository;
    }

    @Transactional
    public List<RewardTransaction> getAllRewardTransactions() {
        return rewardTransactionRepository.findAll();
    }

    @Transactional
    public RewardTransaction getRewardTransactionById(int id) {
        RewardTransaction foundTransaction = rewardTransactionRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Reward Transaction not found")
        );

        return foundTransaction;
    }

    @Transactional
    public RewardTransaction createRewardTransaction(int userId, int rewardId) {

        ActiveReward activeReward = activeRewardService.getActiveRewardNyId(rewardId);
        User user = userService.getUserById(userId);

        RewardTransaction rewardTransaction =
                new RewardTransaction(activeReward.getId(), user.getId(), activeReward.getRewardCostInMiles());

        RewardTransaction newRewardTransaction =  rewardTransactionRepository.save(rewardTransaction);

        milesAccountService.removeMilesFromAccount(
                user.getUserMilesAccount().getId(),
                rewardTransaction.getCostInMiles()
        );

        return newRewardTransaction;
    }

    @Transactional
    public void deleteRewardTransaction(int id) {
        RewardTransaction foundTransaction = rewardTransactionRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Reward Transaction not found")
        );

        rewardTransactionRepository.deleteById(id);
    }
}
