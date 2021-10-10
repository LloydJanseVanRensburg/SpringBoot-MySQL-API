package za.co.nwu.accountsystemapi.rewardtransaction;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/transaction")
public class RewardTransactionController {

    Logger logger = LoggerFactory.getLogger(RewardTransactionController.class);

    @Autowired
    RewardTransactionService rewardTransactionService;

    @GetMapping("/")
    @ApiOperation(
            value="Get ALL Reward Transactions",
            notes="No data required",
            response= RewardTransaction.class,
            responseContainer = "List"
    )
    public List<RewardTransaction> getAllRewardTransactions () {
        logger.debug("Get All Reward Transactions Controller Fired");
        return rewardTransactionService.getAllRewardTransactions();
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value="Get Reward Transaction By Id",
            notes = "Provide Transaction Id in URL param",
            response= RewardTransaction.class
    )
    public RewardTransaction getRewardTransactionById(@PathVariable int id) {
        logger.debug("Get Reward Transaction By Id Controller Fired");

        return rewardTransactionService.getRewardTransactionById(id);
    }

    @PostMapping("/{}")
    @ApiOperation(
            value="Create a new reward transaction",
            notes = "Provide request body with reward transaction object",
            response= RewardTransaction.class
    )
    public RewardTransaction createRewardTransaction(@RequestParam int userId,
                                                     @RequestParam int rewardId) {
        logger.debug("Create Reward Transaction Controller Fired");
        return rewardTransactionService.createRewardTransaction(userId, rewardId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value="Delete a reward transaction by Id",
            notes = "Provide url param containing reward transaction id"
    )
    public void deleteRewardTransaction (@PathVariable int id) {
        logger.debug("Delete Reward Transaction By Id Controller Fired");
        rewardTransactionService.deleteRewardTransaction(id);
    }

}
