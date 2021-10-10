package za.co.nwu.accountsystemapi.milesaccount;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/miles")
public class MilesAccountController {

    // MilesAccountController Logger
    Logger logger = LoggerFactory.getLogger(MilesAccountController.class);

    @Autowired
    MilesAccountService milesAccountService;

    @GetMapping("/")
    @ApiOperation(
            value="Get ALL MilesAccounts",
            notes="No data required",
            response= MilesAccount.class,
            responseContainer = "List"
    )
    public List<MilesAccount> getMilesAccounts() {
        logger.debug("Get Miles Account(s) Controller Fired");
        return milesAccountService.getMilesAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value="Find Miles Account By ID",
            notes="Provide Query Param with MilesAccount Id",
            response=MilesAccount.class
    )
    public MilesAccount getMilesAccount(@PathVariable int id) {
        logger.trace("Get Miles Account Controller Fired");
        return milesAccountService.getMilesAccount(id);
    }

    @PostMapping("/")
    @ApiOperation(
            value="Create a new MilesAccount",
            notes="Provide MilesAccount data",
            response=MilesAccount.class
    )
    public MilesAccount createMilesAccount(@RequestBody MilesAccount milesAccount ) {
                logger.debug("Create Miles Account Controller Fired");
        return milesAccountService.createMilesAccount(milesAccount);
    }

    @PutMapping("/{id}/add")
    @ApiOperation(
            value="Add value to a users totalMilesCount",
            notes="Provide User Id in URL params, and miles value to add in the request body",
            response=MilesAccount.class
    )
    public MilesAccount addMilesToAccount(
            @PathVariable("id") int id,
            @RequestParam int milesValue,
            @RequestParam(defaultValue = "false") boolean makeException
    ) {
        if(makeException) {
            logger.error("Add To Miles Controller Fired -> CUSTOM FIELD CAUSED ERROR - THIS WAS ON PURPOSE");
            throw new ApiRequestException("Checking Custom Error Handling Implementation");
        } else {
            logger.debug("Add Miles To Account Controller Fired");
            return milesAccountService.addMilesToAccount(id, milesValue);
        }
    }

    @PutMapping("/{id}/remove")
    @ApiOperation(
            value="Remove value from a users totalMilesCount",
            notes="Provide User Id in URL params, and miles value to remove in the request body",
            response=MilesAccount.class
    )
    public MilesAccount removeMilesFromAccount(
            @PathVariable int id,
            @RequestParam int milesValue
    ) {
        logger.debug("Remove Miles From Account Controller Fired");
        return milesAccountService.removeMilesFromAccount(id, milesValue);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value="Delete MilesAccount By ID",
            notes="Provide MilesAccount Id in URL params",
            response=MilesAccount.class
    )
    public void deleteMilesAccount(@PathVariable int id) {
        logger.debug("Delete Miles Account Controller Fired");
        milesAccountService.deleteMilesAccount(id);
    }
}
