package za.co.nwu.accountsystemapi.milesaccount;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/miles")
public class MilesAccountController {

    @Autowired
    MilesAccountService milesAccountService;

    @GetMapping("/")
    @ApiOperation(
            value="List out ALL of the miles accounts",
            notes="Nothing needs to be provided",
            response= MilesAccount.class,
            responseContainer = "List"
    )
    public List<MilesAccount> getMilesAccounts() {
        return milesAccountService.getMilesAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value="Find Miles Account By ID",
            notes="Provide Query Param with MilesAccount Id",
            response=MilesAccount.class
    )
    public MilesAccount getMilesAccount(@PathVariable int id) {
        return milesAccountService.getMilesAccount(id);
    }

    @PostMapping("/")
    @ApiOperation(
            value="Create a new MilesAccount",
            notes="Provide MilesAccount data",
            response=MilesAccount.class
    )
    public MilesAccount createMilesAccount(@RequestBody MilesAccount milesAccount ) {
        return milesAccountService.createMilesAccount(milesAccount);
    }

    @PutMapping("/{id}/add")
    @ApiOperation(
            value="Add value to a users totalMilesCount",
            notes="Provide User Id in URL params, and miles value to add in the request body",
            response=MilesAccount.class
    )
    public MilesAccount addMilesToAccount(@PathVariable int id, @RequestBody int milesValue) {
        return milesAccountService.addMilesToAccount(id, milesValue);
    }

    @PutMapping("/{id}/remove")
    @ApiOperation(
            value="Remove value from a users totalMilesCount",
            notes="Provide User Id in URL params, and miles value to remove in the request body",
            response=MilesAccount.class
    )
    public MilesAccount removeMilesFromAccount(@PathVariable int id, @RequestBody int milesValue) {
        return milesAccountService.removeMilesFromAccount(id, milesValue);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value="Delete MilesAccount By ID",
            notes="Provide MilesAccount Id in URL params",
            response=MilesAccount.class
    )
    public void deleteMilesAccount(@PathVariable int id) {
        milesAccountService.deleteMilesAccount(id);
    }
}
