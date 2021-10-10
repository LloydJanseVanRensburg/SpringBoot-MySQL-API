package za.co.nwu.accountsystemapi.activereward;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/activerewards")
public class ActiveRewardController {

    Logger logger = LoggerFactory.getLogger(ActiveRewardController.class);

    @Autowired
    ActiveRewardService activeRewardService;

    @GetMapping("/")
    @ApiOperation(
            value = "Get ALL Active Rewards",
            notes = "No data required",
            response = ActiveReward.class,
            responseContainer = "List"
    )
    public List<ActiveReward> getAllActiveRewards() {
        logger.debug("Get All Active Rewards Controller Fired");
        return activeRewardService.getAllActiveRewards();
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get ALL Active Rewards",
            notes = "Provide reward id in the url params",
            response = ActiveReward.class
    )
    public ActiveReward getActiveRewardById(@PathVariable int id) {
        logger.debug("Get Active Reward By Id Controller Fired");
        return activeRewardService.getActiveRewardNyId(id);
    }

    @PostMapping("/")
    @ApiOperation(
            value = "Create a new Active Reward item",
            notes = "Provide a Active Reward item in request body",
            response = ActiveReward.class
    )
    public ActiveReward getActiveRewardById(@RequestBody ActiveReward activeReward) {
        logger.debug("Create Active Reward Controller Fired");
        return activeRewardService.createActiveReward(activeReward);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update existing active reward by id",
            notes = "Provide a Active Reward item in request body and reward id in the url params",
            response = ActiveReward.class
    )
    public ActiveReward updateActiveRewardById(@PathVariable int id, @RequestBody ActiveReward activeReward) {
        logger.debug("Create Active Reward By Id Controller Fired");
        return activeRewardService.updateActiveReward(id, activeReward);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete existing active reward by id",
            notes = "Provide a Active Reward item id in the url params"
    )
    public void deleteActiveRewardById(@PathVariable int id) {
        logger.debug("Delete Active Reward By Id Controller Fired");
        activeRewardService.deleteActiveRewardById(id);
    }
}
