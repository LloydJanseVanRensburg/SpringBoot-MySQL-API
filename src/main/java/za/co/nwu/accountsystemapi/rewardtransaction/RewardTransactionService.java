package za.co.nwu.accountsystemapi.rewardtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import java.util.List;
import java.util.Objects;

@Service
public class RewardTransactionService {

    @Autowired
    RewardTransactionRepository rewardTransactionRepository;

    public RewardTransactionService(RewardTransactionRepository rewardTransactionRepository) {
        this.rewardTransactionRepository = rewardTransactionRepository;
    }

    public List<RewardTransaction> getAllRewardTransactions() {
        return rewardTransactionRepository.findAll();
    }

    public RewardTransaction getRewardTransactionById(int id) {
        RewardTransaction foundTransaction = rewardTransactionRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Reward Transaction not found")
        );

        return foundTransaction;
    }

    public RewardTransaction createRewardTransaction(RewardTransaction rewardTransaction) {
        return rewardTransactionRepository.save(rewardTransaction);
    }

    public RewardTransaction updateRewardTransactionById(int id, RewardTransaction rewardTransaction){
        RewardTransaction foundTransaction = rewardTransactionRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Reward Transaction Not Found")
        );

        if(!Objects.equals(foundTransaction.getRewardId(), rewardTransaction.getRewardId())) {
            foundTransaction.setRewardId(rewardTransaction.getRewardId());
        }

        if(!Objects.equals(foundTransaction.getCostInMiles(), rewardTransaction.getCostInMiles())) {
            foundTransaction.setCostInMiles(rewardTransaction.getCostInMiles());
        }

        return rewardTransactionRepository.save(foundTransaction);
    }

    public void deleteRewardTransaction(int id) {
        RewardTransaction foundTransaction = rewardTransactionRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("Reward Transaction not found")
        );

        rewardTransactionRepository.deleteById(id);
    }
}
