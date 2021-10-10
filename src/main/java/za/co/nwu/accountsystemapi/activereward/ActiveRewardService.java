package za.co.nwu.accountsystemapi.activereward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ActiveRewardService {

    @Autowired
    ActiveRewardRepository activeRewardRepository;

    public ActiveRewardService(ActiveRewardRepository activeRewardRepository) {
        this.activeRewardRepository = activeRewardRepository;
    }

    @Transactional
    public ActiveReward createActiveReward(ActiveReward activeReward) {
        return activeRewardRepository.save(activeReward);
    }

    @Transactional
    public ActiveReward getActiveRewardNyId(int id) {
        ActiveReward activeReward = activeRewardRepository.findById(id)
                .orElseThrow( () -> new ApiRequestException("Active Reward Not Found"));

        return activeReward;
    }

    @Transactional
    public List<ActiveReward> getAllActiveRewards() {
        return activeRewardRepository.findAll();
    }

    @Transactional
    public ActiveReward updateActiveReward(int id, ActiveReward activeReward) {
        ActiveReward currentReward = activeRewardRepository.findById(id)
                .orElseThrow(()-> new ApiRequestException("Active Reward Not Found"));

        if(!Objects.equals(activeReward.getRewardTitle(), currentReward.getRewardTitle())) {
            currentReward.setRewardTitle(activeReward.getRewardTitle());
        }

        if(!Objects.equals(activeReward.getRewardDescription(), currentReward.getRewardDescription())) {
            currentReward.setRewardDescription(activeReward.getRewardDescription());
        }

        if(!Objects.equals(activeReward.getRewardCostInMiles(), currentReward.getRewardCostInMiles())) {
            currentReward.setRewardCostInMiles(activeReward.getRewardCostInMiles());
        }

        return activeRewardRepository.save(currentReward);
    }

    @Transactional
    public void deleteActiveRewardById(int id) {
        ActiveReward foundActiveReward = activeRewardRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Active Reward Not Found"));

        activeRewardRepository.deleteById(id);
    }
}
